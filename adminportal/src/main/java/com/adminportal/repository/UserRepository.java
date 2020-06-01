package com.adminportal.repository;

import com.adminportal.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();


    User findByUsername(String username);

    User findByEmail(String email);



}
