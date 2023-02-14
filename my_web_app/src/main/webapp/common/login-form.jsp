<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 2/14/2023
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <style>
       <%@include file="../assets/login-form.css"%>
   </style>
</head>
<body>
<form method="post" action="log-in">
    <h2>Login</h2>
    <label for="email">email:</label>
    <input type="text" id="email" name="email" placeholder="Enter email" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Enter password" required>
    <button type="submit">Login</button>
</form>
</body>
</html>

