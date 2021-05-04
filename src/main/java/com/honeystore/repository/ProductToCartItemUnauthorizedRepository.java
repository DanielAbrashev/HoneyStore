package com.honeystore.repository;

import com.honeystore.domain.CartItem;
import com.honeystore.domain.CartItemUnauthorized;
import com.honeystore.domain.ProductToCartItem;
import com.honeystore.domain.ProductToCartItemUnauthorized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductToCartItemUnauthorizedRepository extends JpaRepository<ProductToCartItemUnauthorized, Long> {

    void deleteByCartItemUnauthorized(CartItemUnauthorized cartItemUnauthorized);
}
