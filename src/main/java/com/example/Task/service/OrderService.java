package com.example.Task.service;

import com.example.Task.Dto.*;
import com.example.Task.exception.EntityException;
import com.example.Task.model.*;
import com.example.Task.repository.OrderRepository;
import com.example.Task.staticClasses.Status;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerService customerService;
    private DetailService detailService;
    private InvoiceService invoiceService;
    private ProductService productService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, DetailService detailService, InvoiceService invoiceService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.detailService = detailService;
        this.invoiceService = invoiceService;
        this.productService = productService;
    }

    public ResponseEntity<OrderDto> addOrder(OrderDto orderDto) throws ParseException {
        Optional<Customer> customer = customerService.getCustomerById(orderDto.getCust_id());
        if (customer.isEmpty())
            throw new EntityException("Customer with order id " + orderDto.getId() + "does not exist");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(orderDto.getDate());

        Order order = new Order(date, customer.get());
        orderRepository.save(order);

        orderDto.setId(order.getId());

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDto>> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getDate().toString(),
                        order.getCustomer().getId()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDto>> orderWithoutDetails() {
        List<Order> orders = orderRepository.withoutDetails();
        List<OrderDto> orderDtos = orders.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getDate().toString(),
                        order.getCustomer().getId()
                )).collect(Collectors.toList());

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    public ResponseEntity<OrderResponceDto> addOrderAndInvoice(OrderInvoiceDto orderInvoiceDto) throws ParseException {
        Optional<Customer> customer = customerService.getCustomerById(orderInvoiceDto.getCustomerId());
        if (customer.isEmpty())
            throw new EntityException("Customer with id " + orderInvoiceDto.getCustomerId() + " does not exist");

        Timestamp currentDate = new Timestamp(new Date().getTime());

        Order order = new Order(currentDate, customer.get());
        orderRepository.save(order);

        Optional<Product> product = productService.getProductById(orderInvoiceDto.getProductId());
        if (product.isEmpty())
            throw new EntityException("Product with id" + orderInvoiceDto.getProductId() + "does not exist");

        Detail detail = new Detail(order, product.get(), orderInvoiceDto.getQuantity());
        detailService.detailRepository.save(detail);

        Invoice invoice = new Invoice(order, product.get().getPrice() * orderInvoiceDto.getQuantity(), currentDate, nextMonthFromCurrent());
        InvoiceDto invoiceDto = new InvoiceDto(invoice);
        invoiceService.add(invoiceDto);

        OrderResponceDto orderResponceDto = new OrderResponceDto(Status.SUCCESS(), invoiceDto.getId());

        return new ResponseEntity<>(orderResponceDto, HttpStatus.OK);
    }

    public ResponseEntity<OrderDto> findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) throw new EntityException("Order with id " + id + " does not exist");

        OrderDto orderDto = new OrderDto(order.get());

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderWithoutInvoceDto>> withoutInvoice() {
        List<OrderWithoutInvoceDto> orders = orderRepository.withoutInvoice().stream()
                .map(order -> new OrderWithoutInvoceDto(
                        order.getId(),
                        order.getDate().toString(),
                        order.getDetail().stream().
                                map(Detail::getProduct)
                                .map(Product::getPrice).
                                reduce(Double.valueOf(0), Double::sum) * order.getDetail().stream().map(Detail::getQuantity).reduce(0, Integer::sum)
                ))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    private Timestamp nextMonthFromCurrent() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date nextMonth = cal.getTime();

        Timestamp timestamp = new Timestamp(nextMonth.getTime());

        return timestamp;
    }
}
