package com.quado.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {
  private String id;
  private String name;
  private String description;
  private BigDecimal price;
}
