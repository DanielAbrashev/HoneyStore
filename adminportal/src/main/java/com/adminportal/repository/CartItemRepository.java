package com.adminportal.repository;

import com.adminportal.domain.CartItem;
import com.adminportal.domain.Order;
import com.adminportal.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<CartItem> findByOrder(Order order);

}
