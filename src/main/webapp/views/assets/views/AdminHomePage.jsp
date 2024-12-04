<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 11/12/2024
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- ==============RemixIcon=================== -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.4.0/remixicon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/styleAdmin.css"/>

    <title>Admin</title>
</head>

<body>
<div class="wrapper">
	<!-- Include navbar từ trang khác -->
    <jsp:include page="NavbarAdmin.jsp"/>
<%--    Phần chính trang admin home--%>
    <div class="main p-2">
        <div class="container p-4 ps-5 pe-5 rounded" style="background-color: aliceblue; min-height: 100vh;">
            <h2 class="ps-3">Dashboard</h2>
            <div class="row justify-content-md-center">
                <div class="col-lg-4 col-md-6 rounded rounded-4 topShareDate">
                    <div class="card mb-3" style="max-width: 540px; background-color: #eb9929;">
                        <div class="row g-0">
                            <div class="col-md-8">
                                <div class="card-body text-center">
                                    <h5 class="card-title">Total Share Date</h5>
                                    <small class="card-text">500</small>
                                </div>
                            </div>
                            <div class="col-md-4 text-center align-content-center">
                                <i class="ri-share-fill" style="font-size: 2rem;"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 rounded rounded-4 topLikeDate">
                    <div class="card mb-3" style="max-width: 540px; background-color: #00c6af;">
                        <div class="row g-0">
                            <div class="col-md-8">
                                <div class="card-body text-center">
                                    <h5 class="card-title">Total Like Date</h5>
                                    <small class="card-text">500</small>
                                </div>
                            </div>
                            <div class="col-md-4 text-center align-content-center">
                                <i class="ri-heart-2-fill" style="font-size: 2rem;"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 rounded rounded-4 topVideoNew">
                    <div class="card mb-3" style="max-width: 540px; background-color: #00a3ff;">
                        <div class="row g-0">
                            <div class="col-md-8">
                                <div class="card-body text-center">
                                    <h5 class="card-title">Total Video News</h5>
                                    <small class="card-text">500</small>
                                </div>
                            </div>
                            <div class="col-md-4 text-center align-content-center">
                                <i class="ri-movie-ai-fill" style="font-size: 2rem;"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row p-3">
                <div class="col-lg-4 col-md-12">
                    <h3 class="ps-3">Profile User</h3>
                    <div class="card">
                        <div class="card-header d-flex align-self-center">
                            <img class="card-img-top rounded-circle" src="${pageContext.request.contextPath}/views/assets/images/logos/messi.jpg" alt="Card image"
                                 style="width: 160px; height: 160px;">
                        </div>
                        <div class="card-body text-center">
                            <h4 class="card-title">${sessionScope.user.fullname}</h4>
                            <p class="card-text">${sessionScope.user.email}</p>
                            <a href="#" class="btn btn-primary">See Profile</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-12" >
                    <h3 class="ps-3">Top 10 Video Active</h3>
                    <table class="table">
                        <thead>
                        <tr class="table-info">
                            <th scope="col">#</th>
                            <th scope="col">VIDEO ID</th>
                            <th scope="col">VIDEO TITLE</th>
                            <th scope="col">VIEW COUNT</th>
                            <th scope="col">STATUS</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${listVideoActive}" varStatus="vs">
                            <tr>
                                <th scope="row">${vs.count}</th>
                                <td>${item.id}</td>
                                <td>${item.title}</td>
                                <td>${item.views}</td>
                                <td class="rtblemail">${item.active?'active':''}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--=============== MAIN JS ===============-->
<script src="${pageContext.request.contextPath}/views/assets/js/Admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
