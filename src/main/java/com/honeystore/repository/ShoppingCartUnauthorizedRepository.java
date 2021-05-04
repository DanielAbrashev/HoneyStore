package com.honeystore.repository;

import com.honeystore.domain.ShoppingCart;
import com.honeystore.domain.ShoppingCartUnauthorized;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartUnauthorizedRepository extends CrudRepository<ShoppingCartUnauthorized, Long> {

}
