package com.kirasin.service.impl;

import com.kirasin.dto.customer.CustomerCreateEditDto;
import com.kirasin.dto.customer.CustomerReadDto;
import com.kirasin.mapper.customer.AdaptedCustomerDetails;
import com.kirasin.mapper.customer.CustomerCreateEditMapper;
import com.kirasin.mapper.customer.CustomerReadMapper;
import com.kirasin.model.Customer;
import com.kirasin.repository.CustomerRepository;
import com.kirasin.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private final CustomerRepository repository;
    private final CustomerReadMapper readMapper;
    private final CustomerCreateEditMapper createEditMapper;
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerReadDto> findAllCustomers() {
        return repository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    @Override
    public CustomerReadDto addCustomer(CustomerCreateEditDto customer) {
        return Optional.of(customer)
                .map(createEditMapper::map)
                .map(repository::save)
                .map(readMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<CustomerReadDto> updateCustomer(Long customerId, CustomerCreateEditDto customerDto) {
        return repository.findById(customerId)
                .map(entity -> createEditMapper.map(customerDto, entity))
                .map(repository::saveAndFlush)
                .map(readMapper::map);
    }
    @Override
    public Optional<CustomerReadDto> findById(Long id) {
        return repository.findById(id)
                .map(readMapper::map);
    }

    @Override
    public boolean deleteByEmail(String email) {
        return repository.findByEmail(email)
                .map(entity -> {
                        repository.delete(entity);
                        repository.flush();
                        return true;
                    }
                ).orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(customer -> new AdaptedCustomerDetails(
                        customer.getCustomerId(),
                        customer.getEmail(),
                        customer.getPassword(),
                        Collections.singleton(customer.getRole())
                )).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
    public void addNoopPrefixToPassword(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        Customer originalCustomer = customer.get();
        String password = originalCustomer.getPassword();
        originalCustomer.setPassword("{noop}" + password);
        customerRepository.save(originalCustomer);
    }
}
