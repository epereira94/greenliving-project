package com.ecosolutions.greenliving.web;

import com.ecosolutions.greenliving.dao.ReviewDAO;
import com.ecosolutions.greenliving.model.Review;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AddReviewServlet extends HttpServlet {

    private final ReviewDAO reviewDAO = new ReviewDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String productIdStr = req.getParameter("productId");
        String ratingStr = req.getParameter("rating");
        String comment = req.getParameter("comment");
        String reviewerName = req.getParameter("reviewerName");

        int productId;
        int rating;

        try {
            productId = Integer.parseInt(productIdStr);
        } catch (Exception ex) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        try {
            rating = Integer.parseInt(ratingStr);
        } catch (Exception ex) {
            rating = 5;
        }

        if (rating < 1) rating = 1;
        if (rating > 5) rating = 5;

        if (comment == null || comment.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/product?id=" + productId);
            return;
        }

        Review r = new Review();
        r.setProductId(productId);
        r.setRating(rating);
        r.setComment(comment.trim());
        r.setReviewerName((reviewerName == null || reviewerName.trim().isEmpty()) ? "Anonymous" : reviewerName.trim());

        try {
            reviewDAO.insert(r);
            resp.sendRedirect(req.getContextPath() + "/product?id=" + productId);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
