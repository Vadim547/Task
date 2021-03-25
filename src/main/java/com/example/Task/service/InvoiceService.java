package com.example.Task.service;

import com.example.Task.Dto.InvoiceDto;
import com.example.Task.Dto.InvoiceWrongDateDto;
import com.example.Task.Dto.OverpaidInvoiceDto;
import com.example.Task.exception.EntityException;
import com.example.Task.model.Invoice;
import com.example.Task.model.Order;
import com.example.Task.repository.InvoiceRepository;
import com.example.Task.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
     private OrderRepository orderRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
    }

    public Optional<Invoice> getInvoiceById(Long id) {
       return invoiceRepository.findById(id);
    }

    public ResponseEntity<InvoiceDto> add(InvoiceDto invoiceDto) throws ParseException {
        Optional<Order> order = orderRepository.findById(invoiceDto.getOrderId());
        if (order.isEmpty()) throw new EntityException("Order with id "+ invoiceDto.getOrderId() + " does not exist");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date issuedParsed = format.parse(invoiceDto.getIssued());
        Date dueParsed = format.parse(invoiceDto.getDue());

        Invoice invoice = new Invoice(
                order.get(),
                invoiceDto.getAmount(),
                new java.sql.Timestamp(issuedParsed.getTime()),
                new java.sql.Timestamp(dueParsed.getTime()));
        invoiceRepository.save(invoice);

        invoiceDto.setId(invoice.getId());
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    public ResponseEntity<List<InvoiceDto>> getExpiredInvoice() {
        List<Invoice> expired = invoiceRepository.expiredInvoice();
        List<InvoiceDto> expiredDto = expired.stream()
                .map(InvoiceDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(expiredDto,HttpStatus.OK);
    }

    public List<InvoiceWrongDateDto> wrongDateInvoices() {
        List<Invoice> invoices = invoiceRepository.wrongInvoices();
        List<InvoiceWrongDateDto> invoiceDtos = invoices.stream()
                .map(i -> new InvoiceWrongDateDto(
                        i.getId(),
                        i.getIssued().toString(),
                        i.getOrder().getId(),
                        i.getOrder().getDate().toString()
                )).collect(Collectors.toList());
        return invoiceDtos;
    }

    public ResponseEntity<List<OverpaidInvoiceDto>> overpayedInvoices() {

        List<OverpaidInvoiceDto> overpaid =  invoiceRepository.findOverpaidInvoices().stream()
                .map(OverpaidInvoiceDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(overpaid, HttpStatus.OK);
    }

}
