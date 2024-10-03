package com.kirasin.http.rest;

import com.kirasin.dto.product.ProductCreateEditDto;
import com.kirasin.dto.product.ProductReadDto;
import com.kirasin.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductServiceImpl productService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ProductReadDto> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{product_id}", produces = APPLICATION_JSON_VALUE)
    public ProductReadDto findById(@PathVariable("product_id") Long productId) {
        return productService.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReadDto addProduct(@RequestBody ProductCreateEditDto product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{product_id}")
    public ProductReadDto updateProduct(@PathVariable("product_id") Long productId, @RequestBody ProductCreateEditDto product) {
        return productService.updateProduct(productId, product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{product_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("product_id") Long productId) {
        if (!productService.deleteProductId(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
