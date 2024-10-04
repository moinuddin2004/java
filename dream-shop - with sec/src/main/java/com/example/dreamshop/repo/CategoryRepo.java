package com.example.dreamshop.repo;

import com.example.dreamshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByName(String name);

    boolean existsByName(String name);
}
