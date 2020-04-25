package com.honeystore.service.impl;

import com.honeystore.domain.UserShipping;
import com.honeystore.repository.UserShippingRepository;
import com.honeystore.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public UserShipping getOne(Long id) {
        return userShippingRepository.getOne(id);
    }

    @Override
    public void removeById(Long shippingId) {
        userShippingRepository.deleteById(shippingId);
    }
}
