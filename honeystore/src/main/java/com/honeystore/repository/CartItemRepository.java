package com.honeystore.repository;

import com.honeystore.domain.CartItem;
import com.honeystore.domain.Order;
import com.honeystore.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
      List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) ;

      List<CartItem> findByOrder(Order order);


/*
      void removeCartItem(CartItem cartItem);
*/
}
