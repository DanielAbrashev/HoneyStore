package com.honeystore.service;

import com.honeystore.domain.UserPayment;

import java.util.Optional;

public interface UserPaymentService {

    UserPayment getOne(Long id);

    void removeById(Long creditCardId);
}
