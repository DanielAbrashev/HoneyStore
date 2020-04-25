package com.adminportal.repository;

import com.adminportal.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {

    Product save(Product product);
    List<Product> findAll();

}
