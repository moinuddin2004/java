package com.example.dreamshop.service.image;

import com.example.dreamshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

public class ImageService implements IImageService{
    @Override
    public Image getImageById(Long Id) {
        return null;
    }

    @Override
    public void deleteImageById(Long Id) {

    }

    @Override
    public Image saveImage(MultipartFile file, Long imageId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

    }
}
