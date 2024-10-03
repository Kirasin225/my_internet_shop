package com.kirasin.mapper.product;

import com.kirasin.dto.product.ProductReadDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {
    @Override
    public ProductReadDto map(Product object) {
        return new ProductReadDto(
                object.getProductId(),
                object.getProductName(),
                object.getDescription(),
                object.getPrice(),
                object.getQuantity()
        );
    }
}
