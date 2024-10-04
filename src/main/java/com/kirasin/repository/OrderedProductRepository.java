package com.kirasin.repository;

import com.kirasin.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;


public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long>, QuerydslPredicateExecutor<OrderedProduct> {
    @Query("SELECT op FROM OrderedProduct op WHERE op.order.orderId = :orderId")
    List<OrderedProduct> findAllOrderedProductsByOrderId(Long orderId);
    @Query("SELECT op FROM OrderedProduct op WHERE op.order.orderId = :orderId AND op.product.productId = :productId")
    Optional<OrderedProduct> findByOrderIdAndProductId(Long orderId, Long productId);
}
