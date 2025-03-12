package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductRepository;
import com.example.demo.domain.product.RequestProduct;

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

    @GetMapping("/{id}")
    public ResponseEntity getOneProduct(@PathVariable String id) {
        Optional<Product> productFinded = repository.findById(id);
    
        return ResponseEntity.ok(productFinded);
    }

    @PostMapping
    public ResponseEntity registerNewProduct(@RequestBody @Validated RequestProduct data) {
        Product newProduct = new Product(data);

        repository.save(newProduct);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestParam String id) {
        repository.deleteById(id);
        
        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyProduct(@RequestBody @Validated RequestProduct data, @PathVariable String id) {
        Optional<Product> productFinded = repository.findById(id);
        
        if(productFinded.isPresent()) {
            Product product = productFinded.get();

            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());

            repository.save(product);

            return ResponseEntity.ok("Product changed!");
        }

        return ResponseEntity.notFound().build();
    }
    
}
