<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 13-Mar-23
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Product ID Form</title>
  <style>
    form {
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      border: 2px solid #ccc;
      border-radius: 5px;
      box-shadow: 0 0 5px #ccc;
      font-family: Arial, sans-serif;
    }
    label {
      display: block;
      margin-bottom: 5px;
      font-size: 18px;
    }
    input[type="number"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 2px solid #ccc;
      border-radius: 5px;
      font-size: 16px;
    }
    input[type="submit"] {
      display: block;
      background-color: #4CAF50;
      color: white;
      padding: 10px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 18px;
    }
    input[type="submit"]:hover {
      background-color: #3e8e41;
    }
  </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/user-info" method="get">
  <label for="uid">Vendor's NID:</label>
  <input type="number" id="uid" name="uid" required>
  <input type="submit" value="Submit">
</form>
</body>
</html>




