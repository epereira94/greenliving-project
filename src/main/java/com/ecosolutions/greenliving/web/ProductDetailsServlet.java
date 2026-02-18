package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.ProductDAO;
import com.ecosolutions.greenliving.dao.ReviewDAO;
import com.ecosolutions.greenliving.model.Product;
import com.ecosolutions.greenliving.model.Review;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductDetailsServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();
    private final ReviewDAO reviewDAO = new ReviewDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        try {
            Product product = productDAO.findById(id);
            if (product == null) {
                resp.sendRedirect(req.getContextPath() + "/products");
                return;
            }

            List<Review> reviews = reviewDAO.findByProductId(id);

            req.setAttribute("product", product);
            req.setAttribute("reviews", reviews);

            req.getRequestDispatcher("/WEB-INF/views/productDetails.jsp").forward(req, resp);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
