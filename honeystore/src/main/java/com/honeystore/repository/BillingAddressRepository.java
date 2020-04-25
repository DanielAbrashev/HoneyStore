package com.honeystore.repository;

import com.honeystore.domain.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
