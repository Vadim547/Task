package com.example.Task.Dto;

import com.example.Task.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String date;
    private Long cust_id;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate().toString();
        this.cust_id = order.getCustomer().getId();
    }
}
