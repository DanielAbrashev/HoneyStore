package com.adminportal.service;

import com.adminportal.domain.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product getOne(Long id);

    void removeOne(Long id);

}
