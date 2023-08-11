<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 2/14/2023
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<c:if test="${role=='admin'}">
    <form method="post" action="AdminLogin">
</c:if>
<c:if test="${role!='admin'}">
    <form method="post" action="LogIn">
</c:if>

    <h2>Login</h2>

<%--    show error if any error arises in login--%>
    <div id="error">
     <p>${error}</p>
    </div>

    <label for="email">E-mail:</label>
    <input type="email" id="email" name="email" placeholder="Enter email" required autofocus>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Enter password" required>
    <button type="submit">Login</button>
</form>
</body>
</html>

