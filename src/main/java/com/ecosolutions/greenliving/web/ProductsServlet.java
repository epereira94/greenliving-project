package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.ProductDAO;
import com.ecosolutions.greenliving.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductsServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<Product> products = productDAO.findAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
