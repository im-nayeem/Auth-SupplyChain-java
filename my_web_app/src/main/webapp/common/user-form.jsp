<%--
  Created by IntelliJ IDEA.
  User: nayeem11908002
  Date: ১১/২/২৩
  Time: ১১:২১ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Add ${role}</title>
    <style>
        <%@include file="../assets/user-form.css"%>
    </style>
</head>
<body>
<form class="form-control" method="post" action="add-user">
    <input type="hidden" value="${role}" name="role">
    <h2>Personal Information:</h2>
    <hr>
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required pattern="[a-z A-Z]+">
    </div>
    <div class="form-group">
        <label for="nid">NID Number:</label>
        <input type="number" min="1000000" maxlength="11" id="nid" name="nid" required>
    </div>
    <div class="form-group">
        <label for="confirm-nid">Confirm NID Number:</label>
        <input type="number" min="1000000" maxlength="11" id="confirm-nid" name="confirm-nid" required oninput="checkNidMatch()">
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <c:if test="${role=='Seller'}">
        <h2>Shop Location: </h2>
    </c:if>
    <c:if test="${role=='Distributor'}">
        <h2>Distribution Center Location: </h2>
    </c:if>
    <c:if test="${role=='Supplier'}">
        <h2>Working Area: </h2>
    </c:if>
    <c:if test="${role=='Admin'}">
        <h2>Address: </h2>
    </c:if>
    <hr>
    <div class="form-group">
        <label for="division">Division:</label>
        <select id="division" name="division" required>
            <option value="" selected>--Select Division--</option>
            <c:forEach var="division" items="${divisions}">
                <option value="${division.getId()}">${division.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="district">District:</label>
        <select id="district" name="district" required>
            <option value="" selected>--Select District--</option>
            <c:forEach var="district" items="${districts}">
                <option value="${district.getId()}">${district.getName()}-${divisions.get(district.getParentId()-1).getName()}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="upazila">Upazila:</label>
        <select id="upazila" name="upazila">
            <option value="" selected>--Select Upazila--</option>
            <c:forEach var="upazila" items="${upazilas}">
                <option value="${upazila.getId()}">${upazila.getName()}-${districts.get(upazila.getParentId()-1).getName()}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="union">Union:</label>
        <select id="union" name="union">
            <option value="" selected>--Select Union--</option>
            <c:forEach var="union" items="${unions}">
                <option value="${union.getId()}">${union.getName()}-${upazilas.get(union.getParentId()-1).getName()}</option>
            </c:forEach>
        </select>
    </div>
    <c:if test="${role=='Seller'}">
        <div class="form-group">
            <label for="shop-road">Shop Road:</label>
            <input type="text" id="shop-road" name="shop-road" required pattern="[a-z A-Z,0-9]+">
        </div>
    </c:if>
    <c:if test="${role=='Distributor'}">
        <div class="form-group">
            <label for="dcenter-road">Distribution Center Road:</label>
            <input type="text" id="dcenter-road" name="dcenter-road" required pattern="[a-z A-Z,0-9]+">
        </div>
    </c:if>

    <div class="form-group">
        <input type="submit" value="Submit">
    </div>
</form>


<script>
    // function to check if NID in both nid and confirm-nid field are same
    function checkNidMatch() {
        const nid = document.getElementById("nid");
        const confirm_nid = document.getElementById("confirm-nid");

        if (nid.value !== confirm_nid.value) {
            confirm_nid.setCustomValidity("NID do not match! It can't be changed in future.");

        } else {
            confirm_nid.setCustomValidity("");
        }
    }
</script>
</body>
</html>
