package com.kirasin.mapper.orderedProduct;

import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.mapper.order.OrderReadMapper;
import com.kirasin.mapper.product.ProductReadMapper;
import com.kirasin.model.OrderedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderedProductReadMapper implements Mapper<OrderedProduct, OrderedProductReadDto> {
    private final OrderReadMapper orderReadMapper;
    private final ProductReadMapper productReadMapper;
    @Override
    public OrderedProductReadDto map(OrderedProduct object) {
        return OrderedProductReadDto.builder()
                .order(orderReadMapper.map(object.getOrder()))
                .product(productReadMapper.map(object.getProduct()))
                .quantity(object.getQuantity())
                .build();
    }
}
