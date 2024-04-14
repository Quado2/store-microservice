package com.quado.orderservice.controller;

import com.quado.orderservice.dto.OrderRequest;
import com.quado.orderservice.dto.OrderResponse;
import com.quado.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
  @TimeLimiter(name="inventory")
  @Retry(name="inventory")
  public CompletableFuture<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
    return  CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest)) ;
  }

  public CompletableFuture<OrderResponse>  fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
    return CompletableFuture.supplyAsync(() -> OrderResponse.builder().orderNumber("Failed, try again later").build());
  }
}
