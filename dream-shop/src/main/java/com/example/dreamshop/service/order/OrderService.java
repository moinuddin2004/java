package com.example.dreamshop.service.order;

import com.example.dreamshop.dto.OrderDto;
import com.example.dreamshop.enums.OrderStatus;
import com.example.dreamshop.exception.ResourceNotFoundEXception;
import com.example.dreamshop.model.Cart;
import com.example.dreamshop.model.Order;
import com.example.dreamshop.model.OrderItem;
import com.example.dreamshop.model.Product;
import com.example.dreamshop.repo.OrderRepo;
import com.example.dreamshop.repo.ProductRepo;
import com.example.dreamshop.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
//import sun.security.krb5.internal.ccache.MemoryCredentialsCache;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final ICartService cartService;
private  final ModelMapper modelMapper;
    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepo.findById(orderId)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundEXception("order not found"));
    }
    @Override
    public List<OrderDto> getUserOrder(Long userId){
        return  orderRepo.findByUserId(userId).stream().map( this :: convertToDto).toList();
    }
    @Override
    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItems = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItems));
        order.setTotalAmount(calculateTotalAmount(orderItems));
        Order savedOrder = orderRepo.save(order);
        cartService.clearCart(cart.getId());
        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
//            MemoryCredentialsCache productRepository;

            productRepo.save(product);
            return new OrderItem(
                    order,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice());
        }).toList();

    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList
                .stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
