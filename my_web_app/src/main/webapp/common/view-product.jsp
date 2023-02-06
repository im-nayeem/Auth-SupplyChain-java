<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 05-Feb-23
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Product Information</title>
  <style>
    <%@include file="/assets/product-info.css"%>
  </style>
</head>
<body>
<div id="header">
  <h2>Product Information</h2>
</div>

<div id="content">
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
      <td><strong>Manufactured Date:</strong></td>
      <td class="info">${productBatch.getManufacDate()}</td>
    </tr>
    <tr>
      <td><strong>Expire Date:</strong></td>
      <td class="info">${productBatch.getExpDate()}</td>
    </tr>
    <tr>
      <td><strong>Warranty Year:</strong></td>
      <td class="info">${productBatch.getWarrantyYear()}</td>
    </tr>
    <tr>
      <td><strong>Warranty Month:</strong></td>
      <td class="info">${productBatch.getWarrantyMonth()}</td>
    </tr>
    <tr>
      <td><strong>Status:</strong></td>
      <td class="info">${product.getStatus()}</td>
    </tr>
    <tr>
      <td><strong>Holder:</strong></td>
      <td class="info">${product.getLastHolder()}</td>
    </tr>
    <tr>
      <td><strong>Remaining Warranty:</strong></td>
      <td class="info">11 months</td>
    </tr>
  </table>
</div>

<div id="footer">
  <p>Copyright &copy; 2023</p>
</div>

</body>
</html>

