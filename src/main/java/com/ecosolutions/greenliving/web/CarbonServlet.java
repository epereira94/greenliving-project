package com.ecosolutions.greenliving.web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class CarbonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/carbon.jsp").forward(req, resp);
    }
}
