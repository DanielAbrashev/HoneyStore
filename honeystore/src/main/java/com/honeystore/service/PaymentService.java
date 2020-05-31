package com.honeystore.service;

import com.honeystore.domain.Payment;
import com.honeystore.domain.Product;
import com.honeystore.domain.UserPayment;

import java.util.List;

public interface PaymentService {

    Payment setByUserPayment(UserPayment userPayment, Payment payment);

}
