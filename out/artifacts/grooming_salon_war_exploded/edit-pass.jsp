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
    <title>Edit Password</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Resources/css/styles.css" type="text/css" rel="stylesheet">
    <script src="myscript.js"></script>
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
<br><br>
<div class="col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
    <c:set var="user" value="${user}"></c:set>
    <form method="post" action="/OptionsServlet?param=confirm_pass" class="text-center">
        <div class="form-group">
            <label for="oldPass">Stare hasło:</label><br>
            <input type="password" name="oldPass" required id="oldPass" class="form-control"
                   placeholder="Wpisz stare hasło">
        </div>
        <br>
        <div class="form-group">
            <label for="newPass">Wpisz nowe hasło:</label><br>
            <input type="password" name="newPass" required id="newPass" class="form-control"
                   placeholder="Wpisz nowe hasło" onkeyup="passCheck()"><br>
        </div>
        <br>
        <div class="form-group">
            <label for="newPassConf">Powtórz nowe hasło:</label><br>
            <input type="password" name="newPassConf" required id="newPassConf" class="form-control"
                   placeholder="Powtórz nowe hasło" onkeyup="passCheck()"><br>
            <p id="passConfText" style="color:red"></p>
        </div>
        <br>
        <input type="submit" value="Zapisz " class="btn btn-lg btn-primary btn-block" id="passChangeButton">
    </form>
</div>
<div class="container-fluid optionalDiv">
    <div class="col-sm-8 col-md-8 col-lg-6 col-xl-4 text-center">
        <%if (request.getAttribute("msg_ok") != null) {%>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <%=request.getAttribute("msg_ok")%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>

        <%if (request.getAttribute("msg_nok") != null) {%>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <%=request.getAttribute("msg_nok")%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>
    </div>
</div>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Resources/js/bootstrap.js"></script>
</body>
</html>
