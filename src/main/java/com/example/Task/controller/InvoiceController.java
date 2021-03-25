package com.example.Task.controller;

import com.example.Task.Dto.InvoiceDto;
import com.example.Task.Dto.InvoiceWrongDateDto;
import com.example.Task.Dto.OverpaidInvoiceDto;
import com.example.Task.model.Invoice;
import com.example.Task.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/add")
    public ResponseEntity<InvoiceDto> addInvoice(@RequestBody InvoiceDto invoiceDto) throws ParseException {
        return invoiceService.add(invoiceDto);
    }
    @GetMapping("/expired_invoices")
    public ResponseEntity<List<InvoiceDto>> getExpired() {
        return invoiceService.getExpiredInvoice();
    }

    @GetMapping("/wrong_date_invoices")
    public List<InvoiceWrongDateDto> getWrongDateInvoices() {
        return invoiceService.wrongDateInvoices();
    }

    @GetMapping("/overpaid_invoices")
    public ResponseEntity<List<OverpaidInvoiceDto>> overpayedInvoices() {
        return invoiceService.overpayedInvoices();
    }

}
