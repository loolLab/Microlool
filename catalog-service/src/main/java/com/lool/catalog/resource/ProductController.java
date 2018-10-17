package com.lool.catalog.resource;

import com.lool.catalog.RuntimeException.ProductNotFoundException;
import com.lool.catalog.service.ProductService;
import com.lool.catalog.domain.Product;
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
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> allProducts() {
        return productService.findAllProducts();
    }
    @GetMapping("/{code}")
    public Product productByCode(@PathVariable String code) {
        return productService.findProductByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Product with code ["+code+"] doesn't exist"));
    }

    @Value("${message: default}")
    String name;

    @GetMapping("/name")
    public String name()
    {
        return name;
    }


}
