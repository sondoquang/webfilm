<%-- 
    Document   : Header
    Created on : Nov 24, 2024, 2:46:52 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/home" var="url" />
<div class="container">
    <!--==================== HEADER ====================-->
    <header class="header" id="header">
        <nav class="nav container">
            <a href="${pageContext.request.contextPath}/home/index" class="nav__logo"><img
                    src="${pageContext.request.contextPath}/views/assets/images/logos/logo.png"
                    alt=""></a>

            <div class="nav__menu" id="nav-menu">
                <ul class="nav__list">
                    <li class="nav__item"><a href="${pageContext.request.contextPath}/home/index" class="nav__link">Home</a>
                    </li>

                    <li class="nav__item" ${sessionScope.user==null?'hidden':''}><a href="${pageContext.request.contextPath}/admin/home" class="nav__link">Administration</a></li>
					<li class="nav__item"><a href="${pageContext.request.contextPath}/account" class="nav__link">Favourite</a></li>
					<li class="nav__item"><a href="${pageContext.request.contextPath}/account" class="nav__link">Account</a></li>
					<li class="nav__item" ${sessionScope.user!=null?'hidden':''}><a href="${pageContext.request.contextPath}/sign-in" class="nav__link">Sign In</a></li>
					
                    <!-- <li class="nav__item"><a href="#" class="nav__link dropdown__link">Product<i
                                class="ri-arrow-down-s-fill dropdown__icon"></i></a> -->
                        <!-- <ul class="submenu">
                            <li><a href="#">Product 1</a></li>
                            <li><a href="#">Product 2</a></li>
                            <li><a href="#">Product 3</a></li>
                        </ul></li> -->

                    <!-- <li class="nav__item"><a href="#" class="nav__link">Featured<i
                                class="ri-arrow-down-s-fill dropdown__icon"></i></a>
                        <ul class="submenu">
                            <li><a href="#">Services 1</a></li>
                            <li><a href="#">Services 2</a></li>
                            <li><a href="#">Services 3</a></li>
                        </ul></li>

                    <li class="nav__item"><a href="#" class="nav__link">Contact
                            Us</a></li> -->
                </ul>

                <!-- Close button -->
                <div class="nav__close" id="nav-close">
                    <i class="ri-close-line"></i>
                </div>
            </div>

            <div class="nav__actions">
                <!-- Search button -->
                <i class="ri-search-line nav__search" id="search-btn"></i>
                <c:url value="/logout" var="urlLogout" />
                <a href="${urlLogout}" class="btn btn-info"
                   ${sessionScope.user != null?'':'hidden'}>Logout</a>
                <!-- Login button -->
                <a href="${pageContext.request.contextPath}/login" class="btn btn-info text-white" ${sessionScope.user != null?'hidden':''}><i class="ri-user-line"> Login</i></a> 
                <a href="${pageContext.request.contextPath}/account" class="btn btn-warning text-dark"
                   ${sessionScope.user != null?'':'hidden'}>Account</a>

                <!-- Toggle button -->
                <div class="nav__toggle" id="nav-toggle">
                    <i class="ri-menu-line"></i>
                </div>
            </div>
        </nav>
    </header>

    <!--==================== SEARCH ====================-->
    <div class="search" id="search">
        <form action="" class="search__form" method="get">
            <i class="ri-search-line search__icon"></i> <input type="search"
                                                               placeholder="What are you looking for?" class="search__input" />
        </form>
        <i class="ri-close-line search__close" id="search-close"></i>
    </div>
</div>
