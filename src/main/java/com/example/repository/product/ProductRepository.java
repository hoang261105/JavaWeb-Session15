package com.example.repository.product;

import com.example.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(int id);
}
