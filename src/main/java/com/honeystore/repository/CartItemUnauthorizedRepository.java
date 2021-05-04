package com.honeystore.repository;

import com.honeystore.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartItemUnauthorizedRepository extends JpaRepository<CartItemUnauthorized, Long> {
    List<CartItemUnauthorized> findByShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized);

    List<CartItemUnauthorized> findByOrderUnauthorized(OrderUnauthorized orderUnauthorized);

}
