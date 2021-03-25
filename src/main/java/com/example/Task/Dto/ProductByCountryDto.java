package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductByCountryDto {
    private String country;
    private Long numberOfOrders;

    public ProductByCountryDto(Object[] objects) {
        this.country = objects[0].toString();
        this.numberOfOrders = (Long) objects[1];
    }
}
