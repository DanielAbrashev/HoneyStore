package com.honeystore.repository;

import com.honeystore.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    List<Product> findByCategory(String category);

    List<Product> findByCategoryIndex(String categoryIndex);

    List<Product> findByProductNameContaining(String productName);


    Product getOne(Long id);
}

