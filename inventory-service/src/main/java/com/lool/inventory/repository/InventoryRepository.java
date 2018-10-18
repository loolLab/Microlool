package com.lool.inventory.repository;

import com.lool.inventory.domain.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory,String> {

    Optional<Inventory> findByProductCode(String productCode);

}
