package com.honeystore.service.impl;

import com.honeystore.domain.Product;
import com.honeystore.repository.ProductRepository;
import com.honeystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        Pageable pageable = PageRequest.of(0,2);
        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if (product.isActive()) {
                activeProductList.add(product);
            }
        }

        return activeProductList;
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.getOne(id);
    }

    public List<Product> findByCategory(String category) {
        List<Product> productList = productRepository.findByCategory(category);

        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if (product.isActive()) {
                activeProductList.add(product);
            }
        }

        return activeProductList;
    }

    public List<Product> findByCategoryIndex(String categoryIndex) {
        List<Product> productListIndex = productRepository.findByCategoryIndex(categoryIndex);

        List<Product> activeProductListIndex = new ArrayList<>();

        for (Product product : productListIndex) {
            if (product.isActive()) {
                activeProductListIndex.add(product);
            }
        }

        return activeProductListIndex;
    }

    public List<Product> blurrySearch(String productName) {
        List<Product> productList = productRepository.findByProductNameContaining(productName);

        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if (product.isActive()) {
                activeProductList.add(product);
            }
        }

        return activeProductList;
    }


}
