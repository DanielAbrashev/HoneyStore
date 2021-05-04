package com.honeystore.service;

import com.honeystore.domain.*;

public interface OrderUnauthorizedService {
    OrderUnauthorized createOrderUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized, ShippingAddress shippingAddress,
                      String shippingMethod);

    OrderUnauthorized getOne(Long id);
}
