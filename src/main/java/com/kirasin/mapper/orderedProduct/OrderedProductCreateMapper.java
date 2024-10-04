package com.kirasin.mapper.orderedProduct;

import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.model.OrderedProduct;
import com.kirasin.repository.OrderRepository;
import com.kirasin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderedProductCreateMapper implements Mapper<OrderedProductCreateDto, OrderedProduct> {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderedProduct map(OrderedProductCreateDto object) {
        var orderedProduct = new OrderedProduct();
        copy(object, orderedProduct);
        return orderedProduct;
    }

    @Override
    public OrderedProduct map(OrderedProductCreateDto fromObject, OrderedProduct toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public void copy(OrderedProductCreateDto productCreateDto, OrderedProduct orderedProduct) {
        orderedProduct.setOrder(
                orderRepository
                        .findById(productCreateDto.getOrderId())
                        .orElseThrow());
        orderedProduct.setProduct(
                productRepository
                        .findById(productCreateDto.getProductId())
                        .orElseThrow());
        orderedProduct.setQuantity(productCreateDto.getQuantity());
    }
}
