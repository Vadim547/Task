package com.example.Task.controller;

import com.example.Task.Dto.OrderDto;
import com.example.Task.Dto.OrderInvoiceDto;
import com.example.Task.Dto.OrderResponceDto;
import com.example.Task.Dto.OrderWithoutInvoceDto;
import com.example.Task.model.Order;
import com.example.Task.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.PushBuilder;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) throws ParseException {
        return orderService.addOrder(orderDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderDto>> getList() {
        return orderService.getAll();
    }

    @GetMapping("/orders_without_details")
    public ResponseEntity<List<OrderDto>> getWithoutDetails() {
        return orderService.orderWithoutDetails();
    }

    @PostMapping()
    public ResponseEntity<OrderResponceDto> addOrderAndInvoice(@RequestBody OrderInvoiceDto orderInvoiceDto) throws ParseException {
        return orderService.addOrderAndInvoice(orderInvoiceDto);
    }

    @GetMapping("/details")
    public ResponseEntity<OrderDto> findById(@RequestParam Long order_id) {
       return orderService.findById(order_id);
    }

    @GetMapping("/orders_without_invoices")
    public ResponseEntity<List<OrderWithoutInvoceDto>> ordersWithoutInvoice() {
        return orderService.withoutInvoice();
    }

}
