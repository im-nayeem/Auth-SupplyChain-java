<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 05-Feb-23
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Information</title>
  <style>
    <%@include file="/assets/view-product.css"%>
  </style>
</head>
<body>
<div id="header">
<%--  <h2>${sessionScope.get("company").getName()}</h2>--%>
    <h5>ABC Company</h5>

</div>

<div id="content">

  <c:if test="${product.getStatus()!='sold'}">
      <h3>This Product is produced by our company. But it is not sure still this is original. <br> This can be confirmed and details can be seen when seller mark it as sold after customer's confirmation to buy.</h3>
  </c:if>

  <table id="productInfo" class="responsive-table">
    <tr>
      <td><strong>Product Name:</strong></td>
      <td class="info">${productMap.getProductName()}</td>
    </tr>
    <tr>
      <td><strong>Product ID:</strong></td>
      <td class="info">${product.getProductId()}</td>
    </tr>
    <tr>
      <td><strong>Product Batch:</strong></td>
      <td class="info">${product.getBatchId()}</td>
    </tr>
      <tr>
          <td><strong>Status</strong></td>
          <td class="info">${product.getStatus()}</td>
      </tr>
      <tr>
          <td><strong>Sold Date</strong></td>
          <td class="info">2023-02-17</td>
      </tr>
<c:if test="${product.getStatus()=='sold'}">
    <tr>
        <td><strong>Seller Name:</strong></td>
        <td class="info">Seller</td>
    </tr>
    <tr>
        <td><strong>Shop Name:</strong></td>
        <td class="info">AB Shop</td>
    </tr>
    <tr>
        <td><strong>Shop Address:</strong></td>
        <td class="info">Kotbari,Cumilla</td>
    </tr>
    <tr>
      <td><strong>Manufactured Date:</strong></td>
      <td class="info">${productBatch.getManufacDate()}</td>
    </tr>
<%--    <tr>--%>
<%--      <td><strong>Expire Date:</strong></td>--%>
<%--      <td class="info">${productBatch.getExpDate()}</td>--%>
<%--    </tr>--%>
    <tr>
      <td><strong>Warranty Year:</strong></td>
      <td class="info">${productBatch.getWarrantyYear()}</td>
    </tr>
    <tr>
      <td><strong>Warranty Month:</strong></td>
      <td class="info">${productBatch.getWarrantyMonth()}</td>
    </tr>
    <tr>
      <td><strong>Remaining Warranty:</strong></td>
      <td class="info">12 months</td>
    </tr>
</c:if>
  </table>
</div>

<div id="footer">
  <p>Copyright &copy; 2023</p>
</div>

</body>
</html>

