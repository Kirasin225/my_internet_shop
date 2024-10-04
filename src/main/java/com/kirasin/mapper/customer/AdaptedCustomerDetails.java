package com.kirasin.mapper.customer;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;


@Getter
public class AdaptedCustomerDetails extends User {
    public Long customerId;

    public AdaptedCustomerDetails(Long customerId, String email, String password, Collection<? extends GrantedAuthority> authorities){
        super(email, password, authorities);
        this.customerId = customerId;
    }
    public boolean hasRole(String role) {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(grantedRole -> grantedRole.equals(role));
    }

}
