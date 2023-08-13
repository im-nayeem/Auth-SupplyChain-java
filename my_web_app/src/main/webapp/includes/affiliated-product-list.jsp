<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 12-Aug-23
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    <%@include file="/assets/css/list-items.css"%>
    <%@include file="/assets/css/filter.css"%>
</style>

<h1>Affiliated Product List</h1>
<div class="filter-box">
    <div>
        <strong>Show Result By Filtering Products:</strong>
    </div>
    <div class="filter-field">
        <label for="filter-id">ID:</label>
        <input type="text" id="filter-id">

        <label for="filter-batch">Batch:</label>
        <input type="text" id="filter-batch">

        <label for="filter-status">Status:</label>
        <input type="text" id="filter-status">
    </div>
</div>
<div class="table-view">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Batch</th>
            <th>Status</th>
            <th>Last Handover Date</th>
            <th>Distributor</th>
            <th>Distributor Agent</th>
            <th>Seller</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productList}" var="product">
            <tr class="product-row">
                <td class="product-name">${product.getProductId()}</td>
                <td class="product-batch">${product.getBatchId()}</td>
                <td class="product-status">${product.getStatus()}</td>
                <td>${product.getSoldDate()}</td>
                <td><a href="/user-profile?uid=${product.getDistributor()}"> ${product.getDistributor() == 0 ? 'N/A' : product.getDistributor()}</a></td>
                <td><a href="/user-profile?uid=${product.getDistributorAgent()}">${product.getDistributorAgent() == 0 ? 'N/A' : product.getDistributorAgent()}</a></td>
                <td><a href="/user-profile?uid=${product.getSeller()}">${product.getSeller() == 0 ? 'N/A' : product.getSeller()}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="../assets/jquery/product-filter.js"></script>
