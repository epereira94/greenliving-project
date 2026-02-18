<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>GreenLiving - Products</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    .top { display:flex; justify-content:space-between; align-items:center; margin-bottom:16px; }
    .card { border:1px solid #ddd; border-radius:10px; padding:14px; margin-bottom:12px; }
    .cat { font-size:12px; padding:2px 8px; border:1px solid #aaa; border-radius:999px; display:inline-block; }
    a { text-decoration:none; }
    .btn { display:inline-block; padding:10px 12px; border:1px solid #111; border-radius:8px; }
    .muted { color:#666; }
  </style>
</head>
<body>

  <div class="top">
    <div>
      <h1>Eco-Friendly Products</h1>
      <p><a href="<c:url value='/'/>">← Back to Home</a></p>
    </div>
    <div>
      <a class="btn" href="<c:url value='/add-product'/>">+ Add Product</a>
    </div>
  </div>

  <c:choose>
    <c:when test="${empty products}">
      <p>No products found.</p>
    </c:when>
    <c:otherwise>
      <c:forEach var="p" items="${products}">
        <div class="card">
          <span class="cat">${p.category}</span>
          <h3 style="margin:8px 0 6px;">${p.name}</h3>
          <p class="muted" style="margin:0 0 8px;">Eco score: <b>${p.ecoScore}</b></p>
          <p style="margin:0 0 10px;">${p.description}</p>
          <a class="btn" href="<c:url value='/product'><c:param name='id' value='${p.productId}'/></c:url>">View details</a>
        </div>
      </c:forEach>
    </c:otherwise>
  </c:choose>

</body>
</html>
