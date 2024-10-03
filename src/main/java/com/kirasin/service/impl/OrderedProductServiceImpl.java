package com.kirasin.service.impl;

import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.mapper.orderedProduct.OrderedProductCreateMapper;
import com.kirasin.mapper.orderedProduct.OrderedProductReadMapper;
import com.kirasin.model.OrderedProduct;
import com.kirasin.repository.OrderedProductRepository;
import com.kirasin.service.OrderedProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderedProductServiceImpl implements OrderedProductService {
    private final OrderedProductRepository repository;
    private final OrderedProductCreateMapper productCreateMapper;
    private final OrderedProductReadMapper readMapper;

    @Override
    public OrderedProductReadDto createOrderedProduct(OrderedProductCreateDto orderedProduct) {
        return Optional.of(orderedProduct)
                .map(productCreateMapper::map)
                .map(repository::save)
                .map(readMapper::map)
                .orElseThrow();
    }

    @Override
    public List<OrderedProductReadDto> findAllOrderedProductsByOrderId(Long orderId) {
        return mapToDto(repository.findAllOrderedProductsByOrderId(orderId));
    }

    private List<OrderedProductReadDto> mapToDto(List<OrderedProduct> orderedProducts){
        return   orderedProducts.stream()
                .map(readMapper::map)
                .toList();
    }
}
