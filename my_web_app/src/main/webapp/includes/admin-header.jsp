<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 31-Jan-23
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
    <style>
        <%@include file="../assets/admin.css"%>
    </style>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/AdminPanel" style="background-color: #4CAF50">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/release-product">Release New Product</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/add-batch">Add New Batch</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/generate-qr-code">Generate QR Code</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/add-distributor">Add Distributor</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/assign-product">Assign Product</a></li>


            <div class="acc">
                <li><a href="#">Account</a></li>
            </div>
        </ul>
    </nav>
</header>
<main>
