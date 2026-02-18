package com.ecosolutions.greenliving.dao;

import com.ecosolutions.greenliving.db.DBConnection;

import java.sql.*;
import java.util.*;

public class RewardsDAO {

    public List<Map<String,Object>> listActions() {
        String sql = "SELECT action_id, action_name, points FROM rewards_actions ORDER BY points DESC, action_id ASC";
        List<Map<String,Object>> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String,Object> m = new HashMap<>();
                m.put("actionId", rs.getInt("action_id"));
                m.put("actionName", rs.getString("action_name"));
                m.put("points", rs.getInt("points"));
                out.add(m);
            }
        } catch (Exception e) {
            throw new RuntimeException("listActions failed", e);
        }
        return out;
    }

    public int getUserPoints(String userName) {
        String sql = "SELECT points FROM user_points WHERE user_name=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("points");
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException("getUserPoints failed", e);
        }
    }

    public void addActionForUser(String userName, int actionId) {
        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);

            int points;
            try (PreparedStatement ps = c.prepareStatement("SELECT points FROM rewards_actions WHERE action_id=?")) {
                ps.setInt(1, actionId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) throw new RuntimeException("Invalid actionId");
                    points = rs.getInt("points");
                }
            }

            // upsert user_points
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO user_points(user_name, points) VALUES(?,?) " +
                    "ON DUPLICATE KEY UPDATE points = points + VALUES(points)")) {
                ps.setString(1, userName);
                ps.setInt(2, points);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO user_actions_log(user_name, action_id) VALUES(?,?)")) {
                ps.setString(1, userName);
                ps.setInt(2, actionId);
                ps.executeUpdate();
            }

            c.commit();
        } catch (Exception e) {
            throw new RuntimeException("addActionForUser failed", e);
        }
    }
}
