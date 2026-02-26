package com.ecosolutions.greenliving.dao;

import com.ecosolutions.greenliving.db.DBConnection;
import com.ecosolutions.greenliving.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewDAO {

    // Small DTO for averages
    public static class RatingSummary {
        private final double average;
        private final int count;

        public RatingSummary(double average, int count) {
            this.average = average;
            this.count = count;
        }
        public double getAverage() { return average; }
        public int getCount() { return count; }
    }

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

    /** Average rating + review count for a single product */
    public RatingSummary getRatingSummary(int productId) throws SQLException {
        String sql = "SELECT AVG(rating) AS avg_rating, COUNT(*) AS cnt FROM reviews WHERE product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double avg = rs.getDouble("avg_rating"); // 0.0 if no rows
                    int cnt = rs.getInt("cnt");
                    return new RatingSummary(avg, cnt);
                }
            }
        }
        return new RatingSummary(0.0, 0);
    }

    /** Map of productId -> rating summary for all products (used on Products page) */
    public Map<Integer, RatingSummary> getAllRatingSummaries() throws SQLException {
        String sql = "SELECT product_id, AVG(rating) AS avg_rating, COUNT(*) AS cnt " +
                     "FROM reviews GROUP BY product_id";

        Map<Integer, RatingSummary> map = new HashMap<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                double avg = rs.getDouble("avg_rating");
                int cnt = rs.getInt("cnt");
                map.put(productId, new RatingSummary(avg, cnt));
            }
        }

        return map;
    }
}
