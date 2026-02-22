<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Carbon Calculator</title>
  <link rel="stylesheet" href="<c:url value='/assets/styles.css'/>" />
</head>
<body>
  <jsp:include page="/WEB-INF/views/_nav.jsp"/>
  <div class="container">

    <div class="card">
      <h1>Carbon Footprint Calculator</h1>
      <p class="small">Simple calculator (client-side) for Release 1. You can later store results in DB.</p>

      <label>Monthly electricity (kWh)</label>
      <input id="kwh" type="number" min="0" value="300"/>

      <label>Monthly car travel (km)</label>
      <input id="car" type="number" min="0" value="200"/>

      <label>Monthly flights (hours)</label>
      <input id="flights" type="number" min="0" value="0"/>

      <p style="margin-top:14px;">
        <button class="btn" onclick="calc()">Calculate</button>
      </p>

      <div class="card" id="resultBox" style="display:none;">
        <h2>Result</h2>
        <div id="resultText"></div>
        <p class="small">Formula (simple demo): kWh*0.4 + km*0.2 + hours*90</p>
      </div>
    </div>

  </div>

<script>
function calc(){
  const kwh = parseInt(document.getElementById('kwh').value || '0', 10);
  const car = parseInt(document.getElementById('car').value || '0', 10);
  const flights = parseInt(document.getElementById('flights').value || '0', 10);

  const total = Math.round(kwh*0.4 + car*0.2 + flights*90);

  document.getElementById('resultBox').style.display = 'block';
  document.getElementById('resultText').innerText =
    "Estimated monthly footprint: " + total + " kg CO₂";
}
</script>

  <jsp:include page="/WEB-INF/views/_footer.jsp"/>
</body>
</html>