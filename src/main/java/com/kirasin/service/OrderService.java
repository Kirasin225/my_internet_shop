package com.kirasin.service;

import com.kirasin.dto.order.OrderCreateEditDto;
import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderReadDto createOrder(OrderCreateEditDto order);
    boolean deleteByOrderDateBefore(LocalDate date);
    List<OrderReadDto> findOrdersByProductId(Long productId);
    List<OrderReadDto> findOrdersMadeByCustomer(Long customerId);
    Optional<OrderReadDto> findById(Long id);
    OrderReadDto findTopByOrderByOrderPlacementDateDesc();
}
