package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.RewardsDAO;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class RewardsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RewardsDAO dao = new RewardsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        if (user == null) user = "";
        user = user.trim();

        req.setAttribute("actions", dao.listActions());
        req.setAttribute("userName", user);
        req.setAttribute("points", user.isEmpty() ? 0 : dao.getUserPoints(user));
        req.getRequestDispatcher("/WEB-INF/views/rewards.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String user = t(req.getParameter("userName"));
        int actionId;
        try { actionId = Integer.parseInt(req.getParameter("actionId")); }
        catch(Exception e){ resp.sendRedirect(req.getContextPath()+"/rewards"); return; }

        if (!user.isEmpty()) {
            dao.addActionForUser(user, actionId);
            resp.sendRedirect(req.getContextPath()+"/rewards?user=" + user);
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/rewards");
    }

    private String t(String s){ return s==null?"":s.trim(); }
}
