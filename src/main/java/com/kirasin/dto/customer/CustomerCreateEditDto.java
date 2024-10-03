package com.kirasin.dto.customer;

import com.kirasin.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class CustomerCreateEditDto {
    @NotBlank(message = "Firstname must not be empty")
    String firstName;
    @NotBlank(message = "Lastname must not be empty")
    String lastName;
    @NotBlank(message = "Email must not be empty")
    String email;
    @NotBlank(message = "Phone number must not be empty")
    String phoneNumber;
    @NotBlank(message = "Password must not be empty")
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20")
    String rawPassword;
    @NotBlank (message = "Address must not be empty")
    String address;
    @NotNull(message = "Role must not be empty")
    Role role;
}
