package com.honeystore.service.impl;

import com.honeystore.domain.CartItem;
import com.honeystore.domain.CartItemUnauthorized;
import com.honeystore.domain.ShoppingCart;
import com.honeystore.domain.ShoppingCartUnauthorized;
import com.honeystore.repository.ShoppingCartRepository;
import com.honeystore.repository.ShoppingCartUnauthorizedRepository;
import com.honeystore.service.CartItemService;
import com.honeystore.service.CartItemUnauthorizedService;
import com.honeystore.service.ShoppingCartService;
import com.honeystore.service.ShoppingCartUnauthorizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartUnauthorizedServiceImpl implements ShoppingCartUnauthorizedService {

    @Autowired
    private CartItemUnauthorizedService cartItemUnauthorizedService;

    @Autowired
    private ShoppingCartUnauthorizedRepository shoppingCartUnauthorizedRepository;

    public ShoppingCartUnauthorized updateShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized) {
        BigDecimal cartTotal = new BigDecimal(0);
        List<CartItemUnauthorized> cartItemUnauthorizedList = cartItemUnauthorizedService.findByShoppingCartUnauthorized(shoppingCartUnauthorized);

        for (CartItemUnauthorized cartItemUnauthorized : cartItemUnauthorizedList) {
            if (cartItemUnauthorized.getProduct().getInStockNumber() > 0) {
                cartItemUnauthorizedService.updateCartItemUnauthorized(cartItemUnauthorized);
                cartTotal = cartTotal.add(cartItemUnauthorized.getSubtotal());
            }
        }

        shoppingCartUnauthorized.setGrandTotal(cartTotal);

        shoppingCartUnauthorizedRepository.save(shoppingCartUnauthorized);
        return shoppingCartUnauthorized;

    }

    public void clearShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized) {
        List<CartItemUnauthorized> cartItemUnauthorizedList = cartItemUnauthorizedService.findByShoppingCartUnauthorized(shoppingCartUnauthorized);

        for (CartItemUnauthorized cartItemUnauthorized : cartItemUnauthorizedList) {
            cartItemUnauthorized.setShoppingCartUnauthorized(null);
            cartItemUnauthorizedService.save(cartItemUnauthorized);
        }

        shoppingCartUnauthorized.setGrandTotal(new BigDecimal(0));

        shoppingCartUnauthorizedRepository.save(shoppingCartUnauthorized);
    }

}
