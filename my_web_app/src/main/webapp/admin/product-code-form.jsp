<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 11-Aug-23
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add New Batch of Product</title>
  <style>
    <%@include file="../assets/css/form.css"%>
  </style>
</head>
<body>
<form id="company-form" method="get" action="${pageContext.request.contextPath}/admin/add-batch">
  <div class="form-group">
    <label for="product-code">Product Code:</label>
    <input type="text" id="product-code" name="product-code"  pattern="[A-Z0-9]+" required>
  </div>
  <div class="form-group form-actions">
    <button type="button" onclick="window.history.back()">Cancel</button>
    <button type="submit">Submit</button>
  </div>

</form>

</body>
</html>

