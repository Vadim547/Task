package com.example.Task.repository;

import com.example.Task.Dto.CustomerLastOrderDto;
import com.example.Task.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select distinct c2 from Customer c2 left join Order o2 on c2.id = o2.customer.id where o2.date < '2016-01-01' or o2.date > '2016-12-31'")
    List<Customer> findWithoutOrder();

    @Query("select c2.id, c2.name, max(o2.date) as last_order " +
            "from Customer c2 left join Order o2 on c2.id = o2.customer.id  " +
            "group by c2.id " +
            "HAVING MAX(o2.date) IS NOT null " +
            "order by c2.id asc")
    List<Object[]> findLastOrders();
}
