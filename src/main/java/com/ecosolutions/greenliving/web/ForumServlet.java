package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.ForumDAO;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class ForumServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ForumDAO dao = new ForumDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("threads", dao.listThreads());
        req.getRequestDispatcher("/WEB-INF/views/forum.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = t(req.getParameter("title"));
        String by = t(req.getParameter("createdBy"));
        String content = t(req.getParameter("content"));

        if (title.isEmpty() || by.isEmpty() || content.isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/forum");
            return;
        }

        int threadId = dao.createThread(title, by, content);
        resp.sendRedirect(req.getContextPath()+"/thread?id=" + threadId);
    }

    private String t(String s){ return s==null?"":s.trim(); }
}
