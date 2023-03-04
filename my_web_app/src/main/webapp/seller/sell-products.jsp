<%--
  Created by IntelliJ IDEA.
  User: nayeem11908002
  Date: ১৬/২/২৩
  Time: ১২:১৩ AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Sell Products</title>
    <style>

        <%@include file="../assets/sell-product.css"%>

    </style>
</head>
<body>

<div class="container">
    <h2>Sell Products</h2>

    <form method="post" action="sell-product" >
        <label for="first-product">First Product ID:</label>
        <input type="text"  id="first-product" name="first-product" pattern="[a-zA-Z0-9]+" required>

        <label for="last-product">Last Product ID:</label>
        <input type="text" id="last-product"  name="last-product" pattern="[a-zA-Z0-9]+" required>

        <button id="mark-sold" type="submit" style="display:none;">Mark As Sold!</button>
    </form>

    <button class="sold-button" onclick="openModal()">Mark as Sold</button>
</div>
<!-- Pop-up window to confirm marking as sold -->
<div id="sold-modal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <p>Are you sure you want to mark this product as sold?</p>
        <button onclick="confirmSold()">Yes</button>
        <button onclick="closeModal()">No</button>
    </div>
</div>

<script>
    var count = 1;
    // Get the modal
    var soldModal = document.getElementById("sold-modal");

    // Open the modal
    function openModal() {
        soldModal.style.display = "block";
    }

    // Close the modal
    function closeModal() {
        soldModal.style.display = "none";
    }

    // Confirm marking as sold
    function confirmSold() {
        if(count<2)
        {
            count=count+1;
            closeModal();
            alert("It can't be undone. Think and confirm by clicking 3 times.");
        }
        else
        {
            closeModal();
            document.getElementById("mark-sold").style.display="block";
            document.getElementsByClassName("sold-button")[0].style.display="none";

        }


    }
</script>
</body>
</html>