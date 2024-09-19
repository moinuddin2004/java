package com.example.webDemo.controller;

import com.example.webDemo.model.Product;
import com.example.webDemo.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {
    public productController(productService service) {
        this.service = service;
    }

    productService service;

    @GetMapping("/products")
    public List<Product> getProduct() {
        return service.getProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProductByIId(@PathVariable int prodId) {
        return service.getProductById(prodId);
    }

    @PostMapping("/products")
    public void addProducts( @RequestBody Product prod){
        service.addProduct(prod);
    }

    @PutMapping("/products")
    public void updateProducts( @RequestBody Product prod){
        service.updateProduct(prod);
    }


    @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable int prodId) {
         service.deleteProductById(prodId);
    }
}
