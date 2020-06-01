package com.adminportal.repository;

import com.adminportal.domain.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {

}
