<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Forum</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>
  <div class="container">

    <div class="card">
      <h1>Community Forum</h1>
      <p class="small">Create threads, reply, and share sustainable ideas.</p>
    </div>

    <div class="card">
      <h2>Create Thread</h2>
      <form method="post" action="<c:url value='/forum'/>">
        <label>Title</label>
        <input name="title" required maxlength="150"/>
        <label>Your name</label>
        <input name="createdBy" required maxlength="80"/>
        <label>First post</label>
        <textarea name="content" required></textarea>
        <p style="margin-top:12px;"><button class="btn" type="submit">Create</button></p>
      </form>
    </div>

    <div class="card">
      <h2>Threads</h2>
      <c:forEach var="t" items="${threads}">
        <div class="card" style="margin:10px 0;">
          <a class="btn" href="<c:url value='/thread?id=${t.threadId}'/>">Open</a>
          <div style="margin-top:10px;">
            <b>${t.title}</b>
            <div class="small">By ${t.createdBy} • Posts: ${t.postCount} • ${t.createdAt}</div>
          </div>
        </div>
      </c:forEach>
    </div>

  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>