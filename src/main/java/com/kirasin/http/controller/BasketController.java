package com.kirasin.http.controller;

import com.kirasin.service.impl.BasketServiceImpl;
import com.kirasin.service.impl.OrderedProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/basket")
@AllArgsConstructor
public class BasketController {
    private final BasketServiceImpl basketService;
    private final OrderedProductServiceImpl orderedProductService;

    @GetMapping
    public String getBasketItems(Model model) {
        model.addAttribute("basketItems", basketService.getBasketItems());
        model.addAttribute("totalPrice", basketService.getTotalPrice());
        model.addAttribute("customerId", basketService.getCustomerId());
        return "basket/basket";  // The view name for displaying the basket
    }

    @PostMapping("/add")
    public String addToBasket(@RequestParam Long productId, @RequestParam Integer quantity) {
        basketService.addToBasket(productId, quantity);
        return "redirect:/basket";  // Redirect to basket after adding item
    }

    @GetMapping("/remove/{productId}")
    public String removeFromBasket(@PathVariable Long productId) {
        basketService.removeFromBasket(productId);
        return "redirect:/basket";  // Redirect to basket after removing item
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam Long customerId) {
        basketService.createOrder(customerId);
        return "redirect:/basket";  // Redirect after checkout
    }

    @PostMapping("/clear")
    public String clearBasket() {
        basketService.clearBasket();
        return "redirect:/basket";  // Redirect after clearing the basket
    }
}
