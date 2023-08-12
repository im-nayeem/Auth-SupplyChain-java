<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 13-Mar-23
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="/includes/admin-header.jsp"%>
<style>
   <%@include file="/assets/css/list-items.css"%>
</style>
<h1>Product List</h1>

<table>
    <thead>
    <tr>
        <th>Product Name</th>
        <th>Product Code</th>
        <th>Having Warranty</th>
        <th>Having Expiration</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productMapList}" var="product">
            <td>${product.getProductName()}</td>
            <td><a href="${pageContext.request.contextPath}/admin/all-batch?productCode=${product.getProductCode()}">${product.getProductCode()}</a></td>
            <td>${product.getHaveWarranty()}</td>
            <td>${product.getHaveExpiration()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@include file="/includes/admin-footer.jsp"%>
