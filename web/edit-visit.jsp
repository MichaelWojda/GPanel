<%--
  Created by IntelliJ IDEA.
  User: PLMIWOJ4
  Date: 19.08.2019
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Visit Site</title>
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
            <li class="nav-item"><a class="nav-link" href="admin.jsp">Powrót do panelu głównego</a></li>
            <li class="nav-item"><a class="nav-link" href="/LogoutServlet">Wyloguj się</a></li>
        </ul>
    </div>
</nav>
<div id="editSingleVisit" class="jumbotron col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
    <c:if test="${not empty singleVisit}">
        <c:set var="visit" value="${singleVisit}"></c:set>
        <form action="OptionsServlet?param=update&id=${singleVisit.id_visit}" method="post">
            <h2 class="display-4">Klient: ${singleVisit.customer.username}</h2><br>
            <h2 class="display-4">Pies: ${singleVisit.dog.dogname}</h2><br>
            <div class="form-group text-center">
                <label for="pracownik">Pracownik:</label>
                <div class="dropdown">
                    <select name="groomer" class="form-control" data-toggle="dropdown"
                            id="pracownik">
                        <div class="dropdown-menu">
                            <c:forEach var="groomer" items="${employees}">
                                <option value="${groomer.username}" class="dropdown-item">${groomer.username}</option>
                            </c:forEach><br>
                        </div>
                    </select>
                </div>
            </div>
            <br><br>
            <div class="form-group text-center">
                <label for="date">Data:</label><br>
                <input type="date" name="newDate" id="date"></div>
            <br>
            <div class="form-group text-center">
                <label for="hour">Godzina:</label><br>
                <select name="hour" class="form-control" data-toggle="dropdown" id="hour">
                    <div class="dropdown-menu">
                        <option value="8:00" class="dropdown-item">8:00</option>
                        <option value="8:30" class="dropdown-item">8:30</option>
                        <option value="9:00" class="dropdown-item">9:00</option>
                        <option value="9:30" class="dropdown-item">9:30</option>
                        <option value="10:00" class="dropdown-item">10:00</option>
                        <option value="10:30" class="dropdown-item">10:30</option>
                        <option value="11:00" class="dropdown-item">11:00</option>
                        <option value="11:30" class="dropdown-item">11:30</option>
                        <option value="12:00" class="dropdown-item">12:00</option>
                        <option value="12:30" class="dropdown-item">12:30</option>
                        <option value="13:00" class="dropdown-item">13:00</option>
                        <option value="13:30" class="dropdown-item">13:30</option>
                        <option value="14:00" class="dropdown-item">14:00</option>
                        <option value="14:30" class="dropdown-item">14:30</option>
                        <option value="15:00" class="dropdown-item">15:00</option>
                        <option value="15:30" class="dropdown-item">15:30</option>
                        <option value="16:00" class="dropdown-item">16:00</option>
                        <option value="16:30" class="dropdown-item">16:30</option>
                        <option value="17:00" class="dropdown-item">17:00</option>
                    </div>
                </select></div>
            <br><br>
            <input type="submit" value="Zmień" class="btn btn-primary btn-lg">
        </form>
    </c:if>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">

            </div>
        </div>
    </div>
</div>

</body>
</html>
