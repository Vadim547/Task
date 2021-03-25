package com.example.Task.controller;

import com.example.Task.Dto.InvoiceIdDto;
import com.example.Task.Dto.PaymentByInvoiceDto;
import com.example.Task.Dto.PaymentDto;
import com.example.Task.model.Invoice;
import com.example.Task.model.Payment;
import com.example.Task.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<PaymentDto> addPayment(@RequestBody PaymentDto paymentDto) throws ParseException {
        return paymentService.addPayment(paymentDto);
    }

    @PostMapping("/get")
    public ResponseEntity<PaymentByInvoiceDto> findByInvoiceId(@RequestBody InvoiceIdDto invoiceIdDto) {
        return paymentService.findByInvoiceId(invoiceIdDto);
    }

    @GetMapping("/details")
    public ResponseEntity<PaymentDto> findById(@RequestParam Long id) {
        return paymentService.findById(id);
    }
}
