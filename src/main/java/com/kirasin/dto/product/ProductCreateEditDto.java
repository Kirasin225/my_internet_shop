package com.kirasin.dto.product;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ProductCreateEditDto {

    @NotBlank(message = "Product name must not be empty")
    String newProductName;

    @NotBlank(message = "Description must not be empty")
    String newDescription;

    @NotNull(message = "Price must not be empty")
    Double newPrice;

    @NotNull(message = "Quantity name must not be empty")
    Integer newQuantity;

    @JsonCreator
    public ProductCreateEditDto(
            @JsonProperty("newProductName") String newProductName,
            @JsonProperty("newDescription") String newDescription,
            @JsonProperty("newPrice") Double newPrice,
            @JsonProperty("newQuantity") Integer newQuantity) {
        this.newProductName = newProductName;
        this.newDescription = newDescription;
        this.newPrice = newPrice;
        this.newQuantity = newQuantity;
    }
}
