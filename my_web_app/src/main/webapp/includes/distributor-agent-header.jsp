<%--
  Created by IntelliJ IDEA.
  User: hnaye
  Date: 2/15/2023
  Time: 1:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file="../assets/css/style.css"%>
    </style>
    <script>
        <%@include file="../assets/js/responsive.js"%>
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="head">
    <h2>Distributor Agent Panel</h2>
</div>
<div class="topnav" id="myTopnav">
    <a href="${pageContext.request.contextPath}/DistributorAgentPanel" class="${pageName == 'home' || pageName == null ? 'active' : ''}"
    >Home</a>
    <a href="${pageContext.request.contextPath}/user/product-affiliation" class="${pageName eq 'my-products' ? 'active' : ''}">My Products</a>
    <a href="${pageContext.request.contextPath}/distributorAgent/distribute-to-seller">Distribute Products To Seller</a>
    <a href="${pageContext.request.contextPath}/add-user?role=Seller">Add Seller</a>
    <a href="${pageContext.request.contextPath}/user-profile?uid=${user.getNid()}" class="account-link">Account</a>

    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
    </a>
</div>