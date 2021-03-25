package com.example.Task.model;

import com.example.Task.Dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "id_generator",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "customer")
    private List<Order> order;

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.name = customerDto.getName();
        this.country = customerDto.getCountry();
        this.address = customerDto.getAddress();
        this.phone = customerDto.getPhone();
    }
}
