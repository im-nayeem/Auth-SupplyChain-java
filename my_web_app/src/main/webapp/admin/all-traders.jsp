<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 12-Aug-23
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="/includes/admin-header.jsp"%>
<style>
    <%@include file="/assets/css/list-items.css"%>
    <%@include file="/assets/css/filter.css"%>
</style>
<h2>All Traders Associated with The Company</h2>
<hr>
<div class="filter-box">
    <div>
        <strong>Show Result By Filtering:</strong>
    </div>
    <div class="filter-field">
        <label for="filterName">Name:</label>
        <input type="text" id="filterName">

        <label for="filterNid">NID:</label>
        <input type="text" id="filterNid">

        <label for="filterEmail">Email:</label>
        <input type="text" id="filterEmail">

        <label for="filterRole">Role:</label>
        <input type="text" id="filterRole">

        <label for="filterAddress">Address:</label>
        <input type="text" id="filterAddress">
    </div>
</div>


<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>NID</th>
        <th>Email</th>
        <th>Role</th>
        <th>Address</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${traders}" var="trader">
        <tr class="trader-row">
            <td class="trader-name">${trader.getName()}</td>
            <td class="trader-nid"><a href="${pageContext.request.contextPath}/admin/user-info?uid=${trader.getNid()}">${trader.getNid()}</a></td>
            <td class="trader-email">${trader.getEmail()}</td>
            <td class="trader-role">${trader.getRole()}</td>
            <td class="trader-address">${trader.getAddress().getUpazila()},${trader.getAddress().getDistrict()},${trader.getAddress().getDivision()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="../assets/jquery/trader-filter.js"></script>


<%@include file="/includes/admin-footer.jsp"%>
