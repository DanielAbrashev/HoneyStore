package com.honeystore.service.impl;

import com.honeystore.domain.ShippingAddress;
import com.honeystore.domain.UserShipping;
import com.honeystore.service.ShippingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {

        shippingAddress.setShippingAddressName(userShipping.getUserShippingAddressName());
        shippingAddress.setShippingAddressAddress(userShipping.getUserShippingAddressAddress());
        shippingAddress.setShippingAddressPhone(userShipping.getUserShippingAddressPhone());
        shippingAddress.setShippingAddressCity(userShipping.getUserShippingAddressCity());

        return shippingAddress;

    }

}
