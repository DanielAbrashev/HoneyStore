package com.honeystore.service;

import com.honeystore.domain.*;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
                      String shippingMethod, User user);

    Order getOne(Long id);
}
