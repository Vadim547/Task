package com.example.Task.service;

import com.example.Task.Dto.CustomerDto;
import com.example.Task.Dto.CustomerLastOrderDto;
import com.example.Task.exception.EntityException;
import com.example.Task.model.Customer;
import com.example.Task.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequestMapping("/customer")
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addNewStudent(Customer customer) {
       return customerRepository.save(customer);
    }

    public ResponseEntity<CustomerDto> findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new EntityException("Customer with "+ id + " does not exist");

        CustomerDto customerDto = new CustomerDto(customer.get());

        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public ResponseEntity<List<CustomerDto>> getCustomers (){

        List<CustomerDto> customers = customerRepository.findAll().stream()
                .map(CustomerDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    public ResponseEntity<List<CustomerDto>> findWithoutOrder() {
        List<Customer> customers = customerRepository.findWithoutOrder();
        List<CustomerDto> customerDtos = customers.stream()
                .map(CustomerDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    public ResponseEntity<List<CustomerLastOrderDto>> getLastOrders() {
        List<CustomerLastOrderDto> customers = customerRepository.findLastOrders().stream()
                .map(CustomerLastOrderDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
