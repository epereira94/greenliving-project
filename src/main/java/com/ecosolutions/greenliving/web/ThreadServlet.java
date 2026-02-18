package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.ForumDAO;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class ThreadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ForumDAO dao = new ForumDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try { id = Integer.parseInt(req.getParameter("id")); }
        catch(Exception e){ resp.sendRedirect(req.getContextPath()+"/forum"); return; }

        req.setAttribute("thread", dao.getThread(id));
        req.setAttribute("posts", dao.listPosts(id));
        req.getRequestDispatcher("/WEB-INF/views/thread.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id;
        try { id = Integer.parseInt(req.getParameter("threadId")); }
        catch(Exception e){ resp.sendRedirect(req.getContextPath()+"/forum"); return; }

        String by = t(req.getParameter("postedBy"));
        String content = t(req.getParameter("content"));
        if (!by.isEmpty() && !content.isEmpty()) {
            dao.addReply(id, by, content);
        }
        resp.sendRedirect(req.getContextPath()+"/thread?id=" + id);
    }

    private String t(String s){ return s==null?"":s.trim(); }
}
