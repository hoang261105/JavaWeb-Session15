package com.example.controller;

import com.example.model.Cart;
import com.example.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("cart/{id}")
    public String cart(@PathVariable int id, Model model) {
        List<Cart> carts = cartService.getAllCarts(id);
        model.addAttribute("cartService", cartService);

        BigDecimal totalPrice = carts.stream()
                .map(cart -> {
                    BigDecimal price = cartService.getPriceById(cart.getProductId());
                    return price.multiply(BigDecimal.valueOf(cart.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("carts", carts);
        return "carts";
    }
}
