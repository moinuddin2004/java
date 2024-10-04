package com.example.dreamshop.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String categoryAlredyExists) {
        super(categoryAlredyExists);
    }
}
