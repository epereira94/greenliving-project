package com.ecosolutions.greenliving.dao;

import com.ecosolutions.greenliving.db.DBConnection;

import java.sql.*;
import java.util.*;

public class NewsDAO {

    public List<Map<String,Object>> listAll() {
        String sql = "SELECT news_id, title, content, source_url, created_at FROM news_posts ORDER BY created_at DESC, news_id DESC";
        List<Map<String,Object>> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String,Object> m = new HashMap<>();
                m.put("newsId", rs.getInt("news_id"));
                m.put("title", rs.getString("title"));
                m.put("content", rs.getString("content"));
                m.put("sourceUrl", rs.getString("source_url"));
                m.put("createdAt", rs.getTimestamp("created_at"));
                out.add(m);
            }
        } catch (Exception e) {
            throw new RuntimeException("list news failed", e);
        }
        return out;
    }

    public void add(String title, String content, String sourceUrl) {
        String sql = "INSERT INTO news_posts(title, content, source_url) VALUES(?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, content);
            if (sourceUrl == null || sourceUrl.trim().isEmpty()) ps.setNull(3, Types.VARCHAR); else ps.setString(3, sourceUrl.trim());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("add news failed", e);
        }
    }
}
