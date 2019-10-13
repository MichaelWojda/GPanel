<%--
  Created by IntelliJ IDEA.
  User: michaelwong
  Date: 18.07.19
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Site</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Resources/css/styles.css" type="text/css" rel="stylesheet">
    <script src="adminscript.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <a href="index.jsp" class="navbar-brand">GroomingShop Panel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="#" onclick="showPlan()">Plan pracy</a></li>
            <li class="nav-item"><a class="nav-link" href="#" onclick="showVisits()">Wyświetl wizyty</a></li>
            <li class="nav-item"><a class="nav-link" href="/OptionsServlet?param=showUsers">Użytkownicy</a></li>
            <li class="nav-item"><a class="nav-link" href="/LogoutServlet">Wyloguj się</a></li>
        </ul>
    </div>
</nav>
<br><br>
<div id="planDaySetUpRegular" class="insideDiv col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
    <h2 class="display-4">Ustaw plan pracy:</h2><br>
    <form action="VisitSetupServlet" method="post">
        <div class="input-group row justify-content-center align-self-center">
            <div class="input-group-prepend">
                <span class="input-group-text">Od:</span>
            </div>
            <input type="date" name="fromDate" id="fromDate" class="form-control">
            <div class="input-group-prepend">
                <span class="input-group-text">Do:</span>
            </div>
            <input type="date" name="toDate" id="toDate" class="form-control">
        </div>
        <br><br>
        Pracownik:
        <select name="groomer" class="form-control">
            <c:forEach var="groomer" items="${employees}">
                <option value="${groomer.username}">${groomer.username}</option>
            </c:forEach>
        </select><br><br>
        Ilość psów
        <select name="dogNumber" class="form-control">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select><br><br>
        Godzina rozpoczęcia pracy:
        <select name="startHour" class="form-control">
            <option value="8:00">8:00</option>
            <option value="8:30">8:30</option>
            <option value="9:00">9:00</option>
            <option value="9:30">9:30</option>
            <option value="10:00">10:00</option>
        </select><br><br>
        Odstęp:
        <select name="interval" class="form-control">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select><br>
        <input type="submit" value="Wprowadź" class="btn btn-lg btn-primary btn-block">
    </form>
</div>
<div id="displayPlanOfTheDay" class="insideDiv col-sm-8 col-md-8 col-lg-6 col-xl-4 container-fluid">
    <form action="VisitSetupServlet" method="get">
        <h2 class="display-4">Wpisz szukaną datę:</h2><br>
        <div class="input-group row justify-content-center align-self-center">
            <input type="date" name="fDate" class="form-control">
            <div class="input-group-prepend">
                <span class="input-group-text">Do:</span>
            </div>
            <input type="date" name="tDate" class="form-control">
        </div>
        <br>
        <input type="submit" class="btn btn-lg btn-primary btn-block row justify-content-center align-self-center"
               value="Szukaj">
    </form>
    <br><br>
    <div class="lub">
        <h2 class="display-4">Lub:</h2><br>
        <a class="btn btn-lg btn-primary btn-block row justify-content-center align-self-center w-75"
           href="/OptionsServlet?param=showAll">Wyświetl wszytskie wizyty</a>
    </div>
</div>
<div class="optionalDiv table-responsive-md">
    <c:if test="${not empty visitList}">
    <table class="table table-bordered table-striped container-fluid">
        <tr>
            <th>Customer</th>
            <th>Groomer</th>
            <th>Dog</th>
            <th>Visit Date</th>
            <th>Visit Time</th>
            <th>Booked</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </c:if>
        <c:forEach var="visit" items="${visitList}">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${visit.customer.username eq 'default_value'}">
                            <p style="color:darkgreen">WOLNY</p>
                        </c:when>
                        <c:otherwise>
                            ${visit.customer.username}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${visit.groomer.username}</td>
                <td>
                    <c:choose>
                        <c:when test="${visit.customer.username eq 'default_value'}">
                            <p style="color:darkgreen">WOLNY</p>
                        </c:when>
                        <c:otherwise>
                            ${visit.dog.dogname}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${visit.visit_date}</td>
                <td>${visit.visit_time}</td>
                <td>${visit.booked}</td>
                <td><a href="/OptionsServlet?param=edit&id=${visit.id_visit}" class="btn btn-primary">Edit</a></td>
                <td><a href="/OptionsServlet?param=delete&id=${visit.id_visit}" class="btn btn-primary">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="optionalDiv table-responsive-md">
    <c:if test="${not empty userList}">
    <table class="table table-bordered table-striped container-fluid">
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Imie</th>
            <th>Nazwisko</th>
            <th>Numer telefonu</th>
            <th>Email</th>
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </c:if>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id_user}</td>
                <td>${user.username}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.phonenumber}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td><a href="/OptionsServlet?param=edit_user&id=${user.id_user}" class="btn btn-primary">Edit</a></td>
                <td><a href="/OptionsServlet?param=delete_user&id=${user.id_user}" class="btn btn-primary">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
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
