package com.kirasin.model;

import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.dto.product.ProductReadDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    private List<OrderedProductReadDto> items = new ArrayList<>();

    public void addItem(ProductReadDto productDto, Integer quantity) {
        if (items == null) {
            items = new ArrayList<>();
        }

        for (OrderedProductReadDto item : items) {
            if (item.getProduct().getProductId().equals(productDto.getProductId())) {

                item = OrderedProductReadDto.builder()
                        .order(item.getOrder())
                        .product(productDto)
                        .quantity(item.getQuantity() + quantity)
                        .build();
                return;
            }
        }

        OrderedProductReadDto newItem = OrderedProductReadDto.builder()
                .product(productDto)
                .quantity(quantity)
                .build();
        items.add(newItem);
    }

    public void removeItem(Long productId) {
        if (items != null) {
            items.removeIf(item -> item.getProduct().getProductId().equals(productId));
        }
    }

    public void clearBasket() {
        if (items != null) {
            items.clear();
        }
    }
}
