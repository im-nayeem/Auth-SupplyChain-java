<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 13-Aug-23
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Update Password</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        <%@include file="../assets/css/login-form.css"%>
    </style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/admin/update-password">
    <h2>Update Password</h2>
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" placeholder="Enter email" readonly value="${admin.getEmail()}">
    <label for="old-password">Old Password:</label>
    <input type="password" id="old-password" name="old-password" placeholder="Enter Old password">
    <label for="password">New Password:</label>
    <input type="password" id="password" name="password" placeholder="Enter password" required>
    <label for="confirm-password">Confirm New Password:</label>
    <input type="password" id="confirm-password" name="confirm-password" required>
    <button type="submit">Update</button>
</form>
<script>
    <%@include file="/assets/js/password-checker.js"%>
</script>
</body>
</html>