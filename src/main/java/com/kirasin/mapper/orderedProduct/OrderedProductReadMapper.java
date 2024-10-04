package com.kirasin.mapper.orderedProduct;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.mapper.product.ProductReadMapper;
import com.kirasin.model.Order;
import com.kirasin.model.OrderedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class OrderedProductReadMapper implements Mapper<OrderedProduct, OrderedProductReadDto> {
    private final ProductReadMapper productReadMapper;
    @Override
    public OrderedProductReadDto map(OrderedProduct object) {
        return OrderedProductReadDto.builder()
                .orderedProductId(object.getOrderedProductId())
                .order(mapOrderToDto(object.getOrder()))
                .product(productReadMapper.map(object.getProduct()))
                .quantity(object.getQuantity())
                .build();
    }
    private OrderReadDto mapOrderToDto(Order object) {
        return new OrderReadDto(
                object.getCustomer().getCustomerId(),
                object.getOrderId(),
                object.getOrderState(),
                object.getOrderPlacementDate(),
                Collections.emptyList()
        );
    }
}
