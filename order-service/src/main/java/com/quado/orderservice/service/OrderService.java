package com.quado.orderservice.service;
import com.quado.orderservice.dto.InventoryResponse;
import com.quado.orderservice.dto.OrderRequest;
import com.quado.orderservice.dto.OrderResponse;
import com.quado.orderservice.event.OrderPlacedEvent;
import com.quado.orderservice.model.Order;
import com.quado.orderservice.model.OrderLineItems;
import com.quado.orderservice.repository.OrderRepository;
import com.quado.orderservice.util.Util;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final WebClient.Builder webClientBuilder;
  private final ObservationRegistry observationRegistry;
  private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

  public OrderResponse placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos().stream().map(orderLineItemsDto -> Util.mapToOrderLineItems(orderLineItemsDto)).toList();
    order.setOrderLineItemsList(orderLineItems);

    List<String> skuCodes = order.getOrderLineItemsList().stream()
        .map(OrderLineItems::getSkuCode).toList();

    //Set up tracing to make up for the new thread that the circuit breaker will create
    Observation inventoryServerObservation = Observation.createNotStarted("inventory-service-lookup", this.observationRegistry);
    inventoryServerObservation.lowCardinalityKeyValue("call", "inventory-service");

    return inventoryServerObservation.observe(() -> {
      InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
          .uri("http://inventory-service/api/inventories", uriBuilder -> uriBuilder.queryParam("sku-code", skuCodes).build())
          .retrieve()
          .bodyToMono(InventoryResponse[].class)
          .block();
      Order savedOrder = null;
      log.info("Fetched inventories: {}", inventoryResponses);
      boolean isAllProductInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.getQuantity() > 0);
      if(inventoryResponses.length > 0 && isAllProductInStock) {
        savedOrder = orderRepository.save(order);
        log.info("About to send Notification");
        kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(savedOrder.getOrderNumber()));
        log.info("Notification Sent");
      } else {
        throw new IllegalArgumentException("Product is not in stock, please try again later");
      }
      return Util.mapToOrderResponse(savedOrder);
    });


  }
}
