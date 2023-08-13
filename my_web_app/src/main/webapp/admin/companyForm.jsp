<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 01-Feb-23
  Time: 11:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company Info</title>
    <style>
        <%@include file="../assets/css/form.css"%>
    </style>
</head>
<body>
<form id="company-form" method="post" action="${pageContext.request.contextPath}/admin/storeCompanyInfo">
    <div class="form-group">
        <label for="company-name">Company Name:</label>
        <input type="text" id="company-name" name="company-name" pattern="[a-zA-Z ]+" required>
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="phone-number">Phone Number:</label>
        <input type="tel" id="phone-number" name="phone-number" pattern="[0-9]+" required>
    </div>

    <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required>
    </div>

    <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" maxlength="255" required></textarea>
    </div>

    <div class="form-group">
        <label for="logo-url">Logo URL:</label>
        <input type="url" id="logo-url" name="logo-url">
    </div>
    <div class="form-group form-actions">
        <button type="button" onclick="window.history.back()">Cancel</button>
        <button type="submit">Submit</button>
    </div>
</form>

</body>
</html>
