package com.lool.catalog.service;

import com.lool.catalog.domain.Product;
import com.lool.catalog.repository.ProductRepository;
import com.lool.catalog.util.ProductInventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    public Optional<ProductInventoryDto> findProductByCode(String code) {
        Optional<Product> productOp = productRepository.findByCode(code);
        if(productOp.isPresent()){
            ResponseEntity<ProductInventoryDto> prodResponceEntity = restTemplate.getForEntity("http://inventory-service/api/inventory/{code}",ProductInventoryDto.class,code);

            if(prodResponceEntity.getStatusCode() == HttpStatus.OK)
            {
                return Optional.ofNullable(prodResponceEntity.getBody());
            }
            else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

}
