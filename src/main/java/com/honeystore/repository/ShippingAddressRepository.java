package com.honeystore.repository;

import com.honeystore.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}
