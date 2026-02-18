package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.TipDAO;
import com.ecosolutions.greenliving.model.Tip;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TipsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TipDAO tipDAO = new TipDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Tip> tips = tipDAO.findAllActive();
        req.setAttribute("tips", tips);
        req.getRequestDispatcher("/WEB-INF/views/tips.jsp").forward(req, resp);
    }
}
