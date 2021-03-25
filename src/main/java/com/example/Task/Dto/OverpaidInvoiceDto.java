package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverpaidInvoiceDto {
    private Long invoiceId;
    private Double refund;

    public OverpaidInvoiceDto(Object[] objects) {
        this.invoiceId = (Long) objects[0];
        this.refund = (Double) objects[1];
    }
}
