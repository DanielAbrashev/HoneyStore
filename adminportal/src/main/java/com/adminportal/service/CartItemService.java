package com.adminportal.service;

import com.adminportal.domain.*;

import java.util.List;

public interface CartItemService {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);

    CartItem addProductToCartItem(Product product, User user, int qty);

    CartItem getOne(Long id);

    List<CartItem> findByOrder(Order order);

    void removeCartItem(CartItem cartItem);

    CartItem save(CartItem cartItem);
}
