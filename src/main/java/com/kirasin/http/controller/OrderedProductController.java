package com.kirasin.http.controller;

import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.service.impl.OrderedProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ordered_products")
public class OrderedProductController {
    private final OrderedProductServiceImpl orderedProductService;
    @PostMapping
    public String createOrderedProduct(@ModelAttribute @Validated OrderedProductCreateDto orderedProductCreateDto,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderedProduct", orderedProductCreateDto);
            redirectAttributes.addFlashAttribute("orderedProductErrors");
            return "redirect:/products/" + orderedProductCreateDto.getProductId();
        }
        return "redirect:/products/" + orderedProductService.createOrderedProduct(orderedProductCreateDto).getProduct().getProductId();
    }
}
