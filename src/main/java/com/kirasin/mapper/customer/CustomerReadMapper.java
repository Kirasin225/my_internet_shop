package com.kirasin.mapper.customer;

import com.kirasin.dto.customer.CustomerReadDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerReadMapper implements Mapper<Customer, CustomerReadDto> {
    @Override
    public CustomerReadDto map(Customer object) {
        return new CustomerReadDto(
                object.getCustomerId(),
                object.getFirstName(),
                object.getLastName(),
                object.getEmail(),
                object.getPassword(),
                object.getPhoneNumber(),
                object.getAddress(),
                object.getRole()
        );
    }
}
