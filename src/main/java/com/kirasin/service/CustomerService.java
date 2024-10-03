package com.kirasin.service;

import com.kirasin.dto.customer.CustomerCreateEditDto;
import com.kirasin.dto.customer.CustomerReadDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerReadDto> findAllCustomers();
    CustomerReadDto addCustomer(CustomerCreateEditDto customer);
    Optional<CustomerReadDto> updateCustomer(Long customerId, CustomerCreateEditDto customerDto);
    Optional<CustomerReadDto> findById(Long id);
    boolean deleteByEmail(String email);
}
