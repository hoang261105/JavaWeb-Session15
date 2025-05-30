package com.example.service.cart;

import com.example.model.Cart;
import com.example.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean addToCart(Cart cart) {
        return cartRepository.addToCart(cart);
    }

    @Override
    public List<Cart> getAllCarts(int userId) {
        return cartRepository.getAllCarts(userId);
    }

    @Override
    public boolean updateCart(int userId, int productId) {
        return cartRepository.updateCart(userId, productId);
    }

    @Override
    public String getNameById(int productId) {
        return cartRepository.getNameById(productId);
    }

    @Override
    public BigDecimal getPriceById(int productId) {
        return cartRepository.getPriceById(productId);
    }
}
