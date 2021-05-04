package com.adminportal.service.impl;

import com.adminportal.domain.ShippingAddress;
import com.adminportal.domain.UserShipping;
import com.adminportal.service.ShippingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {

        shippingAddress.setShippingAddressName(userShipping.getUserShippingAddressName());
        shippingAddress.setShippingAddressPhone(userShipping.getUserShippingAddressPhone());

        return shippingAddress;

    }

}
