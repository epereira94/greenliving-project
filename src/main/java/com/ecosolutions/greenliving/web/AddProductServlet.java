package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.ProductDAO;
import com.ecosolutions.greenliving.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

public class AddProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        String ecoScoreStr = req.getParameter("ecoScore");
        String priceStr = req.getParameter("price");
        String productUrl = req.getParameter("productUrl");
        String imageUrl = req.getParameter("imageUrl");

        // Basic validation
        if (name == null || name.trim().isEmpty() ||
            category == null || category.trim().isEmpty() ||
            description == null || description.trim().isEmpty()) {

            req.setAttribute("error", "Name, category, and description are required.");
            req.getRequestDispatcher("/WEB-INF/views/addProduct.jsp").forward(req, resp);
            return;
        }

        int ecoScore = 50;
        try {
            if (ecoScoreStr != null && !ecoScoreStr.trim().isEmpty()) {
                ecoScore = Integer.parseInt(ecoScoreStr.trim());
            }
        } catch (NumberFormatException ignored) {}

        BigDecimal price = null;
        try {
            if (priceStr != null && !priceStr.trim().isEmpty()) {
                price = new BigDecimal(priceStr.trim());
            }
        } catch (Exception ignored) {}

        Product p = new Product();
        p.setName(name.trim());
        p.setCategory(category.trim());
        p.setDescription(description.trim());
        p.setEcoScore(ecoScore);
        p.setPrice(price);
        p.setProductUrl(productUrl != null ? productUrl.trim() : null);
        p.setImageUrl(imageUrl != null ? imageUrl.trim() : null);

        try {
            productDAO.insert(p);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
