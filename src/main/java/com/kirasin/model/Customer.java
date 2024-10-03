package com.kirasin.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NamedEntityGraph(
        name = "WithOrders",
        attributeNodes = {
                @NamedAttributeNode("orders")
        }
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "customers")
public class Customer{
    @Id
    @GeneratedValue
    private Long customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
    @Enumerated(EnumType.STRING)
    private Role role;
}
