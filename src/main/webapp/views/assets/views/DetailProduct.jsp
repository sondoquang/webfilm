<%-- 
    Document   : DetailProduct
    Created on : Nov 12, 2024, 7:23:24 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="movie" content="trailer film">
        <meta name="theater" content="action film">
        <meta name="movie" content="romantic film">

        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <!-- ==============RemixIcon=================== -->
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.4.0/remixicon.css">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/views/assets/css/styleDetail.css" />
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/views/assets/css/styleIndex.css" />
        <title>Detail Product</title>
    </head>

    <body>
        <c:url value="/homepage" var="url" />
        <jsp:include page="Header.jsp"/>
        <!--==================== MAIN ====================-->
        <section class="main">
            <img
                src="${pageContext.request.contextPath}/views/assets/images/backgrounds/body.jpg"
                alt="image" class="main__bg">
            <!-- ================== START SIDE LEFT =================== -->
            <div class="container body__side">
                <div class="row">
                    <div class="col-lg-3 left__side">
                        <button class="ctMenuBtn" id="btnMenuLeft">
                            <h2 id="title__top">
                                <span class="top__10movies">Top 10 Movies</span>
                            </h2>
                        </button>
                        <div class="row list_items">
                            <c:forEach var="item" items="${videofavourite}">
                                <div class="col-sm-12 mt-3 suggested__item">
                                    <div class="wrap__item" style="overflow: hidden;">
                                        <div class="item__image">
                                            <img
                                                src="${pageContext.request.contextPath}/views/assets/images/products/${item.poster}"
                                                alt="">
                                        </div>
                                        <div class="item__content">
                                            <div class="ct">
                                                <span>English</span>
                                            </div>
                                            <a href="${pageContext.request.contextPath}/videos/detail/${item.id}">${item.title}</a>
                                            <div class="duration__time">
                                                <i class="ri-time-line"></i><span>2 Hr 32 Mn</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- ================== END SIDE LEFT =================== -->


                    <!-- ================== START  RIGHT SIDE =================== -->
                    <div class="col-lg-9 p-3 rounded right__side">
                        <div class="right_side_v1">
                            <div class="img">
                                <img
                                    src="${pageContext.request.contextPath}/views/assets/images/banners/${video.poster}"
                                    alt="banner_first" class="poster__film w-100" style="height: 550px; object-fit: cover;">
                            </div>
                            <div class="play__btn__video" id="btnPlayVideo"
                                 style="z-index: 10;">
                                <button class="video_btn" id="play__video"
                                        style="cursor: pointer">
                                    <img
                                        src="${pageContext.request.contextPath}/views/assets/images/logos/play-button.png"
                                        alt="play">
                                </button>
                            </div>
                            <div class="container content" style="z-index: 1;">
                                <p class="title">${video.title}</p>
                                <div class="cat">
                                    <ul>
                                        <li><span>Category : </span> <span class="name">"English
                                                movies - 2019"</span></li>
                                        <li><span>Genre : </span> <span>Action, Drama</span></li>
                                        <li><span>Language : </span> <span>English</span></li>
                                        <li>
                                            <div class="newsleter w-50 ">
                                                <form method="post" class="d-flex" action="${pageContext.request.contextPath}/videos/share/${video.id}">
                                                    <span class="me-2 align-content-center">SHARE: </span>
                                                    <input type="text" name="emailTo" id="newsleter" placeholder="Enter Email" required >
                                                    <button type="submit" class="btn__sendMail"><i class="ri-send-plane-fill"></i></button>
                                                </form>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="detail__Infor__movies">
                            <br>
                            <hr>
                            <h2 class="title">Movie Story</h2>
                            <p class="movie-content">${video.description}</p>
                            <br>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="Footer.jsp"/>
        <div class="poup__play__video" id="popupVideo">
            <iframe width="560rem" height="315rem" class="video__iframe"
                    src="${video.linkVideo}" title="YouTube video player" frameborder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                    referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
            <div class="close">
                <i class="ri-close-large-line"></i>
            </div>
        </div>
        <!--=============== MAIN JS ===============-->
        <script
        src="${pageContext.request.contextPath}/views/assets/js/Detail.js"></script>
        <script
        src="${pageContext.request.contextPath}/views/assets/js/Main.js"></script>
        <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
