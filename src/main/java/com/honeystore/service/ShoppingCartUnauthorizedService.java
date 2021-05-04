package com.honeystore.service;

import com.honeystore.domain.ShoppingCart;
import com.honeystore.domain.ShoppingCartUnauthorized;

public interface ShoppingCartUnauthorizedService {

    ShoppingCartUnauthorized updateShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized);

    void clearShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized);

}
