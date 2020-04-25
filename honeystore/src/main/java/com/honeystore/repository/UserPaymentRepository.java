package com.honeystore.repository;

import com.honeystore.domain.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {

}
