package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.NewsDAO;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class NewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final NewsDAO dao = new NewsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("news", dao.listAll());
        req.getRequestDispatcher("/WEB-INF/views/news.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = t(req.getParameter("title"));
        String content = t(req.getParameter("content"));
        String url = t(req.getParameter("sourceUrl"));

        if (!title.isEmpty() && !content.isEmpty()) {
            dao.add(title, content, url);
        }
        resp.sendRedirect(req.getContextPath()+"/news");
    }

    private String t(String s){ return s==null?"":s.trim(); }
}
