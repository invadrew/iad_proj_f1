<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/RaceRegistrationStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Регистрация на гонку</title>
</head>
<body>
<script type="text/javascript" src="../scripts/RanksPageScript.js"> </script>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="MainArea">
        <div class="InfoArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <center><h3>Ближайшая гонка</h3></center>
                    ${currRace[0][1]} ${currRace[0][0]}
                    <br>
                    Макс. участников: ${currRace[0][5]}
                    <br>
                    Макс. команд: ${currRace[0][5]/2}
                    <br>
                    Начало: <fmt:formatDate value="${currRace[0][3]}" pattern="dd-MM-yyyy HH:mm" />
                    <br>
                    Номер этапа: ${currRace[0][2]}
                    <br>
                    Трасса: ${currRace[0][4]}
                </div>
                <div class="infotab">
                    <center><h3>Принять участие</h3></center>
                    <sec:authorize access="hasAuthority('MANAGER')">
                        <c:url value="/team" var="uUrl">
                            <c:param name="id" value="${team.id}"/>
                        </c:url>
                        <a class="redirHref" href="${uUrl}">
                            Команда: ${team.name}
                        </a>
                    <br> <br>
                    <form>
                        <label for="firstPilotreg">Первый пилот:</label>
                        <select class="res-selector" id="firstPilotreg">
                            <c:forEach items="${racers}" var="racer">
                                <option value="${racer[0]}"> <c:out value="${racer[1]} ${racer[2]}"/> </option>
                            </c:forEach>
                        </select>
                        <label for="firstCarreg">Болид:</label>
                        <select class="res-selector" id="firstCarreg">
                            <c:forEach items="${cars}" var="car">
                                <option value="${car.id}"> <c:out value="${car.label} ${car.model}"/> </option>
                            </c:forEach>
                        </select>
                        <br>
                        <label for="secPilotreg">Второй пилот:</label>
                        <select class="res-selector" id="secPilotreg">
                            <c:forEach items="${racers}" var="racer">
                                <option value="${racer[0]}"> <c:out value="${racer[1]} ${racer[2]}"/> </option>
                            </c:forEach>
                        </select>
                        <label for="secCarreg">Болид:</label>
                        <select class="res-selector" id="secCarreg">
                            <c:forEach items="${cars}" var="car">
                                <option value="${car.id}"> <c:out value="${car.label} ${car.model}"/> </option>
                            </c:forEach>
                        </select>
                        <br> <br>
                        <center><input type="submit" class="res-selector" value="Зарегистрироваться"></center>
                    </form>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('RACER','MECHANIC','CONSTRUCTOR','SPONSOR','ADMIN')">
                        Только менеджеры регестритруют команды
                    </sec:authorize>
                </div>
                <div class="infotab" style="text-align: center" id="toArch">
                    Узнать результаты прошлых гонок:
                    <input type="button" value="В архив" class="res-selector" onclick="location.href='/race-res'">
                </div>
            </div>
        </div>
        <div class="RegistrationTableArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <center> <h3>Список участников</h3> </center>
                    <table class="infotable" border="1">
                        <tr>
                            <th>№</th>
                            <th>Гонщик</th>
                            <th>Команда</th>
                            <th>Болид</th>
                        </tr>
                        <% int i = 0;%>
                        <c:forEach items="${regTable}" var="row">
                        <tr>
                            <% i++;%>
                            <td> <%= i%> </td>
                            <td>
                                <c:url value="/profile" var="uUrl">
                                    <c:param name="id" value="${row[5]}"/>
                                </c:url>
                                <a class="redirHref" href="${uUrl}">
                                        ${row[0]} ${row[1]}
                                </a>
                            </td>
                            <td>
                                <c:url value="/team" var="uUrl">
                                    <c:param name="id" value="${row[6]}"/>
                                </c:url>
                                <a class="redirHref" href="${uUrl}">
                                        ${row[2]}
                                </a>
                            </td>
                            <td> ${row[3]} ${row[4]} </td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>