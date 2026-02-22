<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Rewards</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>
  <div class="container">

    <div class="card">
      <h1>Rewards Program</h1>
      <p class="small">Log eco-friendly actions to earn points (simple demo for Release 1).</p>

      <form method="get" action="<c:url value='/rewards'/>">
        <label>User name</label>
        <input name="user" value="${userName}" placeholder="e.g. Estevan"/>
        <p style="margin-top:12px;"><button class="btn" type="submit">View Points</button></p>
      </form>

      <c:if test="${not empty userName}">
        <div class="card">
          <h2>${userName}'s Points: ${points}</h2>
        </div>
      </c:if>
    </div>

    <div class="card">
      <h2>Log an Action</h2>
      <form method="post" action="<c:url value='/rewards'/>">
        <label>User name</label>
        <input name="userName" required value="${userName}" />
        <label>Action</label>
        <select name="actionId" required>
          <c:forEach var="a" items="${actions}">
            <option value="${a.actionId}">${a.actionName} (+${a.points})</option>
          </c:forEach>
        </select>

        <p style="margin-top:12px;"><button class="btn" type="submit">Add Points</button></p>
      </form>
    </div>

  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>