package com.ecosolutions.greenliving.dao;

import com.ecosolutions.greenliving.db.DBConnection;

import java.sql.*;
import java.util.*;

public class ForumDAO {

    public List<Map<String,Object>> listThreads() {
        String sql =
            "SELECT t.thread_id, t.title, t.created_by, t.created_at, " +
            "       (SELECT COUNT(*) FROM forum_posts p WHERE p.thread_id=t.thread_id) AS post_count " +
            "FROM forum_threads t ORDER BY t.created_at DESC, t.thread_id DESC";
        List<Map<String,Object>> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String,Object> m = new HashMap<>();
                m.put("threadId", rs.getInt("thread_id"));
                m.put("title", rs.getString("title"));
                m.put("createdBy", rs.getString("created_by"));
                m.put("createdAt", rs.getTimestamp("created_at"));
                m.put("postCount", rs.getInt("post_count"));
                out.add(m);
            }
        } catch (Exception e) {
            throw new RuntimeException("listThreads failed", e);
        }
        return out;
    }

    public int createThread(String title, String createdBy, String firstPostContent) {
        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);

            int threadId;
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO forum_threads(title, created_by) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, title);
                ps.setString(2, createdBy);
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    keys.next();
                    threadId = keys.getInt(1);
                }
            }

            try (PreparedStatement ps2 = c.prepareStatement(
                    "INSERT INTO forum_posts(thread_id, posted_by, content) VALUES(?,?,?)")) {
                ps2.setInt(1, threadId);
                ps2.setString(2, createdBy);
                ps2.setString(3, firstPostContent);
                ps2.executeUpdate();
            }

            c.commit();
            return threadId;
        } catch (Exception e) {
            throw new RuntimeException("createThread failed", e);
        }
    }

    public Map<String,Object> getThread(int threadId) {
        String sql = "SELECT thread_id, title, created_by, created_at FROM forum_threads WHERE thread_id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, threadId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Map<String,Object> m = new HashMap<>();
                m.put("threadId", rs.getInt("thread_id"));
                m.put("title", rs.getString("title"));
                m.put("createdBy", rs.getString("created_by"));
                m.put("createdAt", rs.getTimestamp("created_at"));
                return m;
            }
        } catch (Exception e) {
            throw new RuntimeException("getThread failed", e);
        }
    }

    public List<Map<String,Object>> listPosts(int threadId) {
        String sql = "SELECT post_id, posted_by, content, created_at FROM forum_posts WHERE thread_id=? ORDER BY created_at ASC, post_id ASC";
        List<Map<String,Object>> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, threadId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String,Object> m = new HashMap<>();
                    m.put("postId", rs.getInt("post_id"));
                    m.put("postedBy", rs.getString("posted_by"));
                    m.put("content", rs.getString("content"));
                    m.put("createdAt", rs.getTimestamp("created_at"));
                    out.add(m);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("listPosts failed", e);
        }
        return out;
    }

    public void addReply(int threadId, String postedBy, String content) {
        String sql = "INSERT INTO forum_posts(thread_id, posted_by, content) VALUES(?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, threadId);
            ps.setString(2, postedBy);
            ps.setString(3, content);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("addReply failed", e);
        }
    }
}
