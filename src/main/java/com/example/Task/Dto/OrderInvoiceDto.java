package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInvoiceDto {
    private Long customerId;
    private Long productId;
    private Integer quantity;
}
