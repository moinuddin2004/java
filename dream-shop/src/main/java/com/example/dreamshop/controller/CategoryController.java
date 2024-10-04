package com.example.dreamshop.controller;

import com.example.dreamshop.exception.AlreadyExistsException;
import com.example.dreamshop.exception.CategoryNotFoundException;
import com.example.dreamshop.model.Category;
import com.example.dreamshop.response.ApiResponse;
import com.example.dreamshop.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {

        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("found!", categories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name) {
        try {
            Category theCategory = categoryService.addCategory(name);
            return ResponseEntity.ok(new ApiResponse("added", theCategory));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{Id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long Id) {
        try {
            Category TheCategory = categoryService.getCategoryById(Id);
            return ResponseEntity.ok(new ApiResponse("found", TheCategory));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("not found", null));
        }

    }

    @GetMapping("/{Name}/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String Name) {
        try {
            Category TheCategory = categoryService.getCategoryByName(Name);
            return ResponseEntity.ok(new ApiResponse("found", TheCategory));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("not found", null));
        }

    }

    @DeleteMapping("/category/{Id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long Id) {
        try {
            categoryService.deleteCategoryById(Id);
            return ResponseEntity.ok(new ApiResponse("deleted", null));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/category/{Id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long Id, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category, Id);
            return ResponseEntity.ok(new ApiResponse("updated", updatedCategory));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

}
