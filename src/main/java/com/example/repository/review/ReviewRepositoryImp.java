package com.example.repository.review;

import com.example.config.ConnectionDB;
import com.example.model.Review;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryImp implements ReviewRepository {
    @Override
    public boolean saveReview(Review review) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call save_review(?,?,?,?)}");

            callSt.setInt(1, review.getProductId());
            callSt.setInt(2, review.getUserId());
            callSt.setInt(3, review.getRating());
            callSt.setString(4, review.getComment());

            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Review> findAllReviewsById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Review> reviews = new ArrayList<Review>();

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_reviews_by_id(?)}");

            callSt.setInt(1, productId);

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setProductId(rs.getInt("product_id"));
                review.setUserId(rs.getInt("user_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public String findUsernameById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_user_by_id(?)}");

            callSt.setInt(1, id);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
