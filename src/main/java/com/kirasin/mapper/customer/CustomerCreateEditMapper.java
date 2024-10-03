package com.kirasin.mapper.customer;

import com.kirasin.dto.customer.CustomerCreateEditDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerCreateEditMapper implements Mapper<CustomerCreateEditDto, Customer> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public Customer map(CustomerCreateEditDto object) {
        var customer = new Customer();
        copy(object, customer);
        return customer;
    }

    @Override
    public Customer map(CustomerCreateEditDto fromObject, Customer toObject) {

        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CustomerCreateEditDto customer, Customer entity){
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());
        entity.setPhoneNumber(customer.getPhoneNumber());

        Optional.ofNullable(customer.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(entity::setPassword);

        entity.setPassword(customer.getRawPassword());
        entity.setAddress(customer.getAddress());
        entity.setRole(customer.getRole());
    }
}
