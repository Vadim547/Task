package com.example.Task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @SequenceGenerator(
            name = "invoiceId_generator",
            sequenceName = "invoiceId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "invoiceId_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @OneToOne
    private Order order;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Date issued;
    @Column(nullable = false)
    private Date due;
    @OneToMany(mappedBy = "invoice")
    private List<Payment> payment;

    public Invoice(Order orderId, Double amount, Date issued, Date due) {
        this.order = orderId;
        this.amount = amount;
        this.issued = issued;
        this.due = due;
    }

}
