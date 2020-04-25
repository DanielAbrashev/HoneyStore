package com.honeystore.service;

import com.honeystore.domain.UserShipping;

import java.util.Optional;

public interface UserShippingService {

    UserShipping getOne(Long id);

    void removeById(Long shippingId);
}
