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
    <title>Release New Product</title>
    <style>
        <%@include file="../assets/form.css"%>
    </style>
</head>
<body>
<form id="company-form" method="post" action="${pageContext.request.contextPath}/admin/release-product">
    <div class="form-group">
        <label for="product-name">Product Name:</label>
        <input type="text" id="product-name" name="product-name" pattern="[a-zA-Z '&-]+" required>
    </div>
    <div class="form-group">
        <label for="product-code">Product Code:</label>
        <input type="text" id="product-code" name="product-code" pattern="[A-Z0-9]+" required>
    </div>
    <div class="form-group">
        <label for="table-name">Table Name:</label>
        <input type="text" id="table-name" name="table-name" pattern="[a-z_]+" required>
    </div>
    <div class="form-group form-actions">
        <button type="button" onclick="window.history.back()">Cancel</button>
        <button type="submit">Submit</button>
    </div>
</form>

</body>
</html>
