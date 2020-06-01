package com.adminportal.repository;

import com.adminportal.domain.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
