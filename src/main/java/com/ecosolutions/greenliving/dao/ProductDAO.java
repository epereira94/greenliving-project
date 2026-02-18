
package com.ecosolutions.greenliving.dao;

import com.ecosolutions.greenliving.db.DBConnection;
import com.ecosolutions.greenliving.model.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO {

    public List<Product> findAll() throws SQLException {
        String sql = "SELECT product_id, name, category, description, eco_score, price, product_url, image_url " +
                     "FROM products ORDER BY product_id DESC";

        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setDescription(rs.getString("description"));
                p.setEcoScore(rs.getInt("eco_score"));

                BigDecimal price = rs.getBigDecimal("price");
                p.setPrice(price);

                p.setProductUrl(rs.getString("product_url"));
                p.setImageUrl(rs.getString("image_url"));
                list.add(p);
            }
        }
        return list;
    }

    public Product findById(int id) throws SQLException {
        String sql = "SELECT product_id, name, category, description, eco_score, price, product_url, image_url " +
                     "FROM products WHERE product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setDescription(rs.getString("description"));
                p.setEcoScore(rs.getInt("eco_score"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setProductUrl(rs.getString("product_url"));
                p.setImageUrl(rs.getString("image_url"));
                return p;
            }
        }
    }

    public int insert(Product p) throws SQLException {
        String sql = "INSERT INTO products (name, category, description, eco_score, price, product_url, image_url) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setString(3, p.getDescription());
            ps.setInt(4, p.getEcoScore());

            if (p.getPrice() == null) ps.setNull(5, Types.DECIMAL);
            else ps.setBigDecimal(5, p.getPrice());

            ps.setString(6, p.getProductUrl());
            ps.setString(7, p.getImageUrl());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    /**
     * Recommendation query used by RecommendServlet.
     * Returns a list of rows as Map<String,Object> so the JSP can render easily.
     *
     * Filters are optional:
     *  - category (exact match)
     *  - maxPrice (<=)
     *  - minEcoScore (>=)
     */
    public List<Map<String, Object>> recommend(String category, Integer maxPrice, Integer minEcoScore) throws SQLException {

        StringBuilder sql = new StringBuilder(
            "SELECT product_id, name, category, description, eco_score, price, product_url, image_url " +
            "FROM products WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (category != null && !category.trim().isEmpty()) {
            sql.append(" AND category = ? ");
            params.add(category.trim());
        }

        if (minEcoScore != null) {
            sql.append(" AND eco_score >= ? ");
            params.add(minEcoScore);
        }

        if (maxPrice != null) {
            // price is DECIMAL in DB; compare using BigDecimal
            sql.append(" AND price IS NOT NULL AND price <= ? ");
            params.add(new BigDecimal(maxPrice));
        }

        sql.append(" ORDER BY eco_score DESC, price ASC, product_id DESC ");

        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object val = params.get(i);

                if (val instanceof Integer) {
                    ps.setInt(i + 1, (Integer) val);
                } else if (val instanceof BigDecimal) {
                    ps.setBigDecimal(i + 1, (BigDecimal) val);
                } else {
                    ps.setString(i + 1, String.valueOf(val));
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("product_id", rs.getInt("product_id"));
                    row.put("name", rs.getString("name"));
                    row.put("category", rs.getString("category"));
                    row.put("description", rs.getString("description"));
                    row.put("eco_score", rs.getInt("eco_score"));
                    row.put("price", rs.getBigDecimal("price"));
                    row.put("product_url", rs.getString("product_url"));
                    row.put("image_url", rs.getString("image_url"));
                    results.add(row);
                }
            }
        }

        return results;
    }
}
