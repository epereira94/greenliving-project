package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.TipDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTipServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TipDAO tipDAO = new TipDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addTip.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String title = safeTrim(req.getParameter("title"));
        String category = safeTrim(req.getParameter("category"));
        String content = safeTrim(req.getParameter("content"));

        if (title.isEmpty() || category.isEmpty() || content.isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            req.setAttribute("titleVal", title);
            req.setAttribute("categoryVal", category);
            req.setAttribute("contentVal", content);
            req.getRequestDispatcher("/WEB-INF/views/addTip.jsp").forward(req, resp);
            return;
        }

        tipDAO.createTip(title, category, content);

        // Redirect back to tips list (PRG pattern)
        resp.sendRedirect(req.getContextPath() + "/tips");
    }

    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }
}
