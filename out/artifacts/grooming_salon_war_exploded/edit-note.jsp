<%--
  Created by IntelliJ IDEA.
  User: PLMIWOJ4
  Date: 17.09.2019
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Note</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <a href="index.jsp" class="navbar-brand">GroomingShop Panel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="employee.jsp">Powrót</a></li>
            <li class="nav-item"><a class="nav-link" href="/LogoutServlet">Wyloguj się</a></li>
        </ul>
    </div>
</nav>
<br><br>
<div class="col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
<c:set var="oldDog" value="${dog}"></c:set>
<form method="post" action="EmployeeOptionsServlet?param=confNote&id=${oldDog.id_dog}" class="text-center">
    <div class="form-group">
        <label for="groomernotes">Uwagi groomera</label><br>
        <textarea name="groomernotes" required id="groomernotes"
                  class="form-control">${oldDog.groomer_notes}</textarea><br>
    </div>
    <input type="submit" value="Zapisz zmiany" class="btn btn-lg btn-primary btn-block">
</form>
</div>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Resources/js/bootstrap.js"></script>
</body>
</html>
