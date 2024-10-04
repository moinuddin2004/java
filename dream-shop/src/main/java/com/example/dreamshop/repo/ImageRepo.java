package com.example.dreamshop.repo;

import com.example.dreamshop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepo  extends JpaRepository<Image,Long> {
    List<Image> findByProductId(Long id);
}
