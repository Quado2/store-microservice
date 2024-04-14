package com.quado.orderservice.util;

import com.quado.orderservice.dto.OrderLineItemsDto;
import com.quado.orderservice.dto.OrderResponse;
import com.quado.orderservice.model.Order;
import com.quado.orderservice.model.OrderLineItems;

public class Util {
  public static OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
   return OrderLineItems.builder()
        .price(orderLineItemsDto.getPrice())
        .skuCode(orderLineItemsDto.getSkuCode())
        .quantity(orderLineItemsDto.getQuantity())
        .build();
  }

  public static OrderResponse mapToOrderResponse(Order savedOrder) {
    return OrderResponse.builder()
        .id(savedOrder.getId())
        .orderNumber(savedOrder.getOrderNumber())
        .orderLineItemsList(savedOrder.getOrderLineItemsList())
        .build();

  }
}
