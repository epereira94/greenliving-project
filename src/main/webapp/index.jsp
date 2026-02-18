<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>GreenLiving</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>

  <div class="container">
    <div class="card">
      <h1>GreenLiving</h1>
      <p class="small">EcoSolutions Inc. — sustainable tips, product recommendations, community forum, reviews, rewards, carbon calculator, and news.</p>
      <div class="hr"></div>
      <p>Health check: <a href="<c:url value='/health'/>">/health</a></p>
      <p>DB test: <a href="<c:url value='/db-test'/>">/db-test</a></p>
    </div>
  </div>
</body>
</html>
