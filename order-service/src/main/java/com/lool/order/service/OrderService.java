package com.lool.order.service;


import com.lool.order.domain.Order;
import com.lool.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> findAllProducts() {
        return (List<Order>) orderRepository.findAll();
    }
    public Optional<Order> findProductByCode(String code) {
        return orderRepository.findByCode(code);
    }

}
