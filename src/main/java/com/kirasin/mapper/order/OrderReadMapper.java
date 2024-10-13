package com.kirasin.mapper.order;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.mapper.customer.CustomerReadMapper;
import com.kirasin.mapper.orderedProduct.OrderedProductReadMapper;
import com.kirasin.model.Order;
import com.kirasin.model.OrderedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {
    private final CustomerReadMapper customerReadMapper;
    private final OrderedProductReadMapper orderedProductReadMapper;

    @Override
    public OrderReadDto map(Order object) {
        return new OrderReadDto(
                customerReadMapper.map(object.getCustomer()).getCustomerId(),
                object.getOrderId(),
                object.getOrderState(),
                object.getOrderPlacementDate(),
                getOrderedProducts(object.getOrderedProducts())
        );
    }

    private List<OrderedProductReadDto> getOrderedProducts(List<OrderedProduct> orderedProducts) {
        if (orderedProducts == null || orderedProducts.isEmpty()) {
            return Collections.emptyList();
        }
        return orderedProducts.stream().map(orderedProductReadMapper::map).toList();
    }
}
