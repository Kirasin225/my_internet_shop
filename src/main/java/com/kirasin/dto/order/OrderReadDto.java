package com.kirasin.dto.order;

import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.model.OrderState;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Getter
public class OrderReadDto {
    Long customerId;
    Long orderId;
    OrderState orderState;
    LocalDate orderPlacementDate;
    List<OrderedProductReadDto> orderedProducts;
}
