package com.adminportal.service.impl;

import com.adminportal.domain.Product;
import com.adminportal.repository.ProductRepository;
import com.adminportal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.getOne(id);
    }

    public void removeOne(Long id) {
        productRepository.deleteById(id);
    }


}
