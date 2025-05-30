package com.example.service.products;

import com.example.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(int id);
}
