<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Product Details</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
  <style>
    .stars { font-size: 16px; letter-spacing: 1px; }
    .stars .on { color: #f4b400; }
    .stars .off { color: #cfcfcf; }
  </style>
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>

  <div class="container">
    <p><a href="<c:url value='/products'/>">← Back to Products</a></p>

    <div class="card">
      <span class="badge">${product.category}</span>
      <h1 style="margin:10px 0 6px;">${product.name}</h1>

      <div class="small" style="margin:0 0 6px;">
        Eco score: <b>${product.ecoScore}</b>
      </div>

      <!-- Average rating with stars -->
      <div class="small" style="margin:0 0 10px;">
        Rating:
        <c:choose>
          <c:when test="${ratingCount == 0}">
            No ratings yet
          </c:when>
          <c:otherwise>
            <c:set var="rounded" value="${ratingAvg + 0.5}" />
            <c:set var="stars" value="${rounded - (rounded % 1)}" />

            <span class="stars" aria-label="Rating">
              <c:forEach begin="1" end="5" var="i">
                <c:choose>
                  <c:when test="${i <= stars}">
                    <span class="on">★</span>
                  </c:when>
                  <c:otherwise>
                    <span class="off">☆</span>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </span>
            <span> (${ratingCount})</span>
          </c:otherwise>
        </c:choose>
      </div>

      <div class="hr"></div>

      <div style="margin:0 0 10px;">${product.description}</div>

      <c:if test="${not empty product.price}">
        <div class="small" style="margin:0 0 10px;">Price: <b>$${product.price}</b></div>
      </c:if>

      <c:if test="${not empty product.productUrl}">
        <p style="margin:0;">
          <a class="btn" href="${product.productUrl}" target="_blank">Open product link</a>
        </p>
      </c:if>
    </div>

    <div class="card">
      <h2 style="margin-top:0;">Reviews</h2>

      <c:choose>
        <c:when test="${empty reviews}">
          <p>No reviews yet.</p>
        </c:when>
        <c:otherwise>
          <c:forEach var="r" items="${reviews}">
            <div style="border-top:1px solid #eee; padding-top:10px; margin-top:10px;">
              <p style="margin:0;"><b>${r.reviewerName}</b> — Rating: <b>${r.rating}/5</b></p>
              <p style="margin:6px 0 0;">${r.comment}</p>
            </div>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </div>

    <div class="card">
      <h2 style="margin-top:0;">Add a Review</h2>

      <form method="post" action="<c:url value='/add-review'/>">
        <input type="hidden" name="productId" value="${product.productId}" />

        <label>Your name (optional)</label>
        <input name="reviewerName" />

        <label>Rating</label>
        <select name="rating">
          <option value="5">5 - Excellent</option>
          <option value="4">4 - Good</option>
          <option value="3">3 - OK</option>
          <option value="2">2 - Poor</option>
          <option value="1">1 - Bad</option>
        </select>

        <label>Comment</label>
        <textarea name="comment" required></textarea>

        <div style="margin-top:12px;">
          <button class="btn" type="submit">Submit Review</button>
        </div>
      </form>
    </div>
  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>

