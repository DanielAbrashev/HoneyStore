package com.honeystore.service;

import com.honeystore.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Product getOne(Long id);

    List<Product> findByCategory(String category);

    List<Product> blurrySearch(String productName);

    List<Product> findByCategoryIndex(String categoryIndex);




}
