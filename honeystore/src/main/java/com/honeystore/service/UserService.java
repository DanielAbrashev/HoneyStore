package com.honeystore.service;

import com.honeystore.domain.User;
import com.honeystore.domain.UserBilling;
import com.honeystore.domain.UserPayment;
import com.honeystore.domain.UserShipping;
import com.honeystore.domain.security.PasswordResetToken;
import com.honeystore.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findByEmail (String email);

    User getOne (Long id);

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);

    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultShipping(Long shippingAddressId, User user);

    void updateUserPaymentInfo(UserShipping userShipping, UserBilling userBilling, UserPayment userPayment, User user);


}
