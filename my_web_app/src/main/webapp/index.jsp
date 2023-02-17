<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file="assets/style.css"%>
    </style>
    <script>
        <%@include file="assets/responsive.js"%>
    </script>
</head>
<body>
<div class="head">
    <h2>ABC Company</h2>
</div>
<div class="topnav" id="myTopnav">
    <a href="#home" class="active">Home</a>
    <a href="#news">View Product Info</a>
    <a href="#contact">About Us</a>
    <a href="#about" class="account-link">Log-in</a>

    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
    </a>
</div>

<div class="main-content">
    <h2>Featured Products</h2>

    <section class="products">
        <div class="product-block">
            <div class="product-image">
            </div>
            <div class="product-details">
                <h3>XJOGOS XG08</h3>
                <p>Type: Gaming Mouse</p>
                <p>Description: Excellent Ergonomic DesignThe hand design/perfect fit Palm/grip feels comfortable in front of the design/rear round.</p>
                <a href="#" class="btn">Details</a>
            </div>
        </div>
        <div class="product-block">
            <div class="product-image">
            </div>
            <div class="product-details">
                <h3>HT03XL</h3>
                <p>Type: Battery</p>
                <p>Description: Battery for laptop series: HP Pavilion-15CUxx.</p>
                <a href="#" class="btn">Details</a>
            </div>
        </div>
        <div class="product-block">
            <div class="product-image">
            </div>
            <div class="product-details">
                <h3>HM13</h3>
                <p>Type: Earphone</p>
                <p>Description: Earphone with high bass!</p>
                <a href="#" class="btn">Details</a>
            </div>
        </div>
    </section>
</div>
<footer>
    <p>Privacy & Policy | Contact Us | Copyright &copy; 2023</p>
</footer>

</body>
</html>
