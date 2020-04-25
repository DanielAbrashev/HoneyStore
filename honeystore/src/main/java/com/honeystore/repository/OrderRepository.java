package com.honeystore.repository;

import com.honeystore.domain.Order;
import com.honeystore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

}
