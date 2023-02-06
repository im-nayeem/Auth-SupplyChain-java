<%@ page import="com.example.my_web_app.Utility" %>
<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 03-Feb-23
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js">
    </script>
    <style>
        <%@include file="../assets/qrCode.css"%>
    </style>
</head>
<body>
<div id="qrContainer">

    <!-- This container will hold the QR codes and labels -->
</div>
<button id="printButton">Print QR Codes</button>
<script>

    document.getElementById("printButton").addEventListener("click", function() {
        window.print();
    });
</script>
<%String wirelessIp = Utility.getIp();%>

<script>
    let sections = [];
    let section={};
    let url={};
    <c:forEach items="${productList}" var="product">
        section = {};
        section.label = "${product.getProductId()}";
        section.urls = [];

        for (let j = 1; j <= 2; j++) {
            url = {};
            url.url = "${pageContext.request.scheme}://"+"<%= wirelessIp %>"+":${pageContext.request.serverPort}"+"${pageContext.request.contextPath}";
            if(j&1)
            {
                url.label = "Customer";
                url.url += "/view-product?pid=" + "${product.getProductId()}";
            }
            else{
                url.label = "Seller";
                url.url += "/seller/sell?pid=" + "${product.getProductId()}";
            }
            section.urls.push(url);


        }
        sections.push(section);
    </c:forEach>

    const qrContainer = document.getElementById('qrContainer');

    sections.forEach(({ label, urls }) => {
        const section = document.createElement('div');
        section.classList.add('section');

        const sectionLabel = document.createElement('h3');
        sectionLabel.classList.add('sectionLabel');
        sectionLabel.innerText = label;
        section.appendChild(sectionLabel);

        urls.forEach(({ label, url }) => {
            const qrBlock = document.createElement('div');
            qrBlock.classList.add('qrBlock');

            const qrCode = new QRCode(qrBlock, {
                text: url,
                width: 128,
                height: 128,
                colorDark : "#000000",
                colorLight : "#ffffff",
                correctLevel : QRCode.CorrectLevel.H
            });

            const qrLabel = document.createElement('p');
            qrLabel.innerText = label;
            qrBlock.appendChild(qrLabel);

            section.appendChild(qrBlock);
        });

        qrContainer.appendChild(section);
    });
</script>




</body>
</html>
