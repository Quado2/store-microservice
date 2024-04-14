package com.quado.productservice.service;

import com.quado.productservice.dto.ProductRequest;
import com.quado.productservice.dto.ProductResponse;
import com.quado.productservice.model.Product;
import com.quado.productservice.repository.ProductRepository;
import com.quado.productservice.utils.Util;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.xml.UtilNamespaceHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponse createProduct(ProductRequest productRequest) {
    Product product = Product.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .build();
    Product savedProduct =  productRepository.save(product);
    log.info("Product with id: {} is saved", product.getId());

    return Util.mapToProductResponse(savedProduct);
  }

  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream().map(product -> Util.mapToProductResponse(product)).toList();
  }
}
