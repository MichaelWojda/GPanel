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
    <title>Book Visit</title>
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
<div class="table-responsive-md">
<table class="table table-bordered table-striped container-fluid">
    <tr>
        <th>Groomer</th>
        <th>Data</th>
        <th>Godzina</th>
        <th>Zabookuj wizyte</th>
    </tr>
    <c:forEach var="visit" items="${availableVisits}">
        <tr>
            <td>${visit.groomer.username}</td>
            <td>${visit.visit_date}</td>
            <td><c:choose>
                <c:when test="${visit.visit_time eq '00:00'}">
                    <p>12:00</p>
                </c:when>
                <c:otherwise>
                    ${visit.visit_time}
                </c:otherwise>
            </c:choose></td>
            <td><a class="btn btn-danger" href="/CustomerVisitServlet?param=book&id=${visit.id_visit}&id_dog=${id_dog}">Zabookuj
                wizytę</a></td>
        </tr>
    </c:forEach>
</table>
</div>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Resources/js/bootstrap.js"></script>
</body>
</html>
