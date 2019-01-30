<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/MainPageStyle.css">
    <script type="text/javascript" src="/scripts/MainPageScript.js"> </script>
    <title> Добро пожаловать! </title>
</head>
<body>
    <div class="grid-container">
        <div class="MainPageHeader" >
            <div class="HeaderContainer">
                <img src="/pictures/Formula_1_logo.jpg">
            </div>
        </div>
        <div class="LoginForm">
            <div class="inside_block_wrapper">
                <h2>Вход в систему</h2>
               <form id = "login" action="/security_check" method="post">
                   <label for="userName">Логин</label>
                   <input type="text" id="userName" name="uN" maxlength="20">
                   <br> <br>
                   <label for="pass">Пароль</label>
                   <input type=password id="pass" name="uP">
                   <br> <br>
                   <input type="submit" value="Войти" id="getIn">
               </form>
                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                    <br>
                    <label>Неверный логин!</label>
                </c:if>
            </div>
        </div>
        <div class="GreetingsBlock">
            <div class="inside_block_wrapper">
            <p><i> Добро пожаловать в самую современную систему для гонщиков и команд "Формулы-1",
            где вы найдёте всё, что Вам необходимо для успешной карьеры и
                бесконечного процветания во всемирно известных автомобильных гонках </i> </p>
            </div>
        </div>
        <div class="MainPageResultsList">
            <div class="inside_block_wrapper">
                <h2> Рейтинг команд</h2>
                <table border="1" class="MainPageResTable">
                    <tr>
                        <th>Место</th>
                        <th>Команда</th>
                        <th>Очков</th>
                    </tr>
                    <c:set var="maxSeason">99999</c:set>
                    <c:forEach items="${mainPageResTable}" var="res">
                        <c:if test="${res[0]< maxSeason}">
                            <tr><td colspan="3"> Сезон: ${res[0]} </td></tr>
                            <c:set var="maxSeason" value="${res[0]}"/>
                        </c:if>
                            <tr>
                                <td> <c:out value="${res[1]}"/> </td>
                                <td> <c:out value="${res[2]}"/> </td>
                                <td> <c:out value="${res[3]}"/> </td>
                            </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
        <div class="CurrentChampTable">
            <div class="inside_block_wrapper">
                <h2> Текущее событие </h2>
                <c:set var="info" value="${currentEvent}"/>
                <table border="1" class="MainPageResTable">
                    <tr><td>Сезон: ${info[0][0]} </td></tr>
                    <tr><td>Чемпионат: ${info[0][1]}</td></tr>
                    <tr><td>Номер этапа в текущем чемпионате: ${info[0][2]}</td></tr>
                    <tr><td>Дата ближайшей гонки:
                        <fmt:formatDate value="${info[0][3]}" pattern="dd-MM-yyyy HH:mm:ss" />
                    </td></tr>
                    <tr><td>Трасса: ${info[0][4]}</td></tr>
                </table>
            </div>
        </div>
        <div class="LatestNewsBlock">
            <div class="inside_block_wrapper">
                <c:set var="sponsNews" value="${newSponsNews}"/>
                <c:set var="raceNews" value="${newRaceNews}"/>
                    <h1> Последние новости </h1>
                    <table style="text-align: left">
                    <tr><td>- Спонсирование: <fmt:formatDate value="${sponsNews[0][3]}" pattern="dd-MM-yyyy HH:mm:ss" /> <br>
                    ${sponsNews[0][1]} перевел на счёт ${sponsNews[0][0]} сумму в размере ${sponsNews[0][2]} рублей </td></tr>
                        <tr><td>
                            - Гонка: <fmt:formatDate value="${raceNews[0][2]}" pattern="dd-MM-yyyy HH:mm:ss" /> <br>
                            ${raceNews[0][0]} ${raceNews[0][1]} из команды ${raceNews[0][5]} выиграл гонку чемпионата ${raceNews[0][3]}
                            ${raceNews[0][4]} года
                        </td></tr>
                    </table>
            </div>
        </div>
    </div>
</body>
</html>