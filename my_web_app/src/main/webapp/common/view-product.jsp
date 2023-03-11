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
  <a href="${pageContext.request.contextPath}/Init"><h3>${company.getName()}</h3></a>

</div>

<div id="content">

  <c:if test="${sessionScope.get('admin')==null and product.getStatus()!='sold'}">
      <h4>This type of Product is produced by our company. But it is not sure still this is original. <br> This can be confirmed and details can be seen when seller mark it as sold after customer's confirmation to buy.</h4>
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

<c:if test="${product.getStatus()=='sold' && sessionScope.get('admin')==null}">
          <tr>
              <td><strong>Sold Date</strong></td>
              <td class="info">${product.getSoldDate()}</td>
          </tr>
        <tr>
            <td><strong>Sold By:</strong></td>
            <td class="info">${seller.getName()}</td>
        </tr>
        <tr>
            <td><strong>Seller Name:</strong></td>
            <td class="info">${seller.getShopName()}</td>
        </tr>
        <tr>
            <td><strong>Shop Address:</strong></td>
            <td class="info">${seller.getShopRoad()},<br>${seller.getAddress().getUnion()},${seller.getAddress().getUpazila()},${seller.getAddress().getDistrict()}</td>
        </tr>
       <c:if test="${productMap.getHaveWarranty()=='yes'}">
           <tr>
               <td><strong>Warranty Year:</strong></td>
               <td class="info">${productBatch.getWarrantyYear()}</td>
           </tr>
           <tr>
               <td><strong>Warranty Month:</strong></td>
               <td class="info">${productBatch.getWarrantyMonth()}</td>
           </tr>
           <tr>
               <td><strong>Remaining Warranty</strong></td>
                   <td>${product.getRemWarranty()} days</td>
           </tr>
       </c:if>
        <c:if test="${productMap.getHaveExpiration()=='yes'}">
            <tr>
                <td><strong>Manufactured Date:</strong></td>
                <td class="info">${productBatch.getManufacDate()}</td>
            </tr>
            <tr>
                <td><strong>Expire Date:</strong></td>
                <td class="info">${productBatch.getExpDate()}</td>
            </tr>
        </c:if>
</c:if>
<c:if test="${sessionScope.get('admin')!=null}">
    <tr>
        <td><strong>Last Holder's Name:</strong></td>
        <td class="info">${lastHolder.getName()}</td>
    </tr>
    <tr>
        <td><strong>Last Holder's Role:</strong></td>
        <td class="info">${lastHolder.getRole()}</td>
    </tr>
    <tr>
        <td><strong>Last Holder's NID:</strong></td>
        <td class="info"><a href="${pageContext.request.contextPath}/user-view?uid=${lastHolder.getNid()}">${lastHolder.getNid()}</a></td>
    </tr>
    <tr>
        <td><strong>Last Holder's Address:</strong></td>
        <td class="info">${seller.getAddress().getUnion()},${seller.getAddress().getUpazila()},${seller.getAddress().getDistrict()}</td>
    </tr>
</c:if>
  </table>
</div>

<div id="footer">
  <p>Copyright &copy; 2023</p>
</div>

</body>
</html>

