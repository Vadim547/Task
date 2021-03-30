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

    @Query("select o from Order o\n" +
            "where o.id not in (\n" +
            "select o2.id from Order o2 join Invoice i2 on o2.id = i2.order.id )\n" +
            "order by o.id")
    List<Order> withoutInvoice();
}
