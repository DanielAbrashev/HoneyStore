package com.honeystore.repository;

import com.honeystore.domain.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by z00382545 on 12/30/16.
 */
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
