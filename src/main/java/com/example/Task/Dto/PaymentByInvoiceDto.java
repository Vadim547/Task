package com.example.Task.Dto;

import com.example.Task.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentByInvoiceDto {
    private String paymentStatus;
    private PaymentDto paymentDetails;
}
