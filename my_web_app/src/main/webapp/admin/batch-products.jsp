<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 13-Mar-23
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/includes/admin-header.jsp"%>
<style>
    <%@include file="/assets/list-items.css"%>
</style>
<div class="main-content">
<h1>Batch Products</h1>
    <h3>All Products of Batch - ${productBatch.getBatchId()}</h3>
    <table class="first-tab">
        <thead class="first">
        <tr>
            <th>Product Name</th>
            <th>Total Products</th>
            <th>Warranty</th>
            <th>Manufacturing Date</th>
            <th>Expire Date</th>
        </tr>
        </thead>
        <tr>
            <td>${productMap.getProductName()}</td>
            <td>${productBatch.getTotalProduct()}</td>
            <td>${productBatch.getWarrantyYear()}Years,${productBatch.getWarrantyMonth()}Months</td>
            <td>${productBatch.getManufacDate()}</td>
            <c:if test="${productMap.getHaveExpiration()=='yes'}">
               <td>${productBatch.getExpDate()}</td>
            </c:if>
            <c:if test="${productMap.getHaveExpiration()=='no'}">
                 <td>N/A</td>
            </c:if>
        </tr>
    </table>
<h4>Select Product and Holder To Get Detailed Info:</h4>
    <table>
    <thead>
    <tr>
        <th>Product ID</th>
        <th>Product Status</th>
        <th>Last Handover Date</th>
        <th>Last Holder</th>
        <th>Distributor</th>
        <th>Supplier</th>
        <th>Seller</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList}" var="product">
        <td><a target="_blank" href="${pageContext.request.contextPath}/view-product?pid=${product.getProductId()}">${product.getProductId()}</a></td>
        <td>${product.getStatus()}</td>
        <td>${product.getSoldDate()}</td>
        <td><a href="${pageContext.request.contextPath}/admin/user-info?uid=${product.getLastHolder()}">${product.getLastHolder()}</a></td>
        <td></td>
        <td></td>
        <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<%@include file="/includes/admin-footer.jsp"%>
