package com.honeystore.domain;

import javax.persistence.*;

@Entity
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shippingAddressName;
    private String shippingAddressPhone;
    private String shippingAddressAddress;
    private String shippingAddressCity;
    private String ekontCity;
    private String ekontAddress;

    @OneToOne
    private Order order;

    @OneToOne
    private OrderUnauthorized orderUnauthorized;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingAddressName() {
        return shippingAddressName;
    }

    public void setShippingAddressName(String shippingAddressName) {
        this.shippingAddressName = shippingAddressName;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getShippingAddressPhone() {
        return shippingAddressPhone;
    }

    public void setShippingAddressPhone(String shippingAddressPhone) {
        this.shippingAddressPhone = shippingAddressPhone;
    }

    public String getShippingAddressAddress() {
        return shippingAddressAddress;
    }

    public void setShippingAddressAddress(String shippingAddressAddress) {
        this.shippingAddressAddress = shippingAddressAddress;
    }

    public String getEkontCity() {
        return ekontCity;
    }

    public void setEkontCity(String ekontCity) {
        this.ekontCity = ekontCity;
    }

    public String getEkontAddress() {
        return ekontAddress;
    }

    public void setEkontAddress(String ekontAddress) {
        this.ekontAddress = ekontAddress;
    }

    public OrderUnauthorized getOrderUnauthorized() {
        return orderUnauthorized;
    }

    public void setOrderUnauthorized(OrderUnauthorized orderUnauthorized) {
        this.orderUnauthorized = orderUnauthorized;
    }
}
