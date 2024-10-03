package com.kirasin.dto.customer;

import com.kirasin.model.Role;
import lombok.Value;

@Value
public class CustomerReadDto {
    Long customerId;
    String firstName;
    String SecondName;
    String email;
    String password;
    String phoneNumber;
    String address;
    Role role;
}
