<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 31-Jan-23
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body{
            background-image: url("https://cdn-icons-png.flaticon.com/512/5741/5741333.png");
            background-size: auto;
            background-repeat: no-repeat;
            background-position: center center;
        }
        .error-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .error-message {
            font-size: 25px;
            font-weight: bold;
            color: #090909;
            background-color: #888888;

        }

        .error-code {
            font-size: 70px;
            font-weight: bold;
            color: #F00;
        }
    </style>
    <title>Error!</title>
</head>
<body>
<div class="error-container">
<%--    <div class="error-code">Error Found!</div>--%>
    <div class="error-message">${error}</div>
</div>
</body>
</html>

