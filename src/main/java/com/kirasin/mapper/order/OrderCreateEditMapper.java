package com.kirasin.mapper.order;

import com.kirasin.dto.order.OrderCreateEditDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.model.Order;
import com.kirasin.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order> {
    private final CustomerRepository customerRepository;

    @Override
    public Order map(OrderCreateEditDto object) {
        return Order.builder()
                .orderId(object.getOrderId())
                .customer(
                        customerRepository
                                .findById(object.getCustomerId())
                                .orElseThrow()
                )
                .orderState(object.getOrderState())
                .orderPlacementDate(object.getOrderPlacementDate())
                .orderClosingDate(object.getOrderClosingDate())
                .build();
    }
}
