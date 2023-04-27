<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 2/15/2023
  Time: 1:06 AM
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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <script>
        <%@include file="../assets/responsive.js"%>
    </script>
</head>
<body>
<div class="head">
        <h2>Distributor Panel</h2>
</div>
<div class="topnav" id="myTopnav">
    <a href="${pageContext.request.contextPath}/DistributorPanel" class="active">Home</a>
    <a href="#">My Products</a>
    <a href="${pageContext.request.contextPath}/distributor/distribute-product">Distribute Products</a>
    <a href="#">Sell Products</a>
    <a href="${pageContext.request.contextPath}/add-user?role=DistributorAgent">Add DistributorAgent</a>
    <a href="${pageContext.request.contextPath}/user-profile?uid=${user.getNid()}" class="account-link">Account</a>

    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
    </a>
</div>

<div class="main-content distributor">
    <section>
        <h2>Product Statistics</h2>
        <canvas id="product-chart"></canvas>
        <script>
            <%@include file="../assets/seller-dashboard.js"%>
        </script>
    </section>
</div>
<footer>
    <p>Privacy & Policy | Contact Us | Copyright&copy;2023</p>
</footer>

</body>
</html>

