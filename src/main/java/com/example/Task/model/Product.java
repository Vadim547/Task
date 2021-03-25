package com.example.Task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @SequenceGenerator(
            name = "productId_generator",
            sequenceName = "productId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "productId_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    private String description;
    private Double price;
    private String photo;


    public Product(String name, Category category, String description, Double price, String photo) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }
}
