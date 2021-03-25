package com.example.Task.service;

import com.example.Task.Dto.InvoiceIdDto;
import com.example.Task.Dto.PaymentByInvoiceDto;
import com.example.Task.Dto.PaymentDto;
import com.example.Task.exception.EntityException;
import com.example.Task.model.Invoice;
import com.example.Task.model.Payment;
import com.example.Task.repository.InvoiceRepository;
import com.example.Task.repository.PaymentRepository;
import com.example.Task.staticClasses.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceService invoiceService;

    public PaymentService(PaymentRepository paymentRepository, InvoiceService invoiceService) {
        this.paymentRepository = paymentRepository;
        this.invoiceService = invoiceService;
    }

    public ResponseEntity<PaymentDto> addPayment(PaymentDto paymentDto) throws ParseException {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(paymentDto.getInvoiceId());
        if (invoice.isEmpty()) throw new EntityException("Invoice with " + paymentDto.getInvoiceId() +" is not found");

        SimpleDateFormat formater = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date date = formater.parse(paymentDto.getTimestamp());
        Timestamp timestamp = new Timestamp(date.getTime());

        Payment payment = new Payment(timestamp , paymentDto.getAmount(), invoice.get());

        paymentRepository.save(payment);

        paymentDto.setId(payment.getId());
        return new ResponseEntity<>(paymentDto, HttpStatus.OK);
    }

    public ResponseEntity<PaymentByInvoiceDto> findByInvoiceId(InvoiceIdDto invoiceIdDto) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceIdDto.getInvoiceId());
        if (invoice.isEmpty()) throw new EntityException("Invoice with " + invoiceIdDto.getInvoiceId()+ " not found");

        Timestamp currentTime = new Timestamp(new Date().getTime());
        Payment payment = new Payment(currentTime,invoice.get().getAmount(), invoice.get());
        paymentRepository.save(payment);

        PaymentDto paymentDto = new PaymentDto(payment);

        PaymentByInvoiceDto paymentByInvoiceDto = new PaymentByInvoiceDto(Status.SUCCESS(), paymentDto);

        return new ResponseEntity<>(paymentByInvoiceDto,HttpStatus.OK);
    }

    public ResponseEntity<PaymentDto> findById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isEmpty()) throw new EntityException("Payment with " + id + " not found");

        PaymentDto paymentDto = new PaymentDto(payment.get());

        return new ResponseEntity<>(paymentDto, HttpStatus.OK);
    }

}
