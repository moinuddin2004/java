package com.example.dreamshop.service.product;

import com.example.dreamshop.dto.ImageDto;
import com.example.dreamshop.dto.ProductDto;
import com.example.dreamshop.exception.ProductNotFoundException;
import com.example.dreamshop.model.Category;
import com.example.dreamshop.model.Image;
import com.example.dreamshop.model.Product;
import com.example.dreamshop.repo.CategoryRepo;
import com.example.dreamshop.repo.ImageRepo;
import com.example.dreamshop.repo.ProductRepo;
import com.example.dreamshop.request.AddProductRequest;
import com.example.dreamshop.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final ImageRepo imageRepo;

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images = imageRepo.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream().map(image -> modelMapper.map(image, ImageDto.class)).toList();
        productDto.setImages(imageDtos);
        return productDto;
    }


    @Override
    public Product addProduct(AddProductRequest request) {
        // check if the category is found in db
        // if yes, set it as the new product category
        // if no then save it as a new category
        // the set as the new product category
        Category category = Optional.ofNullable(categoryRepo.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepo.save(newCategory);

                });
        request.setCategory((category));
        return productRepo.save(createProduct(request, category));


    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        System.out.println(id);
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("not found"));

    }

    @Override
    public void deleteProduct(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        productRepo.findById(id).ifPresentOrElse(productRepo::delete, () -> {
            throw new ProductNotFoundException("product not found");
        });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long Id) {
        return productRepo.findById(Id)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepo::save)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));

    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setInventory(request.getInventory());
        Category category = categoryRepo.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepo.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepo.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepo.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String name, String brand) {
        return productRepo.findByNameAndBrand(name, brand);
    }

    @Override
    public Long countProductsByBrandAndNAme(String brand, String name) {
        return productRepo.countByBrandAndName(brand, name);
    }
}
