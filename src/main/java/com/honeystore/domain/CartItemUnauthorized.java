package com.honeystore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class CartItemUnauthorized {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int qty;
    private BigDecimal subtotal;

    @OneToOne
    private Product product;

    @OneToMany(mappedBy = "cartItemUnauthorized")
    @JsonIgnore
    private List<ProductToCartItemUnauthorized> productToCartItemUnauthorizedList;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_unauthorized_id")
    private ShoppingCartUnauthorized shoppingCartUnauthorized;

    @ManyToOne
    @JoinColumn(name = "order_unauthorized_id")
    private OrderUnauthorized orderUnauthorized;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductToCartItemUnauthorized> getProductToCartItemUnauthorizedList() {
        return productToCartItemUnauthorizedList;
    }

    public void setProductToCartItemUnauthorizedList(List<ProductToCartItemUnauthorized> productToCartItemUnauthorizedList) {
        this.productToCartItemUnauthorizedList = productToCartItemUnauthorizedList;
    }

    public OrderUnauthorized getOrderUnauthorized() {
        return orderUnauthorized;
    }

    public void setOrderUnauthorized(OrderUnauthorized orderUnauthorized) {
        this.orderUnauthorized = orderUnauthorized;
    }

    public ShoppingCartUnauthorized getShoppingCartUnauthorized() {
        return shoppingCartUnauthorized;
    }

    public void setShoppingCartUnauthorized(ShoppingCartUnauthorized shoppingCartUnauthorized) {
        this.shoppingCartUnauthorized = shoppingCartUnauthorized;
    }
}
