package com.example.Task.Dto;

import com.example.Task.model.Payment;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;
    private String timestamp;
    private Double amount;
    private Long invoiceId;

    public PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.timestamp = payment.getTimestamp().toString();
        this.amount = payment.getAmount();;
        this.invoiceId = payment.getInvoice().getId();
    }
}
