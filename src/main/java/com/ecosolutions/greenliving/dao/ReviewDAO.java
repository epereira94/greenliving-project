package com.ecosolutions.greenliving.dao;

import com.ecosolutions.greenliving.db.DBConnection;
import com.ecosolutions.greenliving.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public List<Review> findByProductId(int productId) throws SQLException {
        String sql = "SELECT review_id, product_id, rating, comment, reviewer_name " +
                     "FROM reviews WHERE product_id=? ORDER BY review_id DESC";

        List<Review> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Review r = new Review();
                    r.setReviewId(rs.getInt("review_id"));
                    r.setProductId(rs.getInt("product_id"));
                    r.setRating(rs.getInt("rating"));
                    r.setComment(rs.getString("comment"));
                    r.setReviewerName(rs.getString("reviewer_name"));
                    list.add(r);
                }
            }
        }
        return list;
    }

    public void insert(Review r) throws SQLException {
        String sql = "INSERT INTO reviews (product_id, rating, comment, reviewer_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getProductId());
            ps.setInt(2, r.getRating());
            ps.setString(3, r.getComment());
            ps.setString(4, r.getReviewerName());

            ps.executeUpdate();
        }
    }
}
