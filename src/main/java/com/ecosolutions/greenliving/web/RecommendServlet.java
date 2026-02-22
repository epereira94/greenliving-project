package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.ProductDAO;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.*;
import java.sql.SQLException;

public class RecommendServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String category = t(req.getParameter("category"));
        Integer maxPrice = toInt(req.getParameter("maxPrice"));
        Integer minEco = toInt(req.getParameter("minEcoScore"));

        try {
            List<Map<String,Object>> recs = dao.recommend(category, maxPrice, minEco);
            req.setAttribute("recs", recs);
        } catch (SQLException e) {
            // keeps UI working but makes the error clear in logs
            throw new ServletException("Failed to load recommendations", e);
        }

        req.setAttribute("category", category);
        req.setAttribute("maxPrice", maxPrice);
        req.setAttribute("minEcoScore", minEco);

        req.getRequestDispatcher("/WEB-INF/views/recommend.jsp").forward(req, resp);
    }

    private String t(String s){ return s == null ? "" : s.trim(); }

    private Integer toInt(String s){
        try {
            if (s == null || s.trim().isEmpty()) return null;
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return null;
        }
    }
}