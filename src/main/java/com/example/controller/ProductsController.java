package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.Review;
import com.example.model.User;
import com.example.service.cart.CartService;
import com.example.service.products.ProductService;
import com.example.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CartService cartService;

    @GetMapping("products")
    public String products(Model model, HttpSession session) {
        List<Product> products = productService.findAll();
        User user = (User) session.getAttribute("user");
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "products";
    }
    
    @GetMapping("productDetail/{id}")
    public String productDetail(Model model, @PathVariable int id, HttpSession session) {
        List<Review> reviews = reviewService.findAllReviewsById(id);
        model.addAttribute("reviews", reviews);
        Review review = new Review();
        Product product = productService.findById(id);
        model.addAttribute("reviewService", reviewService);
        model.addAttribute("review", review);
        model.addAttribute("product", product);
        session.setAttribute("product", product);
        return "productDetail";
    }

    @PostMapping("reviews")
    public String reviews(Review review, HttpSession session) {
        Product product = (Product) session.getAttribute("product");
        User user = (User) session.getAttribute("user");
        review.setProductId(product.getProductId());
        review.setUserId(user.getId());
        reviewService.saveReview(review);
        return "redirect:/products";
    }

    @PostMapping("add-to-cart")
    public String addToCart(Cart cart, HttpSession session) {
        Product product = (Product) session.getAttribute("product");
        User user = (User) session.getAttribute("user");
        cart.setProductId(product.getProductId());
        cart.setUserId(user.getId());

        List<Cart> carts = cartService.getAllCarts(user.getId());
        session.setAttribute("carts", carts);

        boolean existProduct = carts.stream().anyMatch(cart1 -> cart1.getProductId() == product.getProductId() && cart1.getUserId() == user.getId());

        if (existProduct) {
            cartService.updateCart(cart.getUserId(), cart.getProductId());
            return "redirect:/products";
        }

        cartService.addToCart(cart);
        return "redirect:/products";
    }
}
