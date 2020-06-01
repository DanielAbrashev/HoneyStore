package com.adminportal.service;

import com.adminportal.domain.*;

import java.util.List;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
                      BillingAddress billingAddress, Payment payment,
                      String shippingMethod, User user);

    Order getOne(Long id);

    List<Order> findAll();

    Order save(Order order);

}
