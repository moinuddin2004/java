package com.example.dreamshop.service.image;

import com.example.dreamshop.dto.ImageDto;
import com.example.dreamshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long Id);
    void deleteImageById(Long Id);
    List<ImageDto> saveImage(List<MultipartFile> files, Long imageId);
    void updateImage(MultipartFile file , Long imageId);
}
