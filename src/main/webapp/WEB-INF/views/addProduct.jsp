<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Add Product</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    label { display:block; margin-top:12px; font-weight:bold; }
    input, textarea { width: 100%; max-width: 760px; padding: 10px; margin-top: 6px; border: 1px solid #ccc; border-radius: 8px; }
    textarea { height: 120px; }
    .row { max-width: 760px; }
    .btn { padding: 10px 14px; border:1px solid #111; border-radius: 8px; cursor:pointer; background:#fff; }
    .link { margin-left: 10px; }
    .error { color: #b00020; margin-top: 10px; }
  </style>
</head>
<body>

  <h1>Add an Eco-Friendly Product</h1>
  <p><a href="<c:url value='/products'/>">← Back to Products</a></p>

  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>

  <form method="post" action="<c:url value='/add-product'/>" class="row">
    <label>Name</label>
    <input name="name" required />

    <label>Category</label>
    <input name="category" required />

    <label>Description</label>
    <textarea name="description" required></textarea>

    <label>Eco Score (0-100)</label>
    <input name="ecoScore" type="number" min="0" max="100" value="50" />

    <label>Price (optional)</label>
    <input name="price" type="number" step="0.01" min="0" />

    <label>Product URL (optional)</label>
    <input name="productUrl" />

    <label>Image URL (optional)</label>
    <input name="imageUrl" />

    <div style="margin-top:14px;">
      <button class="btn" type="submit">Save Product</button>
      <a class="link" href="<c:url value='/products'/>">Cancel</a>
    </div>
  </form>

</body>
</html>
