package com.adminportal.service;

import com.adminportal.domain.ShippingAddress;
import com.adminportal.domain.UserShipping;

public interface ShippingAddressService {

    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
