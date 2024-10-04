package com.example.dreamshop.service.product;

import com.example.dreamshop.dto.ProductDto;
import com.example.dreamshop.model.Product;
import com.example.dreamshop.request.AddProductRequest;
import com.example.dreamshop.request.ProductUpdateRequest;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface IProductService {
    List<ProductDto>  getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);

    Product addProduct(AddProductRequest product);

    Product getProductById(Long Id);

    void deleteProduct(Long id);

    public Product updateProduct(ProductUpdateRequest request, Long Id);

    List<Product> getAllProducts();

    List<Product> getProductByBrand(String brand);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String name, String brand);

    Long countProductsByBrandAndNAme(String brand, String name);
}
