package com.example.webDemo.service;

import com.example.webDemo.model.Product;
import com.example.webDemo.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class productService {

    @Autowired
    productRepo repo;


//    private List<Product> products = new ArrayList<>(Arrays.asList(
//            new Product(1, "iPhone", 5000),
//            new Product(2, "Samsung Galaxy", 4000),
//            new Product(3, "Google Pixel", 4500)
//    ));

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(int prodId) {
        return repo.findById(prodId).orElse(new Product());
    }

    public void addProduct(Product prod) {
        System.out.println(prod);
       repo.save(prod);
    }

    public void updateProduct(Product prod) {

       repo.save(prod);
    }

    public void deleteProductById(int prodId) {
       repo.deleteById(prodId);
    }
}