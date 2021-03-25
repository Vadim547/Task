package com.example.Task.repository;

import com.example.Task.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order r join Detail d on r.id = d.order.id and r.date < '2016-09-06' ")
    List<Order> withoutDetails();

    @Query("select r.id, r.date from Order r join Invoice i on r.id = i.order.id ")
    List<Object[]> withoutInvoice();
}
