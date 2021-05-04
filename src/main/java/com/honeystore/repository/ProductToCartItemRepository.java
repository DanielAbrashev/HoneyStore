package com.honeystore.repository;

import com.honeystore.domain.Product;
import com.honeystore.domain.ProductToCartItem;
import com.honeystore.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItem, Long> {

    void deleteByCartItem(CartItem cartItem);
}
