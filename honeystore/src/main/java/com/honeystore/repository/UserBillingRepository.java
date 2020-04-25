package com.honeystore.repository;

import com.honeystore.domain.UserBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by z00382545 on 12/31/16.
 */
public interface UserBillingRepository extends JpaRepository<UserBilling, Long> {
}
