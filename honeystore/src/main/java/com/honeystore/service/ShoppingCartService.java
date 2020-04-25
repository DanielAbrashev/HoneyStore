package com.honeystore.service;

import com.honeystore.domain.CartItem;
import com.honeystore.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    void clearShoppingCart(ShoppingCart shoppingCart);

}
