<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>News</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>
  <div class="container">

    <div class="card">
      <h1>News & Updates</h1>
      <p class="small">Post sustainability updates or curated news links.</p>
    </div>

    <div class="card">
      <h2>Add News</h2>
      <form method="post" action="<c:url value='/news'/>">
        <label>Title</label>
        <input name="title" required maxlength="150"/>
        <label>Content</label>
        <textarea name="content" required></textarea>
        <label>Source URL (optional)</label>
        <input name="sourceUrl" maxlength="255"/>
        <p style="margin-top:12px;"><button class="btn" type="submit">Publish</button></p>
      </form>
    </div>

    <c:forEach var="n" items="${news}">
      <div class="card">
        <h3 style="margin:0 0 8px;">${n.title}</h3>
        <div class="small">${n.createdAt}</div>
        <div class="hr"></div>
        <div>${n.content}</div>
        <c:if test="${not empty n.sourceUrl}">
          <div class="hr"></div>
          <div class="small">Source: <a href="${n.sourceUrl}" target="_blank">${n.sourceUrl}</a></div>
        </c:if>
      </div>
    </c:forEach>

  </div>
</body>
</html>
