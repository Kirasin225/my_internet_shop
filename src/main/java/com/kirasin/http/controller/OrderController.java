package com.kirasin.http.controller;

import com.kirasin.dto.order.OrderCreateEditDto;
import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.service.impl.OrderServiceImpl;
import com.kirasin.service.impl.OrderedProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceImpl orderService;
    private final OrderedProductServiceImpl orderedProductService;


    @GetMapping("/{order_id}")
    public String findById(@PathVariable("order_id") Long orderId, Model model, @AuthenticationPrincipal UserDetails userDetails,
                           @ModelAttribute OrderedProductCreateDto orderedProductCreateDto) {
        return orderService.findById(orderId)
                .map(order -> {
                    model.addAttribute("order", order);
                    model.addAttribute("customer", userDetails);
                    model.addAttribute("orderedProducts", orderedProductService.findAllOrderedProductsByOrderId(orderId));
                    model.addAttribute("orderedProduct", orderedProductCreateDto);
                    return "order/orders";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping String createOrder(@ModelAttribute @Validated OrderCreateEditDto orderDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        return "redirect:/orders/" + orderService.createOrder(orderDto).getOrderId();
    }

    @DeleteMapping("/{order_placement_date}/delete")
    public String deleteByOrderDateBefore(@PathVariable("order_placement_date")LocalDate date) {
        if (!orderService.deleteByOrderDateBefore(date)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/orders";
    }


}
