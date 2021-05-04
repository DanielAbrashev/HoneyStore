package com.adminportal.domain;

import javax.persistence.*;

@Entity
public class UserShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userShippingAddressName;
    private String userShippingAddressPhone;
    private String userShippingAddressAddress;
    private String userShippingAddressCity;
    private boolean userShippingDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserShippingAddressName() {
        return userShippingAddressName;
    }

    public void setUserShippingAddressName(String userShippingAddressName) {
        this.userShippingAddressName = userShippingAddressName;
    }

    public String getUserShippingAddressPhone() {
        return userShippingAddressPhone;
    }

    public void setUserShippingAddressPhone(String userShippingAddressPhone) {
        this.userShippingAddressPhone = userShippingAddressPhone;
    }

    public String getUserShippingAddressAddress() {
        return userShippingAddressAddress;
    }

    public void setUserShippingAddressAddress(String userShippingAddressAddress) {
        this.userShippingAddressAddress = userShippingAddressAddress;
    }

    public String getUserShippingAddressCity() {
        return userShippingAddressCity;
    }

    public void setUserShippingAddressCity(String userShippingAddressCity) {
        this.userShippingAddressCity = userShippingAddressCity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUserShippingDefault() {
        return userShippingDefault;
    }

    public void setUserShippingDefault(boolean userShippingDefault) {
        this.userShippingDefault = userShippingDefault;
    }
}
