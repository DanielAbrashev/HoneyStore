package com.honeystore.service.impl;

import com.honeystore.domain.*;
import com.honeystore.repository.CartItemRepository;
import com.honeystore.repository.CartItemUnauthorizedRepository;
import com.honeystore.repository.ProductToCartItemRepository;
import com.honeystore.repository.ProductToCartItemUnauthorizedRepository;
import com.honeystore.service.CartItemService;
import com.honeystore.service.CartItemUnauthorizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemUnauthorizedServiceImpl implements CartItemUnauthorizedService {

    @Autowired
    private CartItemUnauthorizedRepository cartItemUnauthorizedRepository;

    @Autowired
    private ProductToCartItemUnauthorizedRepository productToCartItemUnauthorizedRepository;


    public List<CartItemUnauthorized> findByShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized) {
        return cartItemUnauthorizedRepository.findByShoppingCartUnauthorized(shoppingCartUnauthorized);
    }

    public CartItemUnauthorized updateCartItemUnauthorized(CartItemUnauthorized cartItemUnauthorized) {
        BigDecimal bigDecimal = new BigDecimal(cartItemUnauthorized.getProduct().getOurPrice()).multiply(new BigDecimal(cartItemUnauthorized.getQty()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItemUnauthorized.setSubtotal(bigDecimal);

        cartItemUnauthorizedRepository.save(cartItemUnauthorized);
        return cartItemUnauthorized;
    }

    public void removeCartItemUnauthorized(CartItemUnauthorized cartItemUnauthorized) {
        productToCartItemUnauthorizedRepository.deleteByCartItemUnauthorized(cartItemUnauthorized);
        cartItemUnauthorizedRepository.delete(cartItemUnauthorized);
    }

    public CartItemUnauthorized addProductToCartItemUnauthorized(Product product, int qty, ShoppingCartUnauthorized shoppingCartUnauthorized) {
        List<CartItemUnauthorized> cartItemUnauthorizedList = findByShoppingCartUnauthorized(shoppingCartUnauthorized);

        for (CartItemUnauthorized cartItemUnauthorized : cartItemUnauthorizedList) {
            if (product.getId() == cartItemUnauthorized.getProduct().getId()) {
                cartItemUnauthorized.setQty(cartItemUnauthorized.getQty() + qty);
                cartItemUnauthorized.setSubtotal(new BigDecimal(product.getOurPrice()).multiply((new BigDecimal(qty))));
                cartItemUnauthorizedRepository.save(cartItemUnauthorized);
                return cartItemUnauthorized;
            }
        }

        CartItemUnauthorized cartItemUnauthorized = new CartItemUnauthorized();
        cartItemUnauthorized.setShoppingCartUnauthorized(shoppingCartUnauthorized);
        cartItemUnauthorized.setProduct(product);

        cartItemUnauthorized.setQty(qty);
        cartItemUnauthorized.setSubtotal(new BigDecimal(product.getOurPrice()).multiply((new BigDecimal(qty))));
        cartItemUnauthorized = cartItemUnauthorizedRepository.save(cartItemUnauthorized);

        ProductToCartItemUnauthorized productToCartItemUnauthorized = new ProductToCartItemUnauthorized();
        productToCartItemUnauthorized.setProduct(product);
        productToCartItemUnauthorized.setCartItemUnauthorized(cartItemUnauthorized);
        productToCartItemUnauthorizedRepository.save(productToCartItemUnauthorized);

        return cartItemUnauthorized;

    }

    public CartItemUnauthorized getOne(Long id) {
        return cartItemUnauthorizedRepository.getOne(id);
    }

    public CartItemUnauthorized save(CartItemUnauthorized cartItemUnauthorized) {
        return cartItemUnauthorizedRepository.save(cartItemUnauthorized);
    }

    public List<CartItemUnauthorized> findByOrderUnauthorized(OrderUnauthorized orderUnauthorized) {
        return cartItemUnauthorizedRepository.findByOrderUnauthorized(orderUnauthorized);
    }

}
