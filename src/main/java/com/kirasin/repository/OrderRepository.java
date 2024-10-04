package com.kirasin.repository;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.model.Customer;
import com.kirasin.model.Order;
import com.kirasin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.orderPlacementDate < :date")
    boolean deleteOrderBeforeDate(@Param("date") LocalDate date);

    @Query("SELECT o FROM Order o JOIN o.orderedProducts op WHERE op.product.productId = :productId")
    List<Order> findOrdersByProductId(Long productId);
    @Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId")
    List<Order> findOrdersMadeByCustomer(Long customerId);
    @Query("SELECT o FROM Order o WHERE o.orderPlacementDate < :date")
    List<Order> findByOrderDateBefore(@Param("date") LocalDate localDate);
    Order findTopByOrderByOrderIdDesc();
}
