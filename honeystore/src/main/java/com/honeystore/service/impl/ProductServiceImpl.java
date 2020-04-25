package com.honeystore.service.impl;

import com.honeystore.domain.Product;
import com.honeystore.repository.ProductRepository;
import com.honeystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
