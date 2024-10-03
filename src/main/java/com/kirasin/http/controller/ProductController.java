package com.kirasin.http.controller;

import com.kirasin.dto.order.OrderCreateEditDto;
import com.kirasin.dto.orderedProduct.OrderedProductCreateDto;
import com.kirasin.dto.product.ProductCreateEditDto;


import com.kirasin.mapper.customer.AdaptedCustomerDetails;
import com.kirasin.mapper.product.PageResponse;
import com.kirasin.service.impl.OrderServiceImpl;
import com.kirasin.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Pageable;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;

    @GetMapping
    public String findAll(Model model,
                          @ModelAttribute("newProduct") ProductCreateEditDto productCreateEditDto,
                          @AuthenticationPrincipal AdaptedCustomerDetails userDetails,
                          Authentication authentication) {
        model.addAttribute("products", productService.findAll());
        //model.addAttribute("products", PageResponse.of(productService.findAllProducts(pageable)));
        model.addAttribute("newProduct", productCreateEditDto);
        model.addAttribute("principal", userDetails);
        if (authentication != null) {
            model.addAttribute("principal", authentication.getPrincipal());
        }
        return "product/products";
    }

    @GetMapping("/{product_id}")
    public String findById(@PathVariable("product_id") Long productId,
                           Model model,
                           @AuthenticationPrincipal UserDetails userDetails,
                           @ModelAttribute OrderCreateEditDto orderCreateEditDto,
                           @ModelAttribute OrderedProductCreateDto orderedProductCreateDto) {
        return productService.findById(productId)
                .map(product -> {
                    model.addAttribute("product", product);
                    model.addAttribute("orders", orderService.findOrdersByProductId(product.getProductId()));
                    model.addAttribute("order", orderCreateEditDto);
                    model.addAttribute("user", userDetails);
                    return "product/product";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String addProduct(@ModelAttribute @Validated ProductCreateEditDto productDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newProduct", productDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/products/";
        }
        return "redirect:/products/" + productService.addProduct(productDto).getProductId();
    }

    @PostMapping("/{product_id}/update")
    public String updateProduct(@PathVariable("product_id") Long productId, @ModelAttribute ProductCreateEditDto product){
        return productService.updateProduct(productId, product)
                .map(it -> "redirect:/customers/{product_id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{product_id}/delete_product")
    public String deleteProduct(@PathVariable("product_id") Long id){
        if (!productService.deleteProductId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/products";
    }

}
