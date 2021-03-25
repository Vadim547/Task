package com.example.Task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "orderId_generator",
            sequenceName = "orderId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "orderId_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "cust_id", referencedColumnName = "id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<Detail> detail;
    @JsonIgnore
    @OneToOne(mappedBy = "order")
    private Invoice invoice;

    public Order(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }
}
