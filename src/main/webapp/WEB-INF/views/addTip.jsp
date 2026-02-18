<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Add Tip - GreenLiving</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    .box { max-width: 640px; }
    label { display:block; margin-top:12px; font-weight:600; }
    input, textarea { width: 100%; padding: 10px; margin-top: 6px; border: 1px solid #ccc; border-radius: 8px; }
    textarea { min-height: 140px; }
    .actions { margin-top: 16px; display:flex; gap:12px; align-items:center; }
    button { padding: 10px 14px; border: 1px solid #222; border-radius: 8px; cursor:pointer; background:#fff; }
    .error { background:#ffe8e8; border:1px solid #ffb3b3; padding:10px; border-radius:8px; margin:12px 0; }
    a { text-decoration:none; }
  </style>
</head>
<body>

  <div class="box">
    <h1>Add a Sustainable Tip</h1>
    <p><a href="<c:url value='/tips'/>">← Back to Tips</a></p>

    <c:if test="${not empty error}">
      <div class="error">${error}</div>
    </c:if>

    <form method="post" action="<c:url value='/add-tip'/>">
      <label for="title">Title</label>
      <input id="title" name="title" maxlength="150" required value="${titleVal}" />

      <label for="category">Category</label>
      <input id="category" name="category" maxlength="60" required value="${categoryVal}" />

      <label for="content">Content</label>
      <textarea id="content" name="content" required>${contentVal}</textarea>

      <div class="actions">
        <button type="submit">Save Tip</button>
        <a href="<c:url value='/tips'/>">Cancel</a>
      </div>
    </form>
  </div>

</body>
</html>
