package com.kirasin.dto.product;

import com.kirasin.model.Order;
import lombok.Value;

import java.util.List;

@Value
public class ProductReadDto {
    Long productId;
    String productName;
    String description;
    Double price;
    Integer quantity;

}
