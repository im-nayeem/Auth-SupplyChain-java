<%--
  Created by IntelliJ IDEA.
  User: hnaye
  Date: 2/14/2023
  Time: 9:46 PM
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
        <%@include file="../assets/login-form.css"%>
    </style>
</head>
<body>
<form method="post" action="update-password">
    <h2>Update Password</h2>
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" placeholder="Enter email" readonly value="${email}">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Enter password">
    <label for="confirm-password">Confirm Password:</label>
    <input type="password" id="confirm-password" name="confirm-password" required oninput="checkPasswordMatch()">
    <button type="submit">Update</button>
</form>

<script>
    // function to check if password in both password and confirm-password field are same
    function checkPasswordMatch() {
        const password = document.getElementById("password");
        const confirm_password = document.getElementById("confirm-password");

        if (password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords do not match");

        } else {
            confirm_password.setCustomValidity("");
        }
    }
</script>
</body>
</html>