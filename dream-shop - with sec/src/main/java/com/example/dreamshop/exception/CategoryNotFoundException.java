package com.example.dreamshop.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String categoryNotFound) {
        super(categoryNotFound);
    }
}
