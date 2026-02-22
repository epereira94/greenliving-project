<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Product Details</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    .card { border:1px solid #ddd; border-radius:10px; padding:14px; margin-bottom:12px; max-width: 860px; }
    .cat { font-size:12px; padding:2px 8px; border:1px solid #aaa; border-radius:999px; display:inline-block; }
    label { display:block; margin-top:10px; font-weight:bold; }
    input, textarea, select { width: 100%; max-width: 860px; padding: 10px; margin-top: 6px; border: 1px solid #ccc; border-radius: 8px; }
    textarea { height: 90px; }
    .btn { padding: 10px 14px; border:1px solid #111; border-radius: 8px; cursor:pointer; background:#fff; }
    .review { border-top:1px solid #eee; padding-top:10px; margin-top:10px; }
    .muted { color:#666; }
  </style>
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>

  <div class="container">
    <p><a href="<c:url value='/products'/>">← Back to Products</a></p>

    <div class="card">
      <span class="cat">${product.category}</span>
      <h1 style="margin:8px 0 6px;">${product.name}</h1>
      <p class="muted" style="margin:0 0 8px;">Eco score: <b>${product.ecoScore}</b></p>
      <p style="margin:0 0 8px;">${product.description}</p>

      <c:if test="${not empty product.price}">
        <p class="muted" style="margin:0 0 8px;">Price: <b>$${product.price}</b></p>
      </c:if>

      <c:if test="${not empty product.productUrl}">
        <p style="margin:0;"><a class="btn" href="${product.productUrl}" target="_blank">Open product link</a></p>
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
            <div class="review">
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

