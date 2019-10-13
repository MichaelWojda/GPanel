<%--
  Created by IntelliJ IDEA.
  User: PLMIWOJ4
  Date: 29.08.2019
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Profile Site</title>
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
            <li class="nav-item"><a class="nav-link" href="OptionsServlet?param=back">Powrót do strony głównej</a></li>
            <li class="nav-item"><a class="nav-link" href="/LogoutServlet">Wyloguj się</a></li>
        </ul>
    </div>
</nav>
<div class="container-fluid col-sm-8 col-md-8 col-lg-6 col-xl-4">
    <h2 class="display-1 text-center">Edycja profilu:</h2>
    <c:set var="user" value="${userOld}"></c:set>
    <form action="/OptionsServlet?param=change_user_profile" method="post" class="form-signin text-center">
        <h2><small>${user.username}</small></h2>
        <div class="form-group">
            <label for="userFname">First Name:</label>
            <input type="text" name="userFirstNameNew" id="userFname" value="${user.firstname}" class="form-control">
        </div>
        <div class="form-group">
            <label for="userLname">Last Name:</label>
            <input type="text" name="userLastNameNew" id="userLname" value="${user.lastname}" class="form-control">
        </div>
        <div class="form-group">
            <label for="phonenumber">Phone number:</label>
            <input type="text" name="newPhoneNumber" id="phonenumber" value="${user.phonenumber}" class="form-control">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" name="newEmail" id="email" value="${user.email}" class="form-control">
        </div>
        <input type="submit" value="Wyślij" class="btn btn-lg btn-primary btn-block">
    </form>
    <a class="btn btn-lg btn-block btn-danger" href="/OptionsServlet?param=change_pass">Zmień hasło</a>


</div>
</body>
</html>

