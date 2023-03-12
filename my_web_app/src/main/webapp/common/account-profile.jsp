<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 11-Mar-23
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <c:if test="${sessionScope.get('admin')!=null}">
    <%@include file="../includes/admin-header.jsp"%>
  </c:if>

  <style >
    <%@include file="../assets/account-profile.css"%>
  </style>

 <div class="main-content">
  <div class="container">
    <div class="profile">
      <div class="profile-details">
        <h1 class="name">Profile</h1>
        <ul class="details-list">
          <c:if test="${sessionScope.get('admin')!=null}">
            <li><strong>Email:</strong>${sessionScope.get('admin').getEmail()}</li>
            <li><strong>Role:</strong>Admin</li>
          </c:if>
          <c:if test="${sessionScope.get('admin')==null}">
            <li><strong>Name:</strong>${sessionScope.get('user').getName()}</li>
            <li><strong>Email:</strong>${sessionScope.get('user').getEmail()}</li>
            <li><strong>Location:</strong>${sessionScope.get('user').getAddress().getUpazila()},${sessionScope.get('user').getAddress().getDistrict()}</li>
            <li><strong>Role:</strong>${sessionScope.get('user').getRole()}</li>

          </c:if>

        </ul>
        <div class="profile-actions">
          <a href="#" class="btn-update-password">Update Password</a>
          <a href="${pageContext.request.contextPath}/LogOut" class="btn-logout">Logout</a>
        </div>
      </div>
    </div>
  </div>
  <c:if test="${sessionScope.get('admin')!=null}">
    <%@include file="../includes/admin-footer.jsp"%>
  </c:if>

