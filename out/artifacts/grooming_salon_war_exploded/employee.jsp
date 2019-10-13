<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Employee Site</title>
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
            <li class="nav-item"><a class="nav-link" href="EmployeeOptionsServlet?param=myVisits">Mój plan wizyt</a>
            </li>
            <li class="nav-item"><a class="nav-link" href="EmployeeOptionsServlet?param=history">Historia Wizyt</a></li>
            <li class="nav-item"><a class="nav-link" href="EmployeeOptionsServlet?param=customers">Lista klientów</a>
            </li>
            <li class="nav-item"><a class="nav-link" href="EmployeeOptionsServlet?param=dogs">Lista psów</a></li>
            <li class="nav-item"><a class="nav-link" href="/OptionsServlet?param=edit_profile">Edytuj profil</a></li>
            <li class="nav-item"><a class="nav-link" href="/LogoutServlet">Wyloguj się</a></li>
        </ul>
    </div>
</nav>
<c:if test="${currentVisitList ne null}">
    <br><br>
    <div class="input-group row justify-content-center align-self-center">
        <input type="text" id="searchCVisits" onkeyup="searchCVisits()" placeholder="Szukaj"
               class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2">
        <div class="input-group-prepend">
            <span class="input-group-text">Kryterium</span>
        </div>
        <select name="hour" class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2" data-toggle="dropdown"
                id="selectCVisitsCriterium"
                onchange="searchCVisits()">
            <div class="dropdown-menu">
                <option value=0 class="dropdown-item">Pies</option>
                <option value=3 class="dropdown-item">Klient</option>
                <option value=5 class="dropdown-item">Data</option>
            </div>
        </select>
    </div>
    <br><br>
    <div class="table-responsive-md">
        <table class="table table-bordered table-striped container-fluid"
               id="cVisitsTable">
            <tr>
                <th>Pies</th>
                <th>Notatki dot. zdrowia</th>
                <th>Notatki groomera</th>
                <th>Klient</th>
                <th>Telefon</th>
                <th>Data</th>
                <th>Godzina</th>
                <th>Odwołaj wizytę</th>
            </tr>
            <c:forEach var="visit" items="${currentVisitList}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${visit.customer.username eq 'default_value'}">
                                <p style="color:darkgreen">WOLNY</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="dog" items="${doglist}">
                                    <c:if test="${dog.id_dog eq visit.dog.id_dog}">
                                        <p>${visit.dog.dogname}</p>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${visit.customer.username eq 'default_value'}">
                                <p style="color:darkgreen">WOLNY</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="dog" items="${doglist}">
                                    <c:if test="${dog.id_dog eq visit.dog.id_dog}">
                                        <p>${visit.dog.health_notes}</p>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${visit.customer.username eq 'default_value'}">
                                <p style="color:darkgreen">WOLNY</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="dog" items="${doglist}">
                                    <c:if test="${dog.id_dog eq visit.dog.id_dog}">
                                        <p>${visit.dog.groomer_notes}</p>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
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
                    <td>
                        <c:choose>
                            <c:when test="${visit.customer.username eq 'default_value'}">
                                <p style="color:darkgreen">WOLNY</p>
                            </c:when>
                            <c:otherwise>
                                ${visit.customer.phonenumber}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${visit.visit_date}</td>
                    <td><c:choose>
                        <c:when test="${visit.visit_time eq '00:00'}">
                            <p>12:00</p>
                        </c:when>
                        <c:otherwise>
                            ${visit.visit_time}
                        </c:otherwise>
                    </c:choose></td>
                    <td>
                        <c:choose>
                            <c:when test="${visit.customer.username eq 'default_value'}">
                                <h2>
                                    <small>N/A</small>
                                </h2>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-danger"
                                   href="/EmployeeOptionsServlet?param=cancel&id=${visit.id_visit}">Anuluj
                                    wizytę</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</c:if>
<c:if test="${historyVisitList ne null}">
    <br><br>
    <div class="input-group row justify-content-center align-self-center">
        <input type="text" id="searchHVisits" onkeyup="searchHVisits()" placeholder="Szukaj"
               class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2">
        <div class="input-group-prepend">
            <span class="input-group-text">Kryterium</span>
        </div>
        <select name="hour" class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2" data-toggle="dropdown"
                id="selectHVisitsCriterium"
                onchange="searchHVisits()">
            <div class="dropdown-menu">
                <option value=0 class="dropdown-item">Pies</option>
                <option value=3 class="dropdown-item">Klient</option>
                <option value=5 class="dropdown-item">Data</option>
            </div>
        </select>
    </div>
    <br><br>
    <div class="table-responsive-md">
        <table class="table table-bordered table-striped container-fluid"
               id="hVisitsTable">
            <tr>
                <th>Pies</th>
                <th>Notatki dot. zdrowia</th>
                <th>Notatki groomera</th>
                <th>Klient</th>
                <th>Telefon</th>
                <th>Data</th>
                <th>Godzina</th>
            </tr>
            <c:forEach var="visit" items="${historyVisitList}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${visit.customer.username eq 'default_value'}">
                                <p style="color:darkgreen">WOLNY</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="dog" items="${doglist}">
                                    <c:if test="${dog.id_dog eq visit.dog.id_dog}">
                                        <p>${dog.dogname}</p>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${visit.dog.health_notes}</td>
                    <td>${visit.dog.groomer_notes}</td>
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
                    <td>${visit.customer.phonenumber}</td>
                    <td>${visit.visit_date}</td>
                    <td><c:choose>
                        <c:when test="${visit.visit_time eq '00:00'}">
                            <p>12:00</p>
                        </c:when>
                        <c:otherwise>
                            ${visit.visit_time}
                        </c:otherwise>
                    </c:choose></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</c:if>
<c:if test="${customersList ne null}">
    <br><br>
    <div class="input-group row justify-content-center align-self-center">
        <input type="text" id="searchCustomers" onkeyup="searchCustomers()" placeholder="Szukaj"
               class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2">
        <div class="input-group-prepend">
            <span class="input-group-text">Kryterium</span>
        </div>
        <select name="hour" class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2" data-toggle="dropdown"
                id="searchCustomersCriterium"
                onchange="searchCustomers()">
            <div class="dropdown-menu">
                <option value=0 class="dropdown-item">Nick</option>
                <option value=1 class="dropdown-item">Imię</option>
                <option value=2 class="dropdown-item">Nazwisko</option>
            </div>
        </select>
    </div>
    <br><br>
    <div class="table-responsive-md">
        <table class="table table-bordered table-striped container-fluid"
               id="customersTable">
            <tr>
                <th>Nick</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Telefon</th>
                <th>Email</th>
            </tr>
            <c:forEach var="user" items="${customersList}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.phonenumber}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<c:if test="${allDogList ne null}">
    <br><br>
    <div class="input-group row justify-content-center align-self-center">
        <input type="text" id="searchDogs" onkeyup="searchDogs()" placeholder="Szukaj"
               class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2">
        <div class="input-group-prepend">
            <span class="input-group-text">Kryterium</span>
        </div>
        <select name="hour" class="form-control col-sm-4 col-md-2 col-lg-2 col-xl-2" data-toggle="dropdown"
                id="searchDogsCriterium"
                onchange="searchDogs()">
            <div class="dropdown-menu">
                <option value=0 class="dropdown-item">Imię</option>
                <option value=1 class="dropdown-item">Rasa</option>
                <option value=5 class="dropdown-item">Właściciel</option>
            </div>
        </select>
    </div>
    <br><br>
    <div class="table-responsive-md">
        <table class="table table-bordered table-striped container-fluid" id="dogsTable">
            <tr>
                <th>Imię</th>
                <th>Rasa</th>
                <th>Wiek</th>
                <th>Informacje o zdrowiu</th>
                <th>Notatki Groomera</th>
                <th>Właściciel</th>
                <th>Dodaj notatkę</th>
            </tr>
            <c:forEach var="dog" items="${allDogList}">
                <tr>
                    <td>${dog.dogname}</td>
                    <td>${dog.race}</td>
                    <td>${dog.age}</td>
                    <td>${dog.health_notes}</td>
                    <td>${dog.groomer_notes}</td>
                    <td>${dog.dogowner.firstname} ${dog.dogowner.lastname}</td>
                    <td><a class="btn btn-dark" href="/EmployeeOptionsServlet?param=gnote&id=${dog.id_dog}">Dodaj
                        notatkę</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<br>
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
