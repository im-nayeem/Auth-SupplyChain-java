<%--
  Created by IntelliJ IDEA.
  User: hnaye
  Date: 2/15/2023
  Time: 1:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    <%@include file="../assets/style.css"%>
  </style>
  <script>
    <%@include file="../assets/responsive.js"%>
  </script>
</head>
<body>
<div class="head">
  <h2>distributorAgent Panel</h2>
</div>
<div class="topnav" id="myTopnav">
  <a href="${pageContext.request.contextPath}/DistributorAgentPanel" class="active">Home</a>
  <a href="#">My Products</a>
  <a href="${pageContext.request.contextPath}/distributorAgent/distribute-to-seller">Distribute Products To Seller</a>
  <a href="${pageContext.request.contextPath}/add-user?role=Seller">Add Seller</a>
  <a href="${pageContext.request.contextPath}/user-profile?uid=${user.getNid()}" class="account-link">Account</a>

  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
    <i class="fa fa-bars"></i>
  </a>
</div>

<div class="main-content distributorAgent">

</div>
<footer>
  <p>Privacy & Policy | Contact Us | Copyright&copy;2023</p>
</footer>

</body>
</html>

