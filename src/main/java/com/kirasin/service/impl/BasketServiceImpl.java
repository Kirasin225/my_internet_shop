package com.kirasin.service.impl;

import com.kirasin.dto.order.OrderCreateEditDto;
import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.dto.orderedProduct.OrderedProductReadDto;
import com.kirasin.dto.product.ProductReadDto;
import com.kirasin.mapper.customer.AdaptedCustomerDetails;
import com.kirasin.model.Basket;
import com.kirasin.model.OrderState;
import com.kirasin.service.BasketService;
import com.kirasin.service.OrderService;
import com.kirasin.service.OrderedProductService;
import com.kirasin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderedProductService orderedProductService;

    private final Basket basket = new Basket();

    @Override
    public List<OrderedProductReadDto> getBasketItems() {
        return basket.getItems();
    }

    @Override
    public Long getCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof AdaptedCustomerDetails) {
                return ((AdaptedCustomerDetails) principal).getCustomerId();
            }
        }
        return null; // or throw an exception if user is not authenticated
    }

    @Override
    public void addToBasket(Long productId, Integer quantity) {
        // Retrieve ProductReadDto from ProductService
        ProductReadDto productDto = productService.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity > productDto.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock");
        }
        // Add the item to the basket
        basket.addItem(productDto, quantity);
    }

    @Override
    public void removeFromBasket(Long productId) {
        basket.removeItem(productId);
    }

    @Override
    public void clearBasket() {
        basket.clearBasket();
    }

    @Override
    public OrderReadDto createOrder(Long customerId) {


        // Create the OrderCreateEditDto
        OrderCreateEditDto orderDto = OrderCreateEditDto.builder()
                .customerId(customerId)
                .orderState(OrderState.PENDING) // Set the initial order state
                .orderPlacementDate(LocalDate.now()) // Set the current date
                .orderClosingDate(null) // Assuming the order closing date is not set initially
                .build();

        // Create the order using OrderService
        orderService.createOrder(orderDto);

        basket.getItems().forEach(item -> {
            productService.decreaseProductQuantity(item.getProduct().getProductId(), item.getQuantity());
        });
        List<OrderedProductCreateDto> orderedProducts = basket.getItems().stream()
                .map(item -> new OrderedProductCreateDto(
                        orderService.findTopByOrderByOrderPlacementDateDesc().getOrderId(), // Order ID will be generated after saving the order
                        item.getProduct().getProductId(),
                        item.getQuantity()
                ))
                .toList();
        orderedProducts.forEach(orderedProductService::createOrderedProduct);

        // Clear the basket after checkout
        clearBasket();
        return null;
    }


    @Override
    public Double getTotalPrice() {
        if (basket.getItems() == null || basket.getItems().isEmpty()) {
            return 0.0; // Return a default value when the basket is empty
        }

        // Proceed to calculate the total price if items exist
        return basket.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()) // Assumes price and quantity are Double
                .sum();
    }

}
