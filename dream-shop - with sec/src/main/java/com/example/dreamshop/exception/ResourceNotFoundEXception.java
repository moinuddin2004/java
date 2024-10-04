package com.example.dreamshop.exception;

public class ResourceNotFoundEXception extends  RuntimeException {
    public ResourceNotFoundEXception(String cartNotFound) {
        super(cartNotFound);
    }
}
