package com.kirasin.mapper.order;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.mapper.customer.CustomerReadMapper;
import com.kirasin.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {
    private final CustomerReadMapper customerReadMapper;

    @Override
    public OrderReadDto map(Order object) {
        return new OrderReadDto(
                customerReadMapper.map(object.getCustomer()).getCustomerId(),
                object.getOrderId(),
                object.getOrderState(),
                object.getOrderPlacementDate()
        );
    }
}
