<%--
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Grooming Salon Panel</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Resources/css/styles.css" type="text/css" rel="stylesheet">
    <script src="myscript.js"></script>

</head>
<body class="text-center">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Resources/js/bootstrap.js"></script>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <a href="index.jsp" class="navbar-brand">GroomingShop Panel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="index.jsp">Główna</a></li>
            <li class="nav-item"><a class="nav-link" href="register.jsp">Rejestracja</a></li>
            <li class="nav-item text-right"><a class="nav-link" href="index.jsp">Zaloguj się</a></li>
        </ul>
    </div>
</nav>
<br>
<br>
<div class="col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
    <form action="LoginServlet" method="post" class="form-signin text-center">
        <h1 class="display-4">Logowanie</h1>
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" name="login" placeholder="Wpisz login" required class="form-control" id="login">
        </div>
        <div class="form-group">
            <label for="pwd">Hasło:</label>
            <input type="password" name="pass" placeholder="Wpisz hasło" required class="form-control" id="pwd">
        </div>
        <input type="submit" value="Zaloguj" class="btn btn-lg btn-primary btn-block">
        <%if (request.getAttribute("error") != null) {%>
        <div class="alert alert-danger" alert-dismissible fade show role="alert">
            <%=request.getAttribute("error")%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>

    </form>
</div>
</body>
</html>
