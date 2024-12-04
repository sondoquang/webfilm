<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 11/12/2024
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--Nhúng thư viện bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- ==============Nhúng RemixIcon=================== -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.4.0/remixicon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/styleAdmin.css"/>

        <title>Admin</title>
    </head>

    <body>
    <c:url value="/admin/videos" var="url"/>
        <div class="wrapper">
            <!--==== Include sidebar ====-->
            <jsp:include page="NavbarAdmin.jsp"/>
            <!--  CONTROL - TAB-PANES -->
            <div class="main p-2"> <!-- div bọc cao nhất của main-->
                <div class="container">
                    <!--Phần title không thay đổi nhiều-->
                    <div class="row">
                        <div class="col-sm-12 align-content-center p-0"><h3 class="title rounded">Management Videos</h3></div>
                    </div>
                </div>
                
                <!--Phần control tab-pane-->
                <div class="container p-0 container-tabs">
                    <div class="tabs d-flex">
                        <div class="tab-item p-2 ${isEdit == true?'active_tab':''}">
                            <i class="ri-video-add-fill"></i>
                            Video Edition
                        </div>
                        <div class="tab-item p-2 ${isEdit == false?'active_tab':''}">
                            <i class="ri-film-line"></i>
                            Video List
                        </div>
                    </div>
                    <!-- Line  -->
                    <div class="line"></div>
                </div>

                <!-- Tab-content -->
                <div class="tab-content">
                    <div class="tab-pane ${isEdit == true?'active_pane':''}">
                        <div class="container p-5 rounded h-100">
                            <form action="${url}/upload" method="post" id="form" enctype="multipart/form-data" >
                                <div class="col-12">
                                    <h3 class="text-center pb-4 m-0">FORM DETAIL INFORMATION VIDEO</h3>
                                </div>
                                <div class="row ps-5 pe-5">
                                    <div class="col-sm-4 p-2 pb-3">
                                        <img src="${pageContext.request.contextPath}/views/assets/images/banners/${video.poster}" class="rounded img-thumbnail  w-100" style="height: 400px; object-fit: scale-down;" alt="">
                                        <label for="img" style="display: inline-block;width: 100%;text-align: center; color: #00a3ff;cursor: pointer; padding-top: 6px;">
                                        <input type="file" id="img" name="poster"  class="align-items-center" hidden onchange="this.form.submit()">Choose file banner</label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="col mb-3 input-control">
                                            <label for="id" class="form-label">Video id:</label>
                                            <input type="text" class="form-control" id="id" name="id"
                                                   placeholder="Id automatic" value="${video.id==null?"":video.id}" readonly >
                                            <div class="error"></div>
                                        </div>
                                        <div class="col mb-3 input-control">
                                            <label for="title" class="form-label">Video title:</label>
                                            <input type="text" class="form-control" id="title" name="title"
                                                   placeholder="Enter title video" value="${video.title}" required>
                                            <div class="error"></div>
                                        </div>
                                        <div class="col mb-3 input-control">
                                            <label for="views" class="form-label">View count:</label>
                                            <input type="text" class="form-control" id="views" name="views"
                                                   placeholder="View count" readonly value="${video.views ==null?0:video.views}" required>
                                        </div>
                                        <div class="col mb-3 input-control">
                                            <label for="linkVideo" class="form-label">Link Video:</label>
                                            <input type="text" class="form-control" id="linkVideo" name="linkVideo"
                                                   placeholder="Enter link iframe video" value="${video.linkVideo ==null?"":video.linkVideo}" required>
                                        </div>
                                        <div class="col mb-3 input-control">
                                            <fmt:formatDate var="postDate" value="${video.postDate}" pattern="yyyy-MM-dd"/>
                                            <label for="datePost" class="form-label">Post Date:</label>
                                            <input type="date" class="form-control" id="datePost" name="postDate" value="${postDate}" required>
                                        </div>
                                        <div class="col mb -3 d-flex">
                                            <div class="form-check text-center">
                                                <input class="form-check-input" type="radio" value="true" name="active"
                                                       id="admin"  ${video.active==true || video.active == null?'checked':''}>
                                                <label class="form-check-label" for="admin">
                                                    ACTIVE
                                                </label>
                                            </div>
                                            <div class="form-check ms-3">
                                                <input class="form-check-input" type="radio" value="false" name="active"
                                                       id="user" ${video.active==false ?'checked':''}>
                                                <label class="form-check-label" for="user">
                                                    INACTIVE
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row ps-5 pe-5">
                                    <div class="col-sm-12">
                                        <div class="form-floating">
                                            <textarea class="form-control" placeholder="Enter description" id="floatingTextarea2" style="min-height: 400px" required name="description">${video.description}</textarea>
                                            <label for="floatingTextarea2">Description</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="alert alert-danger d-flex align-items-center p-1 mt-3 ${success==true?'d-none':(message==null?'d-none':'')} " role="alert">
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
                                <div class="row mt-5">
                                    <div class="col-12 p-2 " style="text-align: center;">
                                        <button type="submit" class="btn btn-color m-1" formaction="${url}/create?page=${pageNo}" onclick="submitForm()" value="0">Create</button>
                                        <button type="submit" class="btn btn-color m-1" formaction="${url}/update/?page=${pageNo}" onclick="submitForm()" value="1">Update</button>
                                        <button type="submit" class="btn btn-color m-1" formaction="${url}/delete" value="delete" onclick="clickDelete(this)">Delete</button>
                                        <button type="submit" class="btn btn-color m-1" formaction="${url}/reset" onclick="clickReset()">Reset</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--tab-pane list-->
                    <div class="tab-pane ${isEdit == false?'active_pane':''}">
                        <div class="container p-5 rounded">
                            <h2 class="pb-2">Danh sách tất cả các video của hệ thống:</h2>
                            <form action="${url}/index" method="post">
                                <div class="row mb-2">
                                     <div class="col-sm-5 d-flex ">
                                        <input type="text" class="form-control-sm w-100 border" placeholder="Nhập title video bạn muốn"
                                           aria-label="Recipient's username" aria-describedby="basic-addon2" name="search" value="${param.search}">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-info" type="submit">Search</button>
                                        </div>
                                </div>
                                <div class="col-sm-7">

                                        <select class="form-select" name="status" aria-label="Default select example">
                                            <option value="" ${status!= null?'':'selected'}>Lọc theo trạng thái</option>
                                            <option value="true" ${status == true?'selected':''}>Active</option>
                                            <option value="false" ${status == false?'selected':''}>Inactive</option>
                                        </select>
                                </div>
                            </form>
                            </div>
                            <div class="row">
                                <table class="table">
                                    <thead>
                                        <tr class="table-info">
                                            <th scope="col">#</th>
                                            <th scope="col">VIDEO ID</th>
                                            <th scope="col">VIDEO TITLE</th>
                                            <th scope="col">VIEW COUNT</th>
                                            <th scope="col">STATUS</th>
                                            <th scope="col">ACTION</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${list}" varStatus="vs">
                                        <tr>
                                            <th scope="row">${vs.count}</th>
                                            <td>${item.id}</td>
                                            <td>${item.title}</td>
                                            <td>${item.views}</td>
                                            <td class="rtblemail">${item.active==true?'Active':'Inactive'}</td>
                                            <td><a href="${url}/edit/${item.id}"><i class="ri-edit-2-line"></i></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row">
                                <ul class="pagination col-sm-12" style=" justify-content: center;">
                                    <li class="page-item">
                                        <a class="page-link" href="${url}/index?page=${pageNo>1?pageNo-1:1}&search=${param.search}&status=${param.status}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach var="page" begin="1" end="${sumPage}" step="1">
                                        <li class="page-item"><a class="page-link ${pageNo == page?'active':''}" href="${url}/index?page=${page}&search=${param.search}&status=${param.status}">${page}</a></li>
                                    </c:forEach>
                                    <li class="page-item">
                                        <a class="page-link" href="${url}/index?page=${pageNo<sumPage?pageNo+1:sumPage}&search=${param.search}&status=${param.status}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--=============== MAIN JS ===============-->
        <script src="${pageContext.request.contextPath}/views/assets/js/Admin.js"></script>\
        <script>
            function updateFormAction() {
                const form = document.getElementById('form');
                form.action = `${form.action.split('?')[0]}?poster=true`;
                form.submit();
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
