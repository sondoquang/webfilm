<%--
    Document   : HomePage
    Created on : Nov 12, 2024, 7:37:19 PM
    Author     : acer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="movie" content="trailer film">
    <meta name="theater" content="action film">
    <meta name="movie" content="romantic film">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- ==============RemixIcon=================== -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.4.0/remixicon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/styleIndex.css"/>
    <title>TMovies</title>
    <style>
        /* Xử lý tràn title*/
        .titleItemFilm{
            overflow: hidden;
            -webkit-line-clamp: 2 !important;
            -webkit-box-orient: vertical !important;
            display: -webkit-box !important;
        }
    </style>
</head>

<body>
<c:url value="/home" var="url"/>
<jsp:include page="assets/views/Header.jsp"/>
<!--==================== MAIN ====================-->
<section class="main">
    <img
            src="${pageContext.request.contextPath}/views/assets/images/backgrounds/body.jpg"
            alt="image" class="main__bg">
    <div class="container body__side">
        <div class="row">
            <div class="col-sm-3 left__side">
                <button class="ctMenuBtn" id="btnMenuLeft">
                    <h2 id="title__top">
                        <span class="top__10movies">Top 10 Movies</span>
                    </h2>
                </button>
                <div class="ct_menu show__menuLeft" id="categoryMenu">
                    <ul>
                        <li class="active">
                            <button type="button" class="btnCTMenu">Home</button>
                        </li>
                        <li class="has_sub">
                            <button onclick="toggleSubmenu(this)"
                                    class="dropbtn">
                                Romantic Movies<i class="ri-arrow-down-s-fill dropdown__icon"></i>
                            </button>
                            <ul class="ct__submenu">
                                <div>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">Tamil</a></li>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">China</a></li>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">English</a></li>
                                </div>
                            </ul>
                        </li>
                        <li class="has_sub">
                            <button onclick="toggleSubmenu(this)"
                                    class="dropbtn">
                                Top 10 Movies<i class="ri-arrow-down-s-fill dropdown__icon"></i>
                            </button>
                            <ul class="ct__submenu">
                                <div>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">Tamil</a></li>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">China</a></li>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">English</a></li>
                                </div>
                            </ul>
                        </li>
                        <li class="has_sub">
                            <button onclick="toggleSubmenu(this)"
                                    class="dropbtn">
                                Rating Movies<i class="ri-arrow-down-s-fill dropdown__icon"></i>
                            </button>
                            <ul class="ct__submenu">
                                <div>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">Tamil</a></li>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">China</a></li>
                                    <li class="ct__submenu_item"><a href=""
                                                                    class="ct__submenu_link">English</a></li>
                                </div>
                            </ul>
                        </li>
                        <li>
                            <button type="button" class="btnCTMenu">New
                                Movies
                            </button>
                        </li>
                        <li>
                            <button type="button" class="btnCTMenu">TV
                                Seris
                            </button>
                        </li>
                        <li>
                            <button type="button" class="btnCTMenu">China
                                Movies
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-9 right__side">
                <div class="right_side_v1">
                    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-indicators">
                            <c:forEach var="video" items="${listTopFavourite}" varStatus="vs">
                                <button type="button" data-bs-target="#carouselExampleCaptions"
                                        data-bs-slide-to="${vs.count-1}"
                                        class="${vs.count == 1?'active':''}"></button>
                            </c:forEach>
                        </div>
                        <div class="carousel-inner">
                            <c:forEach var="video" items="${listTopFavourite}" varStatus="vs">
                                <div class="carousel-item ${vs.count==1?'active':''}">
                                    <img src="${pageContext.request.contextPath}/views/assets/images/banners/${video.poster}"
                                         class="d-block w-100 rounded"
                                         style="height: 580px; object-fit: cover;" alt="...">
                                    <div class="carousel-caption d-none d-md-block rounded"
                                         style="background-color:rgba(0, 0, 0, 0.5);">
                                        <h2 class="title">${video.title}</h2>
                                        <div class="cat">
                                            <ul>
                                                <li>
                                                    <span>Category : </span>
                                                    <span class="name">"English movies - 2019"</span>
                                                </li>
                                                <li>
                                                    <span>Genre : </span>
                                                    <span>Action, Drama</span><br>
                                                    <span>View: ${video.views} Viewer</span>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="play__btn">
                                            <a href="${pageContext.request.contextPath}/videos/detail/${video.id}"
                                               class="video_btn justify-content-center">
                                                <img src="${pageContext.request.contextPath}/views/assets/images/logos/play-button.png"
                                                     alt="play">
                                                <span>Play Trailer</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button"
                                data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button"
                                data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<div class="tmoves_new__arrivals">
    <div class="container">
        <div class="row tmoves_hadding">
            <h2>
                        <span class="col-lg-3 col-md-6 text-uppercase">Suggested
                            movies</span>
                <form action="${url}/index?page1=${pageNo01}&page2=${pageNo02}" method="post" class="col-lg-6 d-flex" id="select__sort">
                    <select class="form-select" aria-label="Default select example"
                            style="width: 40%;" name="sortBy" onchange="this.form.submit()">
                        <option value="" ${isActive==null?'selected':''}>Sort by</option>
                        <option value="true" ${isActive==true?'selected':''}>Video active</option>
                        <option value="false" ${isActive==false?'selected':''}>Video inactive</option>
                    </select>
                </form>
                <div class="col-lg-3 col-md-6 control">
                    <div class="btnPage">
                        <a href="${url}/index/?page1=${pageNo01==1?1:pageNo01-1}&sortBy=${isActive}"><i
                                class="ri-arrow-left-s-line"></i></a>
                    </div>
                    <c:forEach var="page" begin="1" end="${sumPage}" step="1">
                        <div class="btnPage ${page == pageNo01?'pageActive':''}">
                            <a href="${url}/index/?page1=${page}&sortBy=${isActive}">${page}</a>
                        </div>
                    </c:forEach>
                    <div class="btnPage">
                        <a href="${url}/index/?page1=${pageNo1<sumPage-1?pageNo1+1:sumPage}&sortBy=${isActive}"><i
                                class="ri-arrow-right-s-line"></i></a>
                    </div>
                </div>

            </h2>

            <span class="border-bottom"></span>
        </div>


        <div class="row list__v__item">
            <jsp:useBean id="list01" scope="request" type="java.util.List"/>
            <c:forEach var="item" items="${list01}">
                <div class="col-sm-12 col-lg-3 video__item gradient" style="min-height: 400px">
                    <img
                            src="${pageContext.request.contextPath}/views/assets/images/products/${item.poster}"
                            alt="film">
                    <div class="movies__content"
                         style="padding-bottom: 0.7rem; background-color: rgba(0, 0, 0, 0.6);">
                        <a href="">${item.title}</a>
                        <div class="v__tag">Romance, English, 2019</div>
                        <div class="v__bottom">
                            <div class="v__time">
                                <i class="ri-time-line"></i><span>2 Hr 3 Min</span>
                            </div>
                            <div class="likle_icon">
                                    <%-- check video favourite of user online --%>
                                <c:if test="${sessionScope.user!=null}">
                                    <c:set value="false" var="isFavourite"/>
                                    <c:forEach var="videoFa" items="${listFavourite}" varStatus="vs">
                                        <c:if test="${(videoFa.id == item.id) && (isFavourite == false)}">
                                            <a onclick="likeApi(this,${item.id})" style="display: none"><i
                                                    class="ri-heart-line"></i></a>
                                            <a onclick="disLikeApi(this,${item.id})"><i
                                                    class="ri-heart-3-fill text-danger"></i></a>
                                            <c:set value="true" var="isFavourite"/>
                                        </c:if>
                                        <c:if test="${(videoFa.id != item.id) }">
                                            <c:if test="${(isFavourite!=true) && (vs.count == listFavourite.size())}">
                                                <a onclick="likeApi(this,${item.id})"><i
                                                        class="ri-heart-line"></i></a>
                                                <a onclick="disLikeApi(this,${item.id})" style="display: none"><i
                                                        class="ri-heart-3-fill text-danger"></i></a>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${listFavourite.size()<=0}">
                                        <a onclick="likeApi(this,${item.id})"><i
                                                class="ri-heart-line"></i></a>
                                        <a onclick="disLikeApi(this,${item.id})" style="display: none"><i
                                                class="ri-heart-3-fill text-danger"></i></a>
                                    </c:if>
                                </c:if>
                                    <%-- check video favourite of user not online --%>
                                <c:if test="${sessionScope.user==null}">
                                    <a onclick="disLikeApi(this,${item.id})" style="display: none"><i
                                            class="ri-heart-3-fill text-danger"></i></a>
                                    <a onclick="likeApi(this,${item.id})"><i
                                            class="ri-heart-line"></i></a>
                                    <c:set value="true" var="isFavourite"/>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="playbtn">
                        <a href="${pageContext.request.contextPath}/videos/detail/${item.id}"><img
                                src="${pageContext.request.contextPath}/views/assets/images/logos/play-button.png"
                                alt=""></a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div class="container wrap__ct__items">
    <div class="row tmoves_hadding">
        <h2>
            <span class="col-sm-3 text-uppercase">Suggested movies</span>
            <form action="${url}/suggest?page1=${pageNo01}&page2=${pageNo02}" method="post" class="col-lg-6 d-flex" id="select__sort">
                <select class="form-select" aria-label="Default select example"
                        style="width: 40%;" name="sortBy" onchange="this.form.submit()">
                    <option value="" ${isActive==null?'selected':''}>Sort by</option>
                    <option value="true" ${isActive==true?'selected':''}>Video active</option>
                    <option value="false" ${isActive==false?'selected':''}>Video inactive</option>
                </select>
            </form>
            <div class="col-lg-3 col-md-6 control">
                <div class="btnPage">
                    <a href="${url}/suggest/?page2=${pageNo02==1?1:pageNo02-1}&sortBy=${isActive}"><i
                            class="ri-arrow-left-s-line"></i></a>
                </div>
                <c:forEach var="page" begin="1" end="${sumPage}" step="1">
                    <div class="btnPage ${page == pageNo02?'pageActive':''}">
                        <a href="${url}/suggest/?page2=${page}&sortBy=${isActive}">${page}</a>
                    </div>
                </c:forEach>
                <div class="btnPage">
                    <a href="${url}/suggest/?page2=${pageNo02<sumPage-1?pageNo02+1:sumPage}&sortBy=${isActive}"><i
                            class="ri-arrow-right-s-line"></i></a>
                </div>
            </div>
        </h2>

        <span class="border-bottom"></span>
    </div>
    <div class="row list_items">
        <c:forEach var="item" items="${list02}">
            <div class="col-xl-3 col-lg-4 col-sm-6 mt-3 suggested__item">
                <div class="wrap__item">
                    <div class="item__image" style="overflow: hidden; padding: 4px">
                        <img
                                src="${pageContext.request.contextPath}/views/assets/images/products/${item.imagePoster}"
                                alt="">
                    </div>
                    <div class="item__content">
                        <div class="ct">
                            <span>English</span>
                        </div>
                        <a href="${pageContext.request.contextPath}/videos/detail/${item.id}" class="titleItemFilm">${item.title}</a>
                        <div class="body d-flex">
                            <div class="duration__time">
                                <i class="ri-time-line"></i><span>2 Hr 32 Mn</span>
                            </div>
                            <div class="likle_icon">
                                    <%-- check video favourite of user online --%>
                                <c:if test="${sessionScope.user!=null}">
                                    <c:set value="false" var="isFavourite"/>
                                    <c:forEach var="videoFa" items="${listFavourite}" varStatus="vs">
                                        <c:if test="${(videoFa.id == item.id) && (isFavourite == false)}">
                                            <a onclick="likeApi(this,${item.id})" style="display: none"><i
                                                    class="ri-heart-line"></i></a>
                                            <a onclick="disLikeApi(this,${item.id})"><i
                                                    class="ri-heart-3-fill text-danger"></i></a>
                                            <c:set value="true" var="isFavourite"/>
                                        </c:if>
                                        <c:if test="${(videoFa.id != item.id) }">
                                            <c:if test="${isFavourite!=true && (vs.count == listFavourite.size())}">
                                                <a onclick="likeApi(this,${item.id})"><i
                                                        class="ri-heart-line"></i></a>
                                                <a onclick="disLikeApi(this,${item.id})" style="display: none"><i
                                                        class="ri-heart-3-fill text-danger"></i></a>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${listFavourite.size()<=0}">
                                        <a onclick="likeApi(this,${item.id})"><i
                                                class="ri-heart-line"></i></a>
                                        <a onclick="disLikeApi(this,${item.id})" style="display: none"><i
                                                class="ri-heart-3-fill text-danger"></i></a>
                                    </c:if>
                                </c:if>
                                    <%-- check video favourite of user not online --%>
                                <c:if test="${sessionScope.user==null}">
                                    <a onclick="disLikeApi(this,${item.id})" style="display: none"><i
                                            class="ri-heart-3-fill text-danger"></i></a>
                                    <a onclick="likeApi(this,${item.id})"><i
                                            class="ri-heart-line"></i></a>
                                    <c:set value="true" var="isFavourite"/>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="./assets/views/Footer.jsp"/>
<!--=============== MAIN JS ===============-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/views/assets/js/Main.js"></script>
<script src="${pageContext.request.contextPath}/views/assets/js/VideoApi.js"></script>
</body>
</html>
