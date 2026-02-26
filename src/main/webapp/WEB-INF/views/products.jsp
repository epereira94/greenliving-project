<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>GreenLiving - Products</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
  <style>
    .stars { font-size: 14px; letter-spacing: 1px; }
    .stars .on { color: #f4b400; }   /* gold */
    .stars .off { color: #cfcfcf; }  /* grey */
  </style>
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>

  <div class="container">
    <div class="card">
      <div style="display:flex; justify-content:space-between; align-items:center; gap:12px; flex-wrap:wrap;">
        <div>
          <h1 style="margin:0;">Eco-Friendly Products</h1>
          <div class="small" style="margin-top:6px;">
            <a href="<c:url value='/'/>">← Back to Home</a>
          </div>
        </div>
        <div>
          <a class="btn" href="<c:url value='/add-product'/>">+ Add Product</a>
        </div>
      </div>
    </div>

    <c:choose>
      <c:when test="${empty products}">
        <div class="card">No products found.</div>
      </c:when>

      <c:otherwise>
        <c:forEach var="p" items="${products}">
          <div class="card">
            <span class="badge">${p.category}</span>

            <h3 style="margin:10px 0 6px;">${p.name}</h3>

            <div class="small" style="margin:0 0 6px;">
              Eco score: <b>${p.ecoScore}</b>
            </div>

            <!-- Rating summary -->
            <c:set var="summary" value="${ratingMap[p.productId]}" />
            <div class="small" style="margin:0 0 10px;">
              Rating:
              <c:choose>
                <c:when test="${summary == null || summary.count == 0}">
                  No ratings yet
                </c:when>
                <c:otherwise>
                  <!-- round average to nearest int using +0.5 trick -->
                  <c:set var="rounded" value="${summary.average + 0.5}" />
                  <c:set var="stars" value="${rounded - (rounded % 1)}" />

                  <span class="stars" aria-label="Rating">
                    <c:forEach begin="1" end="5" var="i">
                      <c:choose>
                        <c:when test="${i <= stars}">
                          <span class="on">★</span>
                        </c:when>
                        <c:otherwise>
                          <span class="off">☆</span>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </span>
                  <span> (${summary.count})</span>
                </c:otherwise>
              </c:choose>
            </div>

            <div class="hr"></div>

            <div style="margin:0 0 10px;">${p.description}</div>

            <a class="btn" href="<c:url value='/product'><c:param name='id' value='${p.productId}'/></c:url>">
              View details
            </a>
          </div>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </div>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>