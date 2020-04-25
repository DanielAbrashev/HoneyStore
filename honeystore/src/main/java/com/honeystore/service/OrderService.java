package com.honeystore.service;

import com.honeystore.domain.*;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
                      BillingAddress billingAddress, Payment payment,
                      String shippingMethod, User user);

    Order getOne(Long id);
}
