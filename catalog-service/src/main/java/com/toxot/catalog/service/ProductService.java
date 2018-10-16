package com.toxot.catalog.service;

import com.toxot.catalog.domain.Product;
import com.toxot.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    public Optional<Product> findProductByCode(String code) {
        return productRepository.findByCode(code);
    }

}
