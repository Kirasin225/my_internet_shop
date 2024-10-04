package com.kirasin.http.controller;

import com.kirasin.service.impl.CustomerServiceImpl;
import com.kirasin.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private final OrderServiceImpl orderService;

    @GetMapping("/{customer_id}")
    public String findById(@PathVariable("customer_id") Long customerId, Model model) {
        return customerService.findById(customerId)
                .map(customer -> {
                    model.addAttribute("customer", customer);
                    model.addAttribute("orders", orderService.findOrdersMadeByCustomer(customerId));
                    return "customer/customer";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
