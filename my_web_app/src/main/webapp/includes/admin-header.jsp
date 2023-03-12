<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 31-Jan-23
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <li><a href="${pageContext.request.contextPath}/AdminPanel" class="${pageName eq 'home' ? 'active' : ''}">Home</a></li>
            <li><a target="_blank" href="${pageContext.request.contextPath}/admin/release-product" class="${pageName eq 'releaseProduct' ? 'active' : ''}">Release New Product</a></li>
            <li><a target="_blank" href="${pageContext.request.contextPath}/admin/add-batch" class="${pageName eq 'addBatch' ? 'active' : ''}">Add New Batch</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/generate-qr-code" class="${pageName eq 'generateQr' ? 'active' : ''}">Generate QR Code</a></li>
            <li><a target="_blank" href="${pageContext.request.contextPath}/add-user?role=Distributor" class="${pageName eq 'addDistributor' ? 'active' : ''}">Add Distributor</a></li>
            <li><a target="_blank" href="${pageContext.request.contextPath}/admin/assign-product" class="class="${pageName eq 'assignProduct' ? 'active' : ''}"">Assign Product</a></li>


            <div class="acc">
                <li><a href="${pageContext.request.contextPath}/user-profile" class="${pageName eq 'profile' ? 'active' : ''}">Account</a></li>
            </div>
        </ul>
    </nav>
</header>
<main>
