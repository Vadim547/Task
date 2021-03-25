package com.example.Task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @SequenceGenerator(
            name = "categoryId_generator",
            sequenceName = "categoryId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "categoryId_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> product;

    public Category(String name) {
    }
}
