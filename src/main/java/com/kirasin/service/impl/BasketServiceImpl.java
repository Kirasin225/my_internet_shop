package com.kirasin.service.impl;

import com.kirasin.dto.order.OrderCreateEditDto;
import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.dto.product.ProductReadDto;
import com.kirasin.model.Basket;
import com.kirasin.model.OrderState;
import com.kirasin.service.BasketService;
import com.kirasin.service.OrderService;
import com.kirasin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final ProductService productService;
    private final OrderService orderService;

    private final Basket basket = new Basket();

    public List<OrderedProductReadDto> getBasketItems() {
        return basket.getItems();
    }

    @Override
    public void addToBasket(Long productId, Integer quantity) {
        // Retrieve ProductReadDto from ProductService
        ProductReadDto productDto = productService.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Add the item to the basket
        basket.addItem(productDto, quantity);
    }

    public void removeFromBasket(Long productId) {
        basket.removeItem(productId);
    }

    public void clearBasket() {
        basket.clearBasket();
    }

    @Override
    public OrderReadDto createOrder(Long customerId) {
        List<OrderedProductCreateDto> orderedProducts = basket.getItems().stream()
                .map(item -> new OrderedProductCreateDto(
                        null, // Order ID will be generated after saving the order
                        item.getProduct().getProductId(),
                        item.getQuantity()
                ))
                .toList();

        // Create the OrderCreateEditDto
        OrderCreateEditDto orderDto = OrderCreateEditDto.builder()
                .customerId(customerId)
                .orderState(OrderState.PENDING) // Set the initial order state
                .orderPlacementDate(LocalDate.now()) // Set the current date
                .orderClosingDate(null) // Assuming the order closing date is not set initially
                .build();

        // Create the order using OrderService
        orderService.createOrder(orderDto);

        // Clear the basket after checkout
        clearBasket();
        return null;
    }

    public Double getTotalPrice() {
        return basket.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

}
