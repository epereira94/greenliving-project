<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Recommendations</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>
  <div class="container">
    <div class="card">
      <h1>Product Recommendation Engine</h1>
      <p class="small">Simple filter-based engine for Release 1 (category + max price + minimum eco-score).</p>

      <form method="get" action="<c:url value='/recommend'/>">
        <div class="row">
          <div>
            <label>Category</label>
            <input name="category" value="${category}" placeholder="e.g. Kitchen, Energy"/>
          </div>
          <div>
            <label>Max Price</label>
            <input name="maxPrice" value="${maxPrice}" type="number" min="0"/>
          </div>
          <div>
            <label>Min Eco Score</label>
            <input name="minEcoScore" value="${minEcoScore}" type="number" min="0" max="100"/>
          </div>
        </div>
        <p style="margin-top:12px;">
          <button class="btn" type="submit">Recommend</button>
          <a class="btn" href="<c:url value='/products'/>">Back</a>
        </p>
      </form>
    </div>

    <c:forEach var="p" items="${recs}">
      <div class="card">
        <span class="badge">${p.category}</span>
        <h3 style="margin:10px 0 6px;">${p.name}</h3>
        <div class="small">${p.description}</div>
        <div class="hr"></div>
        <div><b>Eco score:</b> ${p.ecoScore}</div>
        <div><b>Price:</b>
          <c:choose>
            <c:when test="${p.price == null}">N/A</c:when>
            <c:otherwise>$${p.price}</c:otherwise>
          </c:choose>
        </div>
        <p style="margin-top:10px;">
          <a class="btn" href="<c:url value='/product?id=${p.productId}'/>">View + Reviews</a>
        </p>
      </div>
    </c:forEach>
  </div>
</body>
</html>

