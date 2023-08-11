<%--
  Created by IntelliJ IDEA.
  User: nayeem11908002
  Date: ১৫/২/২৩
  Time: ৭:১১ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Assign Product</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        <%@include file="../assets/login-form.css"%>
    </style>
</head>
<body>
    <c:if test="${role=='admin'}">
         <form method="post" action="assign-product">
    </c:if>
    <c:if test="${role=='distributor'}">
        <form method="post" action="distribute-product">
    </c:if>
    <c:if test="${role=='distributorAgent'}">
        <form method="post" action="distribute-to-seller">
    </c:if>


    <h2>Assign Product</h2>
        <input type="hidden" value="${uid}" name="uid">

        <label for="first-product">First Product ID:</label>
        <input type="text" id="first-product" name="first-product" placeholder="Enter First Product ID" pattern="([A-Z]+[0-9]+)" required>
        <label for="last-product">Last Product ID:</label>
        <input type="text" id="last-product" name="last-product" placeholder="Enter Last Product Code" pattern="([A-Z]+[0-9]+)" required>

        <label for="new-holder-nid">Vendor's NID Number:</label>
        <input type="number" min="1000000" maxlength="11" id="new-holder-nid" name="new-holder-nid" placeholder="Product Receiver's NID" required>

    <button type="submit">Assign</button>
</form>
</body>
</html>

