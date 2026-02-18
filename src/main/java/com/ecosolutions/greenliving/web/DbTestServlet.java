package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DbTestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM tips")) {

            rs.next();
            int total = rs.getInt("total");

            resp.getWriter().println("DB connection OK ✅");
            resp.getWriter().println("tips rows = " + total);

        } catch (Exception ex) {
            resp.getWriter().println("DB connection FAILED ❌");
            resp.getWriter().println(ex.getClass().getName() + ": " + ex.getMessage());
            ex.printStackTrace(resp.getWriter());
        }
    }
}
