package com.adminportal.service.impl;

import com.adminportal.domain.*;
import com.adminportal.repository.OrderRepository;
import com.adminportal.service.CartItemService;
import com.adminportal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemService cartItemService;

    public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
                                          String shippingMethod, User user) {
        Order order = new Order();
        order.setOrderStatus("създадена");
        order.setShippingAddress(shippingAddress);
        order.setShippingMethod(shippingMethod);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            Product product = cartItem.getProduct();
            cartItem.setOrder(order);
            product.setInStockNumber(product.getInStockNumber() - cartItem.getQty());
        }

        order.setCartItemList(cartItemList);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getGrandTotal());
        shippingAddress.setOrder(order);
        order.setUser(user);
        order = orderRepository.save(order);
        return order;
    }

    public Order getOne(Long id) {
        return orderRepository.getOne(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
