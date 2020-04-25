package com.honeystore.service;

import com.honeystore.domain.ShippingAddress;
import com.honeystore.domain.UserShipping;


public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
