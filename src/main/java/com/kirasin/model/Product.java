package com.kirasin.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    @Column(unique = true)
    private String productName;
    private String description;
    private Double price;
    private Integer quantity;

}
