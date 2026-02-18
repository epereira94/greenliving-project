package com.ecosolutions.greenliving.dao;

import com.ecosolutions.greenliving.db.DBConnection;
import com.ecosolutions.greenliving.model.Tip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipDAO {

    public List<Tip> findAllActive() {
        List<Tip> tips = new ArrayList<>();

        String sql = "SELECT tip_id, title, category, content, is_active " +
                     "FROM tips " +
                     "WHERE is_active = 1 " +
                     "ORDER BY created_at DESC, tip_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tip t = new Tip();
                t.setTipId(rs.getInt("tip_id"));
                t.setTitle(rs.getString("title"));
                t.setCategory(rs.getString("category"));
                t.setContent(rs.getString("content"));
                t.setActive(rs.getInt("is_active") == 1);
                tips.add(t);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Error loading tips", ex);
        }

        return tips;
    }

    public void createTip(String title, String category, String content) {
        String sql = "INSERT INTO tips (title, category, content, is_active) VALUES (?, ?, ?, 1)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, category);
            ps.setString(3, content);

            ps.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("Error creating tip", ex);
        }
    }
}
