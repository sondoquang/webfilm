<%-- 
    Document   : NavbarAdmin.jsp
    Created on : Nov 10, 2024, 5:28:43 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/admin" var="urladmin" />
<aside id="sidebar">
    <div class="d-flex">
        <button id="toggle-btn" type="button">
            <i class="ri-layout-grid-fill"></i>
        </button>
        <div class="sidebar-logo">
            <a href="${urladmin}/home">Admin</a>
        </div>
    </div>
    <ul class="sidebar-nav">
        <li class="sidebar-item ${sessionScope.menuActive==1?'active':''}">
            <a href="${urladmin}/home" class="sidebar-link">
                <i class="ri-home-8-line"></i>
                <span>Home</span>
            </a>
        </li>
        <li class="sidebar-item ${sessionScope.menuActive==2?'active':''}">
            <a href="${urladmin}/videos" class="sidebar-link">
                <i class="ri-folder-video-line"></i>
                <span>Videos</span>
            </a>
        </li>
        <li class="sidebar-item ${sessionScope.menuActive==3?'active':''}">
            <a href="${urladmin}/users" class="sidebar-link">
                <i class="ri-shield-user-line"></i>
                <span>Users</span>
            </a>
        </li>
        <li class="sidebar-item ${sessionScope.menuActive==4?'active':''}">
            <a href="${urladmin}/reports" class="sidebar-link">
                <i class="ri-folder-chart-line"></i>
                <span>Reports</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/home/index" class="sidebar-link">
                <i class="ri-eye-fill"></i>
                <span>View Film</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/logout" class="sidebar-link">
                <i class="ri-login-box-line"></i>
                <span>Log Out</span>
            </a>
        </li>
    </ul>
</aside>
