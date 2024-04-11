package com.quado.inventoryservice.service;

import com.quado.inventoryservice.dto.InventoryResponse;
import com.quado.inventoryservice.repository.InventoryRepository;

import com.quado.inventoryservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @SneakyThrows
    @Transactional(readOnly=true)
    public List<InventoryResponse> getStock(List<String> skuCodes){
//        log.info("Started");
//        Thread.sleep(5000);
//        log.info("Finished");
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
            .map(Util::mapToInventoryResponse).toList();
    }
}
