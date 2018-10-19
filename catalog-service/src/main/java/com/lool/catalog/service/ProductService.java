package com.lool.catalog.service;

import com.lool.catalog.domain.Product;
import com.lool.catalog.repository.ProductRepository;
import com.lool.catalog.util.ProductInventoryDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
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

    @HystrixCommand(fallbackMethod = "getProductInventoryByCode", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
        }
    )
    public Optional<ProductInventoryDto> findProductByCode(String code) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    Optional<ProductInventoryDto> getProductInventoryByCode(String code){
        ProductInventoryDto prodResponceEntity = new ProductInventoryDto();
        prodResponceEntity.setProductCode(code);
        prodResponceEntity.setAvailableQuantity(0);
        return Optional.ofNullable(prodResponceEntity);
    }

}
