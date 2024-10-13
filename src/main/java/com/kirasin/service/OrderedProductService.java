package com.kirasin.service;

import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;

import java.util.List;
import java.util.Optional;


public interface OrderedProductService {
    OrderedProductReadDto createOrderedProduct(OrderedProductCreateDto orderedProduct);
    List<OrderedProductReadDto> findAllOrderedProductsByOrderId(Long orderId);
    Optional<OrderedProductReadDto> updateOrderedProduct(Long orderId, Long productId, OrderedProductCreateDto orderedProduct);
}
