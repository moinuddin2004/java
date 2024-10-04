package com.example.dreamshop.repo;

import com.example.dreamshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long userId);
}
