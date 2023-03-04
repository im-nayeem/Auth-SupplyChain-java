<%--
  Created by IntelliJ IDEA.
  User: nayeem11908002
  Date: ১৬/২/২৩
  Time: ১২:১৩ AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Product Information</title>
    <style>
        <%@include file="../assets/sell-product.css"%>
    </style>
</head>
<body>
<div class="container">
    <h1>Product Information:</h1>
        <table>
            <tr>
                <th>Product Name</th>
                <td>${productMap.getProductName()}</td>
            </tr>
            <tr>
                <th>Product ID</th>
                <td>${product.getProductId()}</td>
            </tr>
            <tr>
                <th>Product Batch</th>
                <td>${product.getBatchId()}</td>
            </tr>
            <tr>
                <th>Product Status</th>
                <td>${product.getStatus()}</td>
            </tr>
        </table>
        <button class="sold-button" onclick="openModal()">Mark as Sold</button>
    <form method="post" action="sell-product" >
        <input type="hidden" value="${product.getProductId()}" name="first-product">
        <button id="mark-sold" type="submit" style="display:none;">Mark As Sold!</button>
    </form>

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
        if(count<3)
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