package com.adminportal.service.impl;

import com.adminportal.domain.UserPayment;
import com.adminportal.repository.UserPaymentRepository;
import com.adminportal.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Override
    public UserPayment getOne(Long id) {
        return userPaymentRepository.getOne(id);
    }

    @Override
    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }
}
