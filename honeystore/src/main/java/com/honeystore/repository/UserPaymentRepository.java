package com.honeystore.repository;

import com.honeystore.domain.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * created by saikat on 4/18/19
 */
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {


}
