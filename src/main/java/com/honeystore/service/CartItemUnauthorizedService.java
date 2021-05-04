package com.honeystore.service;

import com.honeystore.domain.*;

import java.util.List;

public interface CartItemUnauthorizedService {

    List<CartItemUnauthorized> findByShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized);

    CartItemUnauthorized updateCartItemUnauthorized(CartItemUnauthorized cartItemUnauthorized);

    CartItemUnauthorized addProductToCartItemUnauthorized(Product product, int qty,ShoppingCartUnauthorized shoppingCartUnauthorized);

    CartItemUnauthorized getOne(Long id);

    List<CartItemUnauthorized> findByOrderUnauthorized(OrderUnauthorized orderUnauthorized);

    void removeCartItemUnauthorized(CartItemUnauthorized cartItemUnauthorized);

    CartItemUnauthorized save(CartItemUnauthorized cartItemUnauthorized);
}
