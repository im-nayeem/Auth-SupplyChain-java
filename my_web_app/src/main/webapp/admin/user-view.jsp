<%--
  Created by IntelliJ IDEA.
  User: Nayeem
  Date: 12-Mar-23
  Time: 11:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../includes/admin-header.jsp"%>
<style>
    <%@include file="../assets/user-view.css"%>
</style>
<div class="container">
    <div class="info">
        <div class="info__item">
            <div class="info__label">Name:</div>
            <div class="info__value">${user.getName()}</div>
        </div>
        <div class="info__item">
            <div class="info__label">NID:</div>
            <div class="info__value">${user.getNid()}</div>
        </div>
        <div class="info__item">
            <div class="info__label">Email:</div>
            <div class="info__value">${user.getEmail()}</div>
        </div>
        <div class="info__item">
            <div class="info__label">Address:</div>
            <div class="info__value">${user.getAddress().getUnion()},${user.getAddress().getUpazila()},${user.getAddress().getDistrict()}</div>
        </div>
        <div class="info__item">
            <div class="info__label">Role:</div>
            <div class="info__value">${user.getRole()}</div>
        </div>
        <c:if test="${user.getRole().equals('seller')}">
            <div class="info__item">
                <div class="info__label">Shop Name:</div>
                <div class="info__value">${user.getShopName()}</div>
            </div>
            <div class="info__item">
                <div class="info__label">Shop Road:</div>
                <div class="info__value">${user.getShopRoad()}</div>
            </div>
        </c:if>
        <c:if test="${user.getRole().equals('distributor')}">
            <div class="info__item">
                <div class="info__label">Distribution Center Road:</div>
                <div class="info__value">${user.getDistCenterRoad()}</div>
            </div>
        </c:if>

    </div>
</div>
<%@include file="../includes/admin-footer.jsp"%>
