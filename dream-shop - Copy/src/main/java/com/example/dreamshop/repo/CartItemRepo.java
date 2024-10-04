package com.example.dreamshop.repo;

import com.example.dreamshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {
    void deleteAllByCartId(Long cartId);
}
