package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBulkDto {
    private Long id;
    private Double price;

    public ProductBulkDto(Object[] objects) {
        this.id = (Long) objects[0];
        this.price = (Double) objects[1];
     }
}
