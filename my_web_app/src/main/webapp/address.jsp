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
    <title>Location Input Form</title>
    <style type="text/css">
        .form-control {
            width: 50%;
            margin: 20px auto;
            padding: 10px;
            display: block;
            font-size: 16px;
            border-radius: 10px;
            border: 1px solid gray;
        }

        .form-group {
            text-align: center;
        }

        .form-group label {
            display: block;
            margin-bottom: 10px;
            font-size: 18px;
        }

        .form-group select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 10px;
            border: 1px solid gray;
        }

        .form-group input[type="submit"] {
            width: 20%;
            padding: 10px;
            background-color: green;
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 18px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<form class="form-control">
    <div class="form-group">
        <label for="division">Division:</label> <select id="division"
                                                        name="division">
        <c:forEach var="division" items="${divisions}">
            <option value="${division.getId()}">${division.getName()}</option>
        </c:forEach>
    </select>
    </div>
    <div class="form-group">
        <label for="district">District:</label>
        <select id="district" name="district">
        </select>
    </div>

    <script>
        let divisions = ${divisions}; // an array of division objects containing id and name
        let districts = ${districts};  // an array of district objects containing id, name, and parentId

        // populate the division select options
        for (let division of divisions) {
            let option = document.createElement("option");
            option.value = division.id;
            option.text = division.name;
            document.querySelector("#division").appendChild(option);
        }

        // handle the division change event to update the districts
        document.querySelector("#division").addEventListener("change", function() {
            let selectedDivisionId = this.value;
            let districtSelect = document.querySelector("#district");
            districtSelect.innerHTML = "";

            // populate the districts based on the selected division
            for (let district of districts) {
                if (district.parentId === selectedDivisionId) {
                    let option = document.createElement("option");
                    option.value = district.id;
                    option.text = district.name;
                    districtSelect.appendChild(option);
                }
            }
        });
    </script>

    <div class="form-group">
        <input type="submit" value="Submit">
    </div>
</form>

</body>
</html>
