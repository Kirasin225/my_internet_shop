package com.kirasin.dto.orderedProduct;


import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class OrderedProductCreateDto {
    Long orderId;
    Long productId;
    @NotNull(message = "Quantity must not be empty")
    Integer quantity;
}
