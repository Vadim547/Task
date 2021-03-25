package com.example.Task.Dto;

import com.example.Task.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phone;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.country = customer.getCountry();
        this.address = customer.getAddress();
        this.phone = customer.getPhone();
    }

}
