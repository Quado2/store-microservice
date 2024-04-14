package com.quado.productservice.utils;

import com.quado.productservice.dto.ProductResponse;
import com.quado.productservice.model.Product;

public class Util {

  public static ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
        .name(product.getName())
        .id(product.getId())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
  }

}
