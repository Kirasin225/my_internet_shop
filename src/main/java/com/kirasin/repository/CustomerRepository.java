package com.kirasin.repository;

import com.kirasin.dto.order.OrderReadDto;
import com.kirasin.model.Customer;
import com.kirasin.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CustomerRepository extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer>{

    Optional<Customer> findByEmail(String email);
}
