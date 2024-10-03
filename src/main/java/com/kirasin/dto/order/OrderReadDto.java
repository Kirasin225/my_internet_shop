package com.kirasin.dto.order;

import com.kirasin.model.OrderState;
import com.kirasin.model.OrderedProduct;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class OrderReadDto {
    Long customerId;
    Long orderId;
    OrderState orderState;
    LocalDate orderPlacementDate;
}
