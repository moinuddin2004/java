package com.example.dreamshop.request;

import com.example.dreamshop.model.Category;
import lombok.Data;

import java.math.BigDecimal;
@Data

public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
