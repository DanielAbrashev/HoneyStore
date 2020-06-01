package com.adminportal.service;

import com.adminportal.domain.UserPayment;

public interface UserPaymentService {

    UserPayment getOne(Long id);

    void removeById(Long creditCardId);
}
