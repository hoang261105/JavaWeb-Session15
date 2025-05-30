package com.example.repository.review;

import com.example.model.Review;

import java.util.List;

public interface ReviewRepository {
    boolean saveReview(Review review);

    List<Review> findAllReviewsById(int productId);

    String findUsernameById(int id);
}
