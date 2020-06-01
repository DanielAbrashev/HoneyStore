package com.adminportal.repository;

import com.adminportal.domain.CartItem;
import com.adminportal.domain.ProductToCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductToCartItemRepository extends JpaRepository<ProductToCartItem, Long> {

    void deleteByCartItem(CartItem cartItem);
}
