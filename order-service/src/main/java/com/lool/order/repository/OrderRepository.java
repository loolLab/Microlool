package com.lool.order.repository;


import com.lool.order.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    Optional<Order> findByCode(String code);

}
