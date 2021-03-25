package com.example.Task.service;

import com.example.Task.Dto.DetailDto;
import com.example.Task.exception.EntityException;
import com.example.Task.model.Detail;
import com.example.Task.model.Order;
import com.example.Task.model.Product;
import com.example.Task.repository.DetailRepository;
import com.example.Task.repository.OrderRepository;
import com.example.Task.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    DetailRepository detailRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;

    public DetailService(DetailRepository detailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.detailRepository = detailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<DetailDto> addDetail(DetailDto detailDto) {
        Optional<Order> order = orderRepository.findById(detailDto.getOrderId());
        if(order.isEmpty()) throw new EntityException("Order with "+ detailDto.getOrderId() + " does not exist");

        Optional<Product> product = productRepository.findById(detailDto.getProductId());
        if (product.isEmpty()) throw new EntityException("Product with " + detailDto.getProductId() +" does not exist");

        Detail detail = new Detail(order.get(), product.get(), detailDto.getQuantity());
        detailRepository.save(detail);

        detailDto.setId(detail.getId());
        return new ResponseEntity<>(detailDto, HttpStatus.OK);
    }


}
