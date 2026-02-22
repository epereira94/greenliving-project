<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Thread</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>
  <div class="container">

    <div class="card">
      <a class="btn" href="<c:url value='/forum'/>">← Back</a>
      <h1 style="margin-top:12px;">${thread.title}</h1>
      <div class="small">Created by ${thread.createdBy} • ${thread.createdAt}</div>
    </div>

    <div class="card">
      <h2>Posts</h2>
      <c:forEach var="p" items="${posts}">
        <div class="card" style="margin:10px 0;">
          <b>${p.postedBy}</b> <span class="small">(${p.createdAt})</span>
          <div class="hr"></div>
          <div>${p.content}</div>
        </div>
      </c:forEach>
    </div>

    <div class="card">
      <h2>Reply</h2>
      <form method="post" action="<c:url value='/thread'/>">
        <input type="hidden" name="threadId" value="${thread.threadId}"/>
        <label>Your name</label>
        <input name="postedBy" required maxlength="80"/>
        <label>Message</label>
        <textarea name="content" required></textarea>
        <p style="margin-top:12px;"><button class="btn" type="submit">Post Reply</button></p>
      </form>
    </div>

  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>