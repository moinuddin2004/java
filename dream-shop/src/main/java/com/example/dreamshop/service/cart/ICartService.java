package com.example.dreamshop.service.cart;

import com.example.dreamshop.dto.CartDto;
import com.example.dreamshop.model.Cart;
import com.example.dreamshop.model.User;

import java.math.BigDecimal;

public interface ICartService {



    Cart getCart(Long cartId);

    void clearCart(Long cartId);

    BigDecimal getTotalPrice(Long cartId);



    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);

    CartDto convertToDto(Cart cart);
}
