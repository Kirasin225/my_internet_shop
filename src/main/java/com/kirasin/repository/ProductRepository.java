package com.kirasin.repository;

import com.kirasin.dto.product.ProductReadDto;
import com.kirasin.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("DELETE FROM Product p WHERE p.productId = :id")
    void deleteProduct(@Param("id") Long id);

    Optional<Product> findProductByProductName(String productName);
}
