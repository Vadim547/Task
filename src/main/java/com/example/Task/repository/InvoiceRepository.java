package com.example.Task.repository;

import com.example.Task.Dto.InvoiceDto;
import com.example.Task.model.Invoice;
import com.example.Task.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("from Invoice i  where i.issued > i.due")
    List<Invoice> expiredInvoice();

    @Query("from Invoice i where i.issued < i.order.date")
    List<Invoice> wrongInvoices();

    @Query("SELECT i2.id , sum(i2.amount) - i2.amount as refund " +
            "FROM Invoice i2 " +
            "JOIN Payment p2 " +
            "ON i2.id = p2.invoice.id " +
            "GROUP BY i2.id " +
            "HAVING count(i2.id) > 1 ")
    List<Object[]> findOverpaidInvoices();
}
