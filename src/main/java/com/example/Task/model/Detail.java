package com.example.Task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Detail {
    @Id
    @SequenceGenerator(
            name = "detailId_generator",
            sequenceName = "detailId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "detailId_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    private Integer quantity;

    public Detail(Order order, Product product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
