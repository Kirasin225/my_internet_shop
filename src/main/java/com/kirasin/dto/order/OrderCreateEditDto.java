package com.kirasin.dto.order;


import com.kirasin.model.OrderState;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

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
