<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>GreenLiving - Tips</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    .top { margin-bottom: 16px; }
    .tip { border: 1px solid #ddd; padding: 12px; border-radius: 8px; margin-bottom: 12px; }
    .cat { font-size: 12px; padding: 2px 8px; border: 1px solid #aaa; border-radius: 999px; display: inline-block; }
    .title { margin: 8px 0 6px; }
    .content { margin: 0; }
    .addBtn { display:inline-block; margin-top:10px; padding:8px 12px; border:1px solid #222; border-radius:8px; }
    a { text-decoration: none; }
  </style>
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>

  <div class="container">
    <div class="top">
      <h1>Sustainable Living Tips</h1>
      <p><a href="<c:url value='/'/>">← Back to Home</a></p>

      <p>
        <a class="addBtn" href="<c:url value='/add-tip'/>">+ Add New Tip</a>
      </p>
    </div>

    <c:choose>
      <c:when test="${empty tips}">
        <p>No tips found.</p>
      </c:when>
      <c:otherwise>
        <c:forEach var="t" items="${tips}">
          <div class="tip">
            <span class="cat">${t.category}</span>
            <h3 class="title">${t.title}</h3>
            <p class="content">${t.content}</p>
          </div>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>