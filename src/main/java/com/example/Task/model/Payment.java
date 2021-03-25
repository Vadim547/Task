package com.example.Task.model;

import com.example.Task.Dto.PaymentDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @SequenceGenerator(
            name = "paymentId_generator",
            sequenceName = "paymentId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "paymentId_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(nullable = false)
    private Timestamp timestamp;
    @Column(nullable = false)
    private Double amount;
    @ManyToOne
    private Invoice invoice;


    public Payment(Timestamp timestamp, Double amount, Invoice invoice) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.invoice = invoice;
    }
}
