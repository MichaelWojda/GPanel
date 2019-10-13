<%--
  Created by IntelliJ IDEA.
  User: michaelwong
  Date: 18.07.19
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Customer Site</title>
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
            <li class="nav-item"><a class="nav-link" onclick="showMyDog()">Mój pies</a></li>
            <li class="nav-item"><a class="nav-link" onclick="addDog()">Dodaj psa</a></li>
            <li class="nav-item"><a class="nav-link" onclick="showVisits()">Wizyty</a></li>
            <li class="nav-item"><a class="nav-link" href="/OptionsServlet?param=edit_profile">Edytuj profil</a></li>
            <li class="nav-item"><a class="nav-link" href="/LogoutServlet">Wyloguj się</a></li>
        </ul>
    </div>
</nav>
<div id="mydog" style="display:none" class="table-responsive-md">
    <c:if test="${not empty doglist}">
    <table class="table table-bordered table-striped container-fluid">
        <tr>
            <th>Imię</th>
            <th>Rasa</th>
            <th>Wiek</th>
            <th>Uwagi dotyczące zdrowia</th>
            <th>Edycja</th>
            <th>Usuń</th>
        </tr>
        </c:if>
        <c:forEach var="dog" items="${doglist}">
            <tr>
                <td>${dog.dogname}</td>
                <td>${dog.race}</td>
                <td>${dog.age}</td>
                <td>${dog.health_notes}</td>
                <td><a href="/CustomerPanelServlet?param=edit&id=${dog.id_dog}" class="btn btn-primary">Edit</a></td>
                <td><a href="/CustomerPanelServlet?param=delete&id=${dog.id_dog}" class="btn btn-primary">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="visits" style="display:none" class="col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
    <button class="btn btn-lg btn-primary btn-block text-center" data-toggle="collapse" data-target="#newVisit">Umów
        wizytę
    </button>
    <br>
    <div id="newVisit" class="collapse">
        <div class="form-group">
            <form action="CustomerVisitServlet?param=bookVisit" method="post" class="form-signin text-center">
                <h1>
                    <small>Wybierz zakres dat:</small>
                </h1>
                <br>
                <label for="fromDate">Od:</label>
                <input type="date" name="fromDate" class="form-control" id="fromDate" required>
                <label for="toDate">Do:</label>
                <input type="date" name="toDate" class="form-control" id="toDate" required><br>
                <h1>
                    <small>Wybierz psa:</small>
                </h1>
                <br>
                <div class="dropdown">
                    <select class="form-control" name="dog" required data-toggle="dropdown">
                        <div class="dropdown-menu">
                            <c:forEach var="dog" items="${doglist}">
                                <option value="${dog.id_dog}" class="dropdown-item">${dog.dogname}</option>
                            </c:forEach>
                        </div>
                    </select>
                </div>
                <br><br>
                <input type="submit" value="Szukaj" class="btn btn-lg btn-danger btn-block">
            </form>
        </div>
    </div>
    <a class="btn btn-lg btn-primary btn-block text-center" href="#mVisits" data-toggle="collapse">Moje
        wizyty
    </a><br>
    <div class="collapse table-responsive-md" id="mVisits">
        <table class="table table-bordered table-striped container-fluid">
            <tr>
                <th>Pies</th>
                <th>Groomer</th>
                <th>Data</th>
                <th>Godzina</th>
                <th>Odwołaj wizytę</th>
            </tr>

            <c:forEach var="visit" items="${currentVisits}">
                <c:if test="${visit.customer.username ne 'default_value'}">
                    <tr>
                        <td>
                            <c:forEach var="dog" items="${doglist}">
                                <c:if test="${dog.id_dog eq visit.dog.id_dog}">
                                    <p>${dog.dogname}</p>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${visit.groomer.username}</td>
                        <td>${visit.visit_date}</td>
                        <td>${visit.visit_time}</td>
                        <td><a class="btn btn-danger" href="/CustomerVisitServlet?param=cancel&id=${visit.id_visit}">Anuluj
                            wizytę</a></td>
                    </tr>
            </c:if>
            </c:forEach>

        </table>

    </div>
    <a class="btn btn-lg btn-primary btn-block text-center" href="/CustomerVisitServlet?param=visitHistory">
        Historia Wizyt
    </a>
</div>
<div id="addDog" style="display:none" class="col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid jumbotron">
    <h1 class="text-center">
        <small>Dodaj psa</small>
    </h1>
    <form action="CustomerPanelServlet?param=addDog" method="post" class="form-signin text-center">
        <div class="form-group">
            <label for="dogname">Imię psa</label><br>
            <input type="text" name="dogname" placeholder="Wpisz imię psa" required id="dogname"
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
            <input type="number" name="age" required id="age" class="form-control"><br>
        </div>
        <div class="form-group">
            <label for="healthnotes">Uwagi dotyczące zdrowia</label><br>
            <textarea name="healthnotes" required id="healthnotes" class="form-control"></textarea><br>
        </div>
        <input type="submit" value="Dodaj psa" class="btn btn-lg btn-primary btn-block">
    </form>


</div>
<div class="row justify-content-center align-self-center">
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
