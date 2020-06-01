package com.adminportal.service;

import com.adminportal.domain.BillingAddress;
import com.adminportal.domain.UserBilling;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
