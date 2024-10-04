package com.kirasin.dto.orderedProduct;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.product.ProductReadDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderedProductReadDto {
    Long orderedProductId;
    OrderReadDto order;
    ProductReadDto product;
    Integer quantity;
}
