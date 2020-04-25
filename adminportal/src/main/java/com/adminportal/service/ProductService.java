package com.adminportal.service;

import com.adminportal.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void removeOne(Long id);

}
