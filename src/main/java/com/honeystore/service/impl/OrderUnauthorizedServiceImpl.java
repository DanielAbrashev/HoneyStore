package com.honeystore.service.impl;

import com.honeystore.domain.*;
import com.honeystore.repository.OrderRepository;
import com.honeystore.repository.OrderUnauthorizedRepository;
import com.honeystore.service.CartItemService;
import com.honeystore.service.CartItemUnauthorizedService;
import com.honeystore.service.OrderUnauthorizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderUnauthorizedServiceImpl implements OrderUnauthorizedService {

    @Autowired
    private OrderUnauthorizedRepository orderUnauthorizedRepository;

    @Autowired
    private CartItemUnauthorizedService cartItemUnauthorizedService;

    public synchronized OrderUnauthorized createOrderUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized, ShippingAddress shippingAddress,
                                          String shippingMethod) {
        OrderUnauthorized orderUnauthorized = new OrderUnauthorized();
        orderUnauthorized.setOrderStatus("създадена");
        orderUnauthorized.setShippingAddress(shippingAddress);
        orderUnauthorized.setShippingMethod(shippingMethod);

        List<CartItemUnauthorized> cartItemUnauthorizedList = cartItemUnauthorizedService.findByShoppingCartUnauthorized(shoppingCartUnauthorized);

        for (CartItemUnauthorized cartItemUnauthorized : cartItemUnauthorizedList) {
            Product product = cartItemUnauthorized.getProduct();
            cartItemUnauthorized.setOrderUnauthorized(orderUnauthorized);
            product.setInStockNumber(product.getInStockNumber() - cartItemUnauthorized.getQty());
        }

        orderUnauthorized.setCartItemUnauthorizedList(cartItemUnauthorizedList);
        orderUnauthorized.setOrderDate(Calendar.getInstance().getTime());
        orderUnauthorized.setOrderTotal(shoppingCartUnauthorized.getGrandTotal());
        shippingAddress.setOrderUnauthorized(orderUnauthorized);
        orderUnauthorized = orderUnauthorizedRepository.save(orderUnauthorized);
        return orderUnauthorized;
    }

    public OrderUnauthorized getOne(Long id) {
        return orderUnauthorizedRepository.getOne(id);
    }
}
