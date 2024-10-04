package com.kirasin.service;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;

import java.util.List;

public interface BasketService {
    List<OrderedProductReadDto> getBasketItems();
    void addToBasket(Long productId, Integer quantity);
    void removeFromBasket(Long productId);
    Double getTotalPrice();
    void clearBasket();
    OrderReadDto createOrder(Long customerId);
    Long getCustomerId();
}
