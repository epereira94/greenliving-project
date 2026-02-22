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
    <div class="card" style="text-align:center;">
      <h2 style="margin-top:6px;">Sustainable Living Starts Here</h2>
    </div>
  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>