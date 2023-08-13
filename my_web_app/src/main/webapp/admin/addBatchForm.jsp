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
  <title>Add New Batch of Product</title>
  <style>
    <%@include file="../assets/css/form.css"%>
  </style>
</head>
<body>
<form id="company-form" method="post" action="${pageContext.request.contextPath}/admin/add-batch">
  <div class="form-group">
    <label for="product-code">Product Code:</label>
    <input type="text" id="product-code" name="product-code" value="${productMap.getProductCode()}" pattern="[A-Z0-9]+" required>
  </div>
  <div class="form-group">
    <label for="batch-id">Batch ID:</label>
    <input type="text" id="batch-id" name="batch-id" pattern="B[0-9]+[A-Z]+" required>
  </div>
  <div class="form-group">
    <label for="total-product">Total Product:</label>
    <input type="number" id="total-product" name="total-product" pattern="[1-9]+" required>
  </div>
  <div class="form-group">
    <label for="manufacturing-date">Manufacturing Date:</label>
    <input type="date" id="manufacturing-date" name="manufacturing-date" required>
  </div>
  <c:if test="${productMap.getHaveExpiration().equals('yes')}">
    <div class="form-group">
      <label for="expire-date">Expire Date:</label>
      <input type="date" id="expire-date" name="expire-date" required>
    </div>
  </c:if>

  <c:if test="${productMap.getHaveWarranty().equals('yes')}">
    <div class="form-group">
      <label for="warranty-year">Warranty Year:</label>
      <input type="number" id="warranty-year" name="warranty-year" min="0" required>
    </div>
    <div class="form-group">
      <label for="warranty-month">Warranty Month:</label>
      <input type="number" id="warranty-month" name="warranty-month" min="0" max="11" required>
    </div>
  </c:if>

  <div class="form-group form-actions">
    <button type="button" onclick="window.history.back()">Cancel</button>
    <button type="submit">Submit</button>
  </div>

</form>

</body>
</html>
