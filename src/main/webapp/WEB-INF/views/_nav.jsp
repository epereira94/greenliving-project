<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="banner">
  <h1 style="margin:0;">
    Welcome to GreenLiving
    <span class="leaf" aria-hidden="true">
      <svg viewBox="0 0 64 64" width="18" height="18" style="vertical-align:-3px;">
        <!-- stem -->
        <path fill="currentColor" d="M31 56c0-16 2-26 10-36 1-1 3 0 2 2-7 10-9 19-9 34 0 2-3 2-3 0z"/>
        <!-- left leaf -->
        <path fill="currentColor" d="M26 20c-9 2-14 10-13 18 10 1 18-5 20-13 1-3-2-6-7-5z"/>
        <!-- right leaf -->
        <path fill="currentColor" d="M40 10c-8 3-12 11-10 18 10 0 18-7 19-16 0-3-4-4-9-2z"/>
      </svg>
    </span>
  </h1>
</div>

<div class="nav">
  <div class="container">
    <a href="<c:url value='/'/>"><b>Home</b></a>
    <a href="<c:url value='/tips'/>">Tips</a>
    <a href="<c:url value='/products'/>">Products</a>
    <a href="<c:url value='/forum'/>">Forum</a>
    <a href="<c:url value='/news'/>">News</a>
    <a href="<c:url value='/carbon'/>">Carbon Calculator</a>
    <a href="<c:url value='/rewards'/>">Rewards</a>
  </div>
</div>