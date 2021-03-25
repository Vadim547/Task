package com.example.Task.repository;

import com.example.Task.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p.id , sum(d.quantity) from Product p join Detail d on p.id = d.product.id group by p.id having sum(d.quantity) > 10")
    List<Object[]> findDemandProducts();

    @Query("select p.id, p.price from Product p  join Detail d on p.id = d.product.id group by p.id having avg(d.quantity)>= 8")
    List<Object[]> findBulkProducts();

    @Query("SELECT c.country, count(c.country) as order_numbers\n" +
            "FROM Detail d \n" +
            "   JOIN\n" +
            "   Order o\n" +
            "   ON d.order.id = o.id\n" +
            "   JOIN\n" +
            "   Customer c \n" +
            "   ON o.customer.id = c.id\n" +
            "   where o.date > '2016-01-01' and o.date < '2016-12-31'\n" +
            "   group by c.country")
    List<Object[]> findOrdersByCountry();
}
