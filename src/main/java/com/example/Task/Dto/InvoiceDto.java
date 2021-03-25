package com.example.Task.Dto;

import com.example.Task.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long id;
    private Long orderId;
    private Double amount;
    private String issued;
    private String due;

    public InvoiceDto(Invoice invoice) {
        this.id = invoice.getId();
        this.orderId = invoice.getOrder().getId();
        this.amount = invoice.getAmount();
        this.issued = invoice.getIssued().toString();
        this.due = invoice.getDue().toString();
    }
}
