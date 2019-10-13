<%--
  Created by IntelliJ IDEA.
  User: michaelwong
  Date: 19.07.19
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body class="text-center">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <a href="index.jsp" class="navbar-brand">GroomingShop Panel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="index.jsp">Główna</a></li>
            <li class="nav-item"><a class="nav-link" href="register.jsp">Rejestracja</a></li>
            <li class="nav-item"><a class="nav-link" href="index.jsp">Zaloguj się</a></li>
        </ul>
    </div>
</nav>

<br>
<br>
<div class="col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
    <form method="post" action="RegistrationServlet" class="form-signin text-center">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" name="login" placeholder="Wpisz login" required id="login" class="form-control"><br>
        </div>
        <div class="form-group">
            <label for="pass">Hasło:</label>
            <input type="password" name="pass" placeholder="Wpisz hasło" required id="pass" class="form-control"><br>
        </div>
        <div class="form-group">
            <label for="name">Imię:</label>
            <input type="text" name="name" placeholder="Podaj imię" required id="name" class="form-control"><br>
        </div>
        <div class="form-group">
            <label for="surname">Nazwisko:</label>
            <input type="text" name="surname" placeholder="Podaj nazwisko" required id="surname"
                   class="form-control"><br>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" name="email" placeholder="Wpisz email" required id="email" class="form-control"><br>
        </div>
        <div class="form-group">
            <label for="phonenumber">Numer telefonu:</label>
            <input type="number" name="phonenumber" placeholder="Wpisz numer telefonu" required id="phonenumber"
                   class="form-control"><br>
        </div>
        <input type="submit" value="Wyślij" class="btn btn-lg btn-primary btn-block">
        <% if (request.getAttribute("error") != null) {%>
        <div class="alert-danger alert-dismissible fade show" role="alert"><%=request.getAttribute("error")%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>
    </form>
</div>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Resources/js/bootstrap.js"></script>
</body>
</html>
