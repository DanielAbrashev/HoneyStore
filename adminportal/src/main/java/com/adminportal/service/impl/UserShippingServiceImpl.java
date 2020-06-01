package com.adminportal.service.impl;

import com.adminportal.domain.UserShipping;
import com.adminportal.repository.UserShippingRepository;
import com.adminportal.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
