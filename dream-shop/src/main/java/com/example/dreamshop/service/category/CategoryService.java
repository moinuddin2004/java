package com.example.dreamshop.service.category;

import com.example.dreamshop.exception.AlreadyExistsException;
import com.example.dreamshop.exception.CategoryNotFoundException;
import com.example.dreamshop.model.Category;
import com.example.dreamshop.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public Category getCategoryById(Long Id) {
        return categoryRepo.findById(Id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
//    public Category addCategory(Category category) {
//        return Optional.of(category).filter(c-> !categoryRepo.existsByName(c.getName()))
//                .map(categoryRepo:: save).orElseThrow(()->new AlreadyExistsException(category.getName()+ "already exists"));
//    }
    public Category addCategory(Category category) {
        return Optional.ofNullable(categoryRepo.findByName(category.getName()))
                .orElseGet(() -> categoryRepo.save(category));
    }


    @Override
    public Category updateCategory(Category category,Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepo.save(oldCategory);
                }).orElseThrow(()->new CategoryNotFoundException("category not found"));
    }

    @Override
    public void deleteCategoryById(Long Id) {
//        categoryRepo.deleteById(Id);
        categoryRepo.findById(Id).ifPresentOrElse(categoryRepo::delete, () -> {
            throw new CategoryNotFoundException("category not found");
        });
    }

}
