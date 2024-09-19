package com.example.dreamshop.service.image;

import com.example.dreamshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image getImageById(Long Id);
    void deleteImageById(Long Id);
    Image saveImage(MultipartFile file, Long imageId);
    void updateImage(MultipartFile file , Long imageId);
}
