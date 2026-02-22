<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Add Product</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>

  <div class="container">
    <div class="card form-card">
      <h1 style="margin-top:0;">Add an Eco-Friendly Product</h1>
      <p><a href="<c:url value='/products'/>">← Back to Products</a></p>

      <c:if test="${not empty error}">
        <div class="error">${error}</div>
      </c:if>

      <!-- IMPORTANT: no class="row" on the form -->
      <form method="post" action="<c:url value='/add-product'/>">

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

        <div class="actions">
          <button class="btn" type="submit">Save Product</button>
          <a class="btn btn-link" href="<c:url value='/products'/>">Cancel</a>
        </div>
      </form>
    </div>
  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>