<%-- 
    Document   : AdminUserPage
    Created on : Nov 10, 2024, 5:32:25 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- ================ RemixIcon =================== -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.4.0/remixicon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/styleAdmin.css"/>
        <title>Admin</title>
    </head>
    <body>
    <c:url value="/admin/user" var="url"/>
    <div class="wrapper">
        <jsp:include page="NavbarAdmin.jsp"/>
        <div class="main p-2">
            <!--==== Phần thông tin người dùng (user đang đăng nhập vào hệ thống)======-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 align-content-center p-0"><h3 class="title rounded">Management Users</h3></div>
                </div>
            </div>

            <!--======= Phần control tab trong page User ======-->
            <div class="container p-0 container-tabs">
                <div class="tabs d-flex">
                    <div class="tab-item p-2 ${edit == true?'active_tab':''} ">
                        <i class=" ri-edit-fill "></i>
                        User Edition
                    </div>

                    <div class="tab-item p-2 ${edit == false?'active_tab':''}">
                        <i class="ri-file-list-line"></i>
                        User list
                    </div>
                </div>
                <!-- Line  -->
                <div class="line"></div>
            </div>

            <!-- Tab-content -->
            <div class="tab-content">
                <!--Tab pane #1-->
                <div class="tab-pane ${edit==true?'active_pane':''}">
                    <div class="container p-5 rounded h-100">
                        <form method="post" id="form">
                            <div class="col-12">
                                <h3 style="text-align:center">FORM DETAIL INFORMATION OF USER</h3>
                            </div>
                            <div class="row ps-5 pe-5">
                                <div class="col mb-3 input-control">
                                    <label for="id" class="form-label">Id:</label>
                                    <input type="text" class="form-control" id="id" name="id" placeholder="Id automatic" value="${item.id}" readonly  >
                                    <div class="error"></div>
                                </div>
                                <div class="col mb-3 input-control">
                                    <label for="username" class="form-label">Username:</label>
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" value="${item.username}"  required>
                                    <div class="error"></div>
                                </div>
                                <div class="col mb-3 input-control">
                                    <label for="pwd" class="form-label">Password:</label>
                                    <input type="text" class="form-control" id="pwd" name="password" placeholder="Enter Password" value="${item.password}" required>
                                    <div class="error"></div>
                                </div>
                            </div>
                            <div class="row ps-5 pe-5">
                                <div class="col mb-3 input-control">
                                    <label for="fullname" class="form-label">Fullname:</label>
                                    <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Enter fullmame" value="${item.fullname}" required>
                                    <div class="error"></div>
                                </div>
                                <div class="col mb-3 input-control">
                                    <label for="email" class="form-label">Email:</label>
                                    <input type="text" class="form-control" id="email" name="email" placeholder="Enter Email" value="${item.email}" required>
                                    <div class="error"></div>
                                </div>
                            </div>
                            <div class="row ps-5 pe-5">
                                <div class="col mb-3 d-flex">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" value="true" name="admin" id="admin" ${item.admin==true?'checked':''} >
                                        <label class="form-check-label" for="admin">
                                            Admin
                                        </label>
                                    </div>
                                    <div class="form-check ms-3">
                                        <input class="form-check-input" type="radio" value="false" name="admin" id="user" ${item.admin==false?'checked':''}>
                                        <label class="form-check-label" for="user">
                                            User
                                        </label>
                                    </div>
                                </div>
                                <div class="error"></div>
                            </div>
                            <div class="row">
                                <div class="col-12 p-2 " style="text-align: center;">
                                    <button type="submit" class="btn btn-color m-1" formaction="${url}/create" onclick="submitForm()" value="0">Create</button>
                                    <button type="submit" class="btn btn-color m-1" formaction="${url}/update/?page=${pageActive}" onclick="submitForm()" value="1">Update</button>
                                    <button type="submit" class="btn btn-color m-1" formaction="${url}/delete" value="delete" onclick="clickDelete(this)">Delete</button>
                                    <button type="submit" class="btn btn-color m-1" formaction="${url}/reset" onclick="clickReset()">Reset</button>
                                </div>
                            </div>
                        </form>
                        <div class="alert alert-danger d-flex align-items-center p-1 ${success==true?'d-none':(message==null?'d-none':'')} " role="alert">
                            <span class="icon" style="font-size: 1.5rem;"><i class="ri-error-warning-fill"></i> </span>
                            <div>
                                ${message}
                            </div>
                        </div>
                        <div class="alert alert-success d-flex align-items-center p-1 ${success==true?(message==null?'d-none':''):'d-none'} " role="alert">
                            <span class="icon" style="font-size: 1.5rem;"><i class="ri-check-double-line"></i></span>
                            <div>
                                ${message}
                            </div>
                        </div>
                    </div>
                </div>
                <!--Tab pane #2-->
                <div class="tab-pane ${edit==false?'active_pane':''}">
                    <div class="container p-5 rounded">
                        <!--Phần filter theo yêu cầu-->
                  
                        <h2 class="pb-2">Danh sách tất cả các người dùng:</h2>
                        <div class="row mb-2">
                            <form action="${url}/search/?page=${i}&search=${param.search}&admin=${param.selectOption}" method="post" class="d-flex">
                                <div class="col-sm-5 d-flex ">
                                    <input type="text" class="form-control-sm w-100 border" name="search" value="${param.search}" placeholder="Nhập tên hoặc email bạn muốn" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                </div>  
                                <div class="col-sm-7 container d-flex">
                                    <div class="form-check align-content-center">
                                        <input class="form-check-input" type="radio" value="true" name="role" id="filterAdmin" ${param.role==true?'checked':''}>
                                        <label class="form-check-label" for="filterAdmin">
                                            Admin
                                        </label>
                                        </div>
                                        <div class="form-check align-content-center ms-3">
                                            <input class="form-check-input" type="radio" value="false" name="role" id="filterUser" ${param.role==false?'checked':''}>
                                            <label class="form-check-label" for="filterUser">
                                                User
                                            </label>
                                        </div>
                                        <div class="form-check align-content-center ms-3">
                                            <input class="form-check-input" type="radio" value="" name="role" id="filterAll" ${param.role==null?'checked':''} >
                                            <label class="form-check-label" for="filterAll">
                                                All
                                            </label>
                                        </div>
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-info ms-3" type="submit" formaction="${url}/search">Search</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <!--Phần thông tin trên table-->
                        <div class="row">
                            <table class="table ">
                                <thead>
                                    <tr class="table-info">
                                        <th scope="col">#</th>
                                        <th scope="col">PASSWORD</th>
                                        <th scope="col">FULLNAME</th>
                                        <th scope="col">EMAIL</th>
                                        <th scope="col">ADMIN</th>
                                        <th scope="col">ACTION</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${list}" varStatus="vs">
                                    <tr>
                                        <th scope="row">${vs.count}</th>
                                        <td>${item.password}</td>
                                        <td>${item.fullname}</td>
                                        <td class="rtblemail">${item.email}</td>
                                        <td>${item.admin==true?'Admin':'User' }</td>
                                        <td><a href="${url}/edit/?id=${item.id}&search=${param.search}&role=${param.role}"><i class="ri-edit-2-line"></i></a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <!--Phần phân trang-->
                        <div class="row" style=" align-items: center;">
                            <nav >
                                <ul class="pagination float-end">
                                    <li class="page-item ">
                                        <a class="page-link" href="${url}/search/?page=${pageActive-1<1?1:pageActive-1}&search=${param.search}&role=${param.role}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach var="i" begin="1" end="${sumCountPage}" step="1">
                                        <li class="page-item ${pageActive==i?'active':''}"><a class="page-link" href="${url}/search/?page=${i}&search=${param.search}&role=${param.role}">${i}</a></li>
                                    </c:forEach>
                                    <li class="page-item">
                                        <a class="page-link" href="${url}/search/?page=${pageActive<sumCountPage?pageActive+1:sumCountPage}&search=${param.search}&role=${param.role}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
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
