package com.example.service.review;

import com.example.model.Review;
import com.example.repository.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public boolean saveReview(Review review) {
        return reviewRepository.saveReview(review);
    }

    @Override
    public List<Review> findAllReviewsById(int productId) {
        return reviewRepository.findAllReviewsById(productId);
    }

    @Override
    public String findUsernameById(int id) {
        return reviewRepository.findUsernameById(id);
    }
}
