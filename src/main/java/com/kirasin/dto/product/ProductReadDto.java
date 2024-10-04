package com.kirasin.dto.product;

import lombok.Value;


@Value
public class ProductReadDto {
    Long productId;
    String productName;
    String description;
    Double price;
    Integer quantity;
}
