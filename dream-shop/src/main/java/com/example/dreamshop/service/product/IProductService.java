package com.example.dreamshop.service.product;

import com.example.dreamshop.model.Product;
import com.example.dreamshop.request.AddProductRequest;
import com.example.dreamshop.request.ProductUpdateRequest;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);

    Product getProductById(Long Id);

    void DeleteProduct(Long Id);

    public Product UpdateProduct(ProductUpdateRequest request, Long Id);

    List<Product> getAllProducts();

    List<Product> getProductByBrand(String brand);

    List<Product> getProductsBYCategory(String category);

    List<Product> getProductsBYCategoryAndBrand(String category, String brand);

    List<Product> getProductsBYName(String name);

    List<Product> getProductsBYBrandAndName(String name, String brand);

    Long countProductsByBrandAndNAme(String brand, String name);
}
