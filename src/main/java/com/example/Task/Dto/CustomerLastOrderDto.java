package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLastOrderDto {
    private Long id;
    private String name;
    private String lastOrderDate;

    public CustomerLastOrderDto(Object[] objects) {
        this.id = (Long) objects[0];
        this.name = (String) objects[1];
        this.lastOrderDate = objects[2].toString();
    }
}
