package com.kirasin.dto.orderedProduct;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class OrderedProductCreateDto {
    @NotNull(message = "Order ID must not be empty")
    Long orderId;

    @NotNull(message = "Product ID must not be empty")
    Long productId;

    @NotNull(message = "Quantity must not be empty")
    Integer quantity;
}
