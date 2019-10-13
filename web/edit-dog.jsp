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
    <title>Edit Dog</title>
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
            <li class="nav-item"><a class="nav-link" href="customer.jsp">Powrót</a></li>
            <li class="nav-item"><a class="nav-link" href="/LogoutServlet">Wyloguj się</a></li>
        </ul>
    </div>
</nav>
<br><br>
<c:set var="oldDog" value="${oldDog}"></c:set>
<div id="editDog" class="col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid jumbotron">
    <h1 class="text-center">
        <small>Edycja psa</small>
    </h1>
    <form action="/CustomerPanelServlet?param=change&id=${oldDog.id_dog}" method="post" class="form-signin text-center">
        <div class="form-group">
            <label for="dogname">Imię psa</label><br>
            <input type="text" name="dogname" value="${oldDog.dogname}" required id="dogname"
                   class="form-control"><br>
        </div>
        <div class="form-group">
            <label for="race">Rasa</label><br>
            <select name="race" required id="race" class="form-control">
                <option value="Yorkshire terrier">Yorkshire terrier</option>
                <option value="Maltese">Maltese</option>
                <option value="Bijon">Bijon Frise</option>
            </select><br>
        </div>
        <div class="form-group">
            <label for="age">Wiek</label><br>
            <input type="number" name="age" required id="age" class="form-control" value="${oldDog.age}"><br>
        </div>
        <div class="form-group">
            <label for="healthnotes">Uwagi dotyczące zdrowia</label><br>
            <textarea name="healthnotes" required id="healthnotes"
                      class="form-control">${oldDog.health_notes}</textarea><br>
        </div>
        <input type="submit" value="Zapisz zmiany" class="btn btn-lg btn-primary btn-block">
    </form>


</div>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Resources/js/bootstrap.js"></script>
</body>
</html>
