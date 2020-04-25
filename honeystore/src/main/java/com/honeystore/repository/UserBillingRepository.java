package com.honeystore.repository;

import com.honeystore.domain.UserBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserBillingRepository extends JpaRepository<UserBilling, Long> {
}
