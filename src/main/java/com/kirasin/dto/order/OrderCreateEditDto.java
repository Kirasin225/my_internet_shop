package com.kirasin.dto.order;

import com.kirasin.model.OrderState;
import com.kirasin.model.OrderedProduct;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class OrderCreateEditDto {
    Long orderId;
    Long customerId;
    @NotNull
    OrderState orderState;
    @NotNull
    LocalDate orderPlacementDate;
    LocalDate orderClosingDate;
}
