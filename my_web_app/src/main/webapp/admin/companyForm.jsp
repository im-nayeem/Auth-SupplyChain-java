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
        <%@include file="../assets/companyForm.css"%>
    </style>
</head>
<body>
<form id="company-info-form" method="post" action="${pageContext.request.contextPath}/admin/storeCompanyInfo">
    <div class="form-group">
        <label for="company-name">Company Name:</label>
        <input type="text" id="company-name" name="company-name" pattern="[a-zA-Z ]+" required>
    </div>
    <div class="form-group">
        <label for="supplier-code">Supplier Code:</label>
        <input type="text" id="supplier-code" name="supplier-code" pattern="[A-Z]+" required>
    </div>
    <div class="form-group">
        <label for="distributor-code">Distributor Code:</label>
        <input type="text" id="distributor-code" name="distributor-code" pattern="[A-Z]+" required>
    </div>
    <div class="form-group">
        <label for="seller-code">Seller Code:</label>
        <input type="text" id="seller-code" name="seller-code" pattern="[A-Z]+" required>
    </div>
    <div class="form-group form-actions">
        <button type="button" onclick="window.history.back()">Cancel</button>
        <button type="submit">Submit</button>
    </div>
</form>

</body>
</html>
