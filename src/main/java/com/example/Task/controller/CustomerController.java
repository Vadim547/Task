package com.example.Task.controller;

import com.example.Task.Dto.CustomerDto;
import com.example.Task.Dto.CustomerLastOrderDto;
import com.example.Task.model.Customer;
import com.example.Task.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addNewStudent(customer);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> customerList() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers_without_orders")
    public ResponseEntity<List<CustomerDto>> getWithoutDate() {
        return customerService.findWithoutOrder();
    }

    @GetMapping("/customers_last_orders")
    public ResponseEntity<List<CustomerLastOrderDto>> getLastOrders() {
        return customerService.getLastOrders();
    }
}
