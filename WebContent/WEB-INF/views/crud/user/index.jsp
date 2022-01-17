<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="fr">
<%@ include file="../../layout.jsp" %>

<body>

    <div class="vertical-nav" id="sidebar">
        <div class="py-4 px-3 mb-4 bg-light">
            <div class="media d-flex align-items-center"><img src="./resources/images/pochette.jpg" alt="..." width="65" class="mr-3 rounded-circle img-thumbnail shadow-sm">
                <div class="media-body">
                    <h4 class="m-0">Profile</h4>
                    <p class="font-weight-light text-muted mb-0">Beatmaker</p>
                </div>
            </div>
        </div>

        <ul class="nav flex-column mb-0">
            <li class="nav-item">
                <a href="#" class="nav-link text-light">
                    <img src="./resources/images/home.png" height="50px" width="50px" alt=""> User
                </a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link text-light">
                    <img src="./resources/images/discover.png" height="50px" width="50px" alt=""> Role
                </a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link text-light">
                    <img src="./resources/images/upload.png" height="50px" width="50px" alt=""> Music
                </a>
            </li>
        </ul>

    </div>

    <div id="background">

        <div class="style-bar">
            <input id="sidebarCollapse" type="image" src="./resources/images/menu.png" height="50px" width="50px" name="menu" />
        </div>

        <div class="container-fluid header">
            <div class="row">
                <div class="col-md-4 title my-auto">
                    <a href="index.html"><img src="./resources/images/logo_1.0.png" height="60" width="60"> Raplay</a>
                </div>

                <div class="col-md-3">

                </div>

                <div class="col-md-5 search my-auto">
                    <div class="p-2 bg-dark rounded rounded-pill shadow-sm">
                        <div class="input-group">
                            <input type="search" placeholder="Search an user" aria-describedby="button-addon1" class="form-control border-0 bg-dark">
                            <div class="input-group-append">
                                <input type="image" src="./resources/images/discover.png" height="40px" width="40px" name="search" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid center-block">

            <div class="table-wrapper">
                <div class="table-title bg-dark">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Users</h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><span>Add New User</span></a>
                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><span>Delete</span></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox"><input type="checkbox" id="selectAll"><label for="selectAll"></label></span>
                            </th>
                            <th>Username</th>
                            <th>Mail</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="listValue" items="${usersList}">
                        <tr>
                            <td>
                                <span class="custom-checkbox"><input type="checkbox" id="checkbox1" name="options[]" value="1"><label for="checkbox1"></label></span>
                            </td>
                            <td>${listValue.getUsername()}</td>
                            <td>${listValue.getMail()}</td>

                            <td>
                                <a href="#editEmployeeModal" class="edit" data-toggle="modal"><img src="images/edit.png" width=20 height=20/></a>
                                <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><img src="images/delete.png" width=20 height=20/></a>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>${usersList.size()}</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>

    </div>

    </div>

</body>

<script>
    $(function() {
        // Sidebar toggle behavior
        $('#sidebarCollapse').on('click', function() {
            $('#sidebar, #background, .style-bar .table-wrapper').toggleClass('enable');
        });

    });
</script>

</html>
