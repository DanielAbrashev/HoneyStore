package com.adminportal.service;

import com.adminportal.domain.Payment;
import com.adminportal.domain.UserPayment;

public interface PaymentService {

    Payment setByUserPayment(UserPayment userPayment, Payment payment);

}
