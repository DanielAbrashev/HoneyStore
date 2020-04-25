package com.honeystore.repository;

import com.honeystore.domain.UserShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * created by saikat on 4/19/19
 */
public interface UserShippingRepository extends JpaRepository<UserShipping, Long> {
}
