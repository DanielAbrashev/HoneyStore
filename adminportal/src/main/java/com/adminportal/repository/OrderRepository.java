package com.adminportal.repository;

import com.adminportal.domain.Order;
import com.adminportal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
    List<Order> findAll();
    Order save(Order order);



}
