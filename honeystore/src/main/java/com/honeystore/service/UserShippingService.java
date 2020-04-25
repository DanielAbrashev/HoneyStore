package com.honeystore.service;

import com.honeystore.domain.UserShipping;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

/**
 * created by saikat on 4/19/19
 */

public interface UserShippingService {

    UserShipping getOne(Long id);

    void removeById(Long shippingId);
}
