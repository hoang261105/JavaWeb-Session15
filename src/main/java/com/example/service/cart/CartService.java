package com.example.service.cart;

import com.example.model.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    boolean addToCart(Cart cart);

    List<Cart> getAllCarts(int userId);

    boolean updateCart(int userId, int productId);
    
    String getNameById(int productId);

    BigDecimal getPriceById(int productId);
}
