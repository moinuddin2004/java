package com.example.dreamshop.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String s) {
        super(s);
    }
}
