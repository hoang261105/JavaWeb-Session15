package com.example.service.review;

import com.example.model.Review;

import java.util.List;

public interface ReviewService {
    boolean saveReview(Review review);

    List<Review> findAllReviewsById(int productId);

    String findUsernameById(int id);
}
