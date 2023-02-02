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
        <label for="having-warranty">Having Warranty:</label>
        <input type="radio" id="having-warranty-yes" name="having-warranty" value="yes" required>
        <label for="having-warranty-yes">Yes</label>
        <input type="radio" id="having-warranty-no" name="having-warranty" value="no" required>
        <label for="having-warranty-no">No</label>
    </div>
    <div class="form-group">
        <label for="having-expired-date">Having Expired Date:</label>
        <input type="radio" id="having-expired-date-yes" name="having-expired-date" value="yes" required>
        <label for="having-expired-date-yes">Yes</label>
        <input type="radio" id="having-expired-date-no" name="having-expired-date" value="no" required>
        <label for="having-expired-date-no">No</label>
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
