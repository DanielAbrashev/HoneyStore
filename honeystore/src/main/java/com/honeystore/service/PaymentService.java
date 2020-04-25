package com.honeystore.service;

import com.honeystore.domain.Payment;
import com.honeystore.domain.UserPayment;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
