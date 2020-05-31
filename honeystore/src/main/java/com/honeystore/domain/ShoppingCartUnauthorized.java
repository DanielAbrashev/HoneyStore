/*
package com.honeystore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ShoppingCartUnauthorized {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal grandTotal;

    @OneToMany(mappedBy = "shoppingCartUnauthorized", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CartItemUnauthorized> cartItemListUnauthorized;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartItemUnauthorized> getCartItemListUnauthorized() {
        return cartItemListUnauthorized;
    }

    public void setCartItemListUnauthorized(List<CartItemUnauthorized> cartItemListUnauthorized) {
        this.cartItemListUnauthorized = cartItemListUnauthorized;
    }
}
*/
