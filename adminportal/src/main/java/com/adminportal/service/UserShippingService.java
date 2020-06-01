package com.adminportal.service;

import com.adminportal.domain.UserShipping;

public interface UserShippingService {

    UserShipping getOne(Long id);

    void removeById(Long shippingId);
}
