package com.honeystore.service;

import com.honeystore.domain.UserPayment;

import java.util.Optional;

/**
 * created by saikat on 4/18/19
 */
public interface UserPaymentService {


   UserPayment getOne(Long id);

    void removeById(Long creditCardId);
}
