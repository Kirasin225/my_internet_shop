package com.kirasin.repository;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.model.OrderedProduct;

import java.util.List;

public interface BasketRepository {
    List<OrderedProductReadDto> getBasketItems();
    void addToBasket(Long productId, Integer quantity);
    void removeFromBasket(Long productId);
    double getTotalPrice();
    void clearBasket();
    OrderReadDto createOrder(Long customerId);
 }
