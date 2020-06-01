package com.adminportal.repository;

import com.adminportal.domain.UserBilling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBillingRepository extends JpaRepository<UserBilling, Long> {
}
