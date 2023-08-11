<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 12-Aug-23
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="/includes/admin-header.jsp"%>
<style>
    <%@include file="/assets/list-items.css"%>
</style>
<h1>All Traders Associated with The Company</h1>

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
        <td>${trader.getName()}</td>
        <td><a href="${pageContext.request.contextPath}/admin/user-info?uid=${trader.getNid()}">${trader.getNid()}</a></td>
        <td>${trader.getEmail()}</td>
        <td>${trader.getRole()}</td>
        <td>${trader.getAddress().getUpazila()},${trader.getAddress().getDistrict()},${trader.getAddress().getDivision()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@include file="/includes/admin-footer.jsp"%>
