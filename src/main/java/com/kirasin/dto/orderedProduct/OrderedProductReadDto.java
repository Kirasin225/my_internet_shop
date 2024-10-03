package com.kirasin.dto.orderedProduct;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.product.ProductReadDto;
import com.kirasin.model.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderedProductReadDto {
    OrderReadDto order;
    ProductReadDto product;
    Integer quantity;
}
