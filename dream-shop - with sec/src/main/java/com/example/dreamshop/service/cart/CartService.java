package com.example.dreamshop.service.cart;

import com.example.dreamshop.dto.CartDto;
import com.example.dreamshop.exception.ResourceNotFoundEXception;
import com.example.dreamshop.model.Cart;
import com.example.dreamshop.model.CartItem;
import com.example.dreamshop.model.User;
import com.example.dreamshop.repo.CartItemRepo;
import com.example.dreamshop.repo.CartRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    private final ModelMapper modelMapper;
private  final AtomicLong ca = new AtomicLong(0);


    @Override
    public Cart getCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundEXception("cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepo.save(cart);
    }
@Transactional
    @Override
    public void clearCart(Long cartId) {
        Cart cart = getCart(cartId);
        cartItemRepo.deleteAllByCartId(cartId);
        cart.getItems().clear();
        cartRepo.deleteById(cartId);

    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        Cart cart = getCart(cartId);
        return cart.getItems().stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public Cart initializeNewCart(User user) {
       return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepo.save(cart);

                });
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepo.findByUserId(userId);
    }
    @Override
    public CartDto convertToDto(Cart cart){
        return modelMapper.map(cart, CartDto.class);
    }
}
