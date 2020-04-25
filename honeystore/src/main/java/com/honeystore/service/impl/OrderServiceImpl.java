package com.honeystore.service.impl;

import com.honeystore.domain.*;
import com.honeystore.repository.OrderRepository;
import com.honeystore.service.CartItemService;
import com.honeystore.service.OrderService;
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
                      BillingAddress billingAddress, Payment payment,
                      String shippingMethod, User user){
            Order order=new Order();
            order.setBillingAddress(billingAddress);
            order.setOrderStatus("created");
            order.setPayment(payment);
            order.setShippingAddress(shippingAddress);
            order.setShippingMethod(shippingMethod);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

            for(CartItem cartItem:cartItemList){
                Product product = cartItem.getProduct();
                cartItem.setOrder(order);
                product.setInStockNumber(product.getInStockNumber() - cartItem.getQty());
            }

            order.setCartItemList(cartItemList);
            order.setOrderDate(Calendar.getInstance().getTime());
            order.setOrderTotal(shoppingCart.getGrandTotal());
            shippingAddress.setOrder(order);
            billingAddress.setOrder(order);
            payment.setOrder(order);
            order.setUser(user);
            order = orderRepository.save(order);
            return order;
   }

   public Order getOne(Long id){
        return orderRepository.getOne(id);
   }
}
