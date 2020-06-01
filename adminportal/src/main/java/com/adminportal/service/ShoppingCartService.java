package com.adminportal.service;

import com.adminportal.domain.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    void clearShoppingCart(ShoppingCart shoppingCart);

}
