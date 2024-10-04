package com.example.dreamshop.service.image;

import com.example.dreamshop.dto.ImageDto;
import com.example.dreamshop.exception.ImageNotFound;
import com.example.dreamshop.model.Image;
import com.example.dreamshop.model.Product;
import com.example.dreamshop.repo.ImageRepo;
import com.example.dreamshop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    private final ImageRepo imageRepo;
    private final ProductService productService;

    @Override
    public Image getImageById(Long Id) {
        return imageRepo.findById(Id)
                .orElseThrow(() -> new ImageNotFound("Image not found"));
    }

    @Override
    public void deleteImageById(Long Id) {
        imageRepo.findById(Id).ifPresentOrElse(imageRepo::delete, () -> {
                    throw new ImageNotFound("image not found");
                }
        );
    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> imageDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);
                String downloadUrl = "/api/v1/images/image/download/" + image.getId();
                image.setDownloadURL(downloadUrl);
                Image savedImage = imageRepo.save(image);
                savedImage.setDownloadURL("/api/v1/images/image/download/" + savedImage.getId());
                imageRepo.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                imageDto.setDownloadURL(savedImage.getDownloadURL());
                imageDto.setFileName(savedImage.getFileName());

                imageDtos.add(imageDto);

            } catch (SerialException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imageDtos;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepo.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
