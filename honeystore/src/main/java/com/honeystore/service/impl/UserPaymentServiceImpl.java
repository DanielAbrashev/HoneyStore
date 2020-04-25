package com.honeystore.service.impl;

import com.honeystore.domain.UserPayment;
import com.honeystore.repository.UserPaymentRepository;
import com.honeystore.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * created by saikat on 4/18/19
 */
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
