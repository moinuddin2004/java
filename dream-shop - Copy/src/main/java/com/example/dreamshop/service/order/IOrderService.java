package com.example.dreamshop.service.order;

import com.example.dreamshop.dto.OrderDto;
import com.example.dreamshop.model.Order;

import java.util.List;

public interface IOrderService {
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrder(Long userId);

    OrderDto convertToDto(Order order);

    Order placeOrder(Long userId);
}
