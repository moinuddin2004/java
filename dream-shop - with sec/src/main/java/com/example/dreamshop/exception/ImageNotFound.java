package com.example.dreamshop.exception;

public class ImageNotFound extends RuntimeException {
    public ImageNotFound(String imageNotFound) {
        super(imageNotFound);
    }
}
