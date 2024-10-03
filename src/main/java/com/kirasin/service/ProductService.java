package com.kirasin.service;

import com.kirasin.dto.product.ProductCreateEditDto;
import com.kirasin.dto.product.ProductReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductReadDto> findAll();
    Page<ProductReadDto> findAllProducts(Pageable pageable);
    ProductReadDto addProduct(ProductCreateEditDto product);
    Optional<ProductReadDto> updateProduct(Long productId, ProductCreateEditDto product);
    Optional<ProductReadDto> findProductByProductName(String productName);
    boolean deleteProductId(Long id);
    Optional<ProductReadDto> findById(Long productId);
}
