package com.kirasin.http.controller;

import com.kirasin.dto.customer.CustomerCreateEditDto;
import com.kirasin.model.Role;
import com.kirasin.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginRegisterController {
    private final CustomerService customerService;

    @GetMapping("/login")
    public String loginPage() {
        return "customer/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model,
                                   @ModelAttribute("customer") CustomerCreateEditDto customerDto) {

        model.addAttribute("customer", customerDto);
        model.addAttribute("roles", Role.values());
        return "customer/registration";
    }

    @PostMapping("/registration")
    public String addCustomer(@ModelAttribute @Validated CustomerCreateEditDto customerDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customerDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }
        customerService.addCustomer(customerDto);
        customerService.addNoopPrefixToPassword(customerDto.getEmail());
        return "redirect:/login";
    }
}
