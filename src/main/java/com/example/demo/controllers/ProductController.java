package com.example.demo.controllers;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductRepository;
import com.example.demo.domain.product.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Gabriel
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAll();

        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerNewProduct(@RequestBody @Validated RequestProduct data) {
        Product newProduct = new Product(data);

        repository.save(newProduct);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestParam String id) {
        Object productFinded = repository.findById(id);
    
        return ResponseEntity.ok(productFinded);
    }
    
}
