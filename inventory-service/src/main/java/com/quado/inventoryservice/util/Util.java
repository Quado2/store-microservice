package com.quado.inventoryservice.util;

import com.quado.inventoryservice.dto.InventoryResponse;
import com.quado.inventoryservice.model.Inventory;

public class Util {

  public static InventoryResponse mapToInventoryResponse(Inventory inventory) {
    return InventoryResponse.builder()
        .quantity(inventory.getQuantity())
        .skuCode(inventory.getSkuCode())
        .id(inventory.getId())
        .build();
  }
}
