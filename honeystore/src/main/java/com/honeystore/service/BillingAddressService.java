package com.honeystore.service;

import com.honeystore.domain.BillingAddress;
import com.honeystore.domain.UserBilling;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
