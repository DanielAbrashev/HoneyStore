package com.honeystore.repository;

import com.honeystore.domain.UserShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserShippingRepository extends JpaRepository<UserShipping, Long> {

}
