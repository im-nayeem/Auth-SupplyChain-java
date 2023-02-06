<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 04-Feb-23
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/includes/admin-header.jsp"%>
<style>
    <%@include file="/assets/all-batch.css"%>
</style>
<h4>Select Batch of Products To Generate QR Code:</h4>
<table id="batchList">
    <thead>
    <tr>
        <th>Batch ID</th>
        <th>Product Code</th>
        <th>Total Product</th>
        <th>Manufacturing Date</th>
        <th>Expire Date</th>
        <th>Warranty Year</th>
        <th>Warranty Month</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${batchList}" var="batch">
        <tr>
            <td><a href="./generate-qr-code?batch=${batch.getBatchId()}">${batch.getBatchId()}</a></td>
            <td>${batch.getProductCode()}</td>
            <td>${batch.getTotalProduct()}</td>
            <td>${batch.getManufacDate()}</td>
            <td>${batch.getExpDate()}</td>
            <td>${batch.getWarrantyYear()}</td>
            <td>${batch.getWarrantyMonth()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="/includes/admin-footer.jsp"%>
