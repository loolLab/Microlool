package com.lool.inventory.resource;

import com.lool.inventory.domain.Inventory;
import com.lool.inventory.repository.InventoryRepository;
import com.lool.inventory.util.InventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
public class InventoryController {



    @Autowired
    private InventoryRepository inventoryRepository;


    @GetMapping("/api/inventory/{productCode}")
    public ResponseEntity findInventoryByProductCode(@PathVariable("productCode") String productCode) {

        Optional<Inventory> inventory = inventoryRepository.findByProductCode(productCode);
        if(inventory.isPresent()) {
            InventoryDto inventoryItem = new InventoryDto();
            inventoryItem.setProductCode(inventory.get().getProductCode());
            inventoryItem.setAvailableQuantity(inventory.get().getAvailableQuantity());
            return new ResponseEntity(inventoryItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<InventoryDto>>(HttpStatus.NOT_FOUND);
        }
    }

}

