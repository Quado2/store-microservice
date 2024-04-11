package com.quado.inventoryservice.controller;


import com.quado.inventoryservice.dto.InventoryResponse;
import com.quado.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInstock(@RequestParam("sku-code") List<String> skuCode) {
        return inventoryService.getStock(skuCode);
    }
}
