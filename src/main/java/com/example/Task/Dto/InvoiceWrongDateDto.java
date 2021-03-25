package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceWrongDateDto {
    private Long invoiceId;
    private String invoiceDate;
    private Long orderId;
    private String orderDate;
}
