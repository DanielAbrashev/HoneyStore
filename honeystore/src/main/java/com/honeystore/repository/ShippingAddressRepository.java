package com.honeystore.repository;

import com.honeystore.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by z00382545 on 12/30/16.
 */
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}
