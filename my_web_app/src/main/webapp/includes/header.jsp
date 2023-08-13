<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 11-Mar-23
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <h2>${company.getName()}</h2>
</div>
<div class="topnav" id="myTopnav">
    <a href="./" class="${pageName eq 'home' ? 'active' : ''}">Home</a>
    <a href="${pageContext.request.contextPath}/view-product">View Product Info</a>
    <a href="#">About Us</a>

    <c:if test="${sessionScope.get('user')==null}">
        <a href="${pageContext.request.contextPath}/LogIn" class="account-link">Log-in</a>
    </c:if>
    <c:if test="${sessionScope.get('user')!=null}">
        <a href="${pageContext.request.contextPath}/user-profile" class="account-link ${pageName eq 'profile' ? 'active' : ''}">Account</a>
    </c:if>

    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
    </a>
</div>
