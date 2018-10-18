package com.lool.order.resource;

import com.lool.order.RuntimeException.OrderNotFoundException;
import com.lool.order.domain.Order;
import com.lool.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<Order> allProducts() {
        return orderService.findAllProducts();
    }
    @GetMapping("/{code}")
    public Order productByCode(@PathVariable String code) {
        return orderService.findProductByCode(code)
                .orElseThrow(() -> new OrderNotFoundException("Order with code ["+code+"] doesn't exist"));
    }

    @Value("${message: default}")
    String name;

    @GetMapping("/name")
    public String name()
    {
        return name;
    }


}
