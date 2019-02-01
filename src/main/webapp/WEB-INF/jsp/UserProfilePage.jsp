<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/UserProfileStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Профиль пользователя</title>
</head>
<body>
<div class="grid-container">
    <!-- TODO: try to make ui component for header zone-->
    <div class="Header">
        <div class="Header-LogoContainer">
            <img src="/pictures/Formula_1_logo.jpg">
        </div>
        <div class="Header-UserInfo-container">
            ${nameSurname}
            <form action="/logout">
                <input type="submit" value="Выйти" id="out">
            </form>
        </div>
        <div class="UserPhoto-container">
            <img src="/pictures/rogoVK.jpg">
        </div>
    </div>
    <div class="HeaderMenu">
        <ul>
            <li><a href="MainPage.jsp">Профиль</a></li>
            <li><a href="MainPage.jsp">Команда</a></li>
            <li><a href="MainPage.jsp">Гараж</a></li>
            <li><a href="MainPage.jsp">Заезды</a></li>
            <li><a href="RanksPage.jsp">Рейтинг</a></li>
            <li><a href="MainPage.jsp">Сообщения</a></li>
        </ul>
        <div class="searcher">
            <form>
                <input type="text" placeholder="Поиск по сайту" id="search">
                <input type="submit" value="Найти" id="find">
            </form>
        </div>
    </div>
    <!-- end if-->
    <div class="MainArea">
        <div class="UserPhotoArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <div class="pic-container">
                        <img src="/pictures/rogoVK.jpg">
                    </div>
                </div>
        </div>
        </div>
        <div class="UserInfoArea">
          <div class="inside_block_wrapper"  style="height: 95%">
            <div class="infotab" style="height: 93.5%">
                <center><h2>${nameSurname}</h2></center>
                Команда: ${team}
                <br> <br>
                Должность: ${spec}
                <c:if test="${!(user.spec.toString().equals('RACER'))}">
                <br> <br> <br>
                    <i>Статистика пользователя совпадает с командной</i>
                </c:if>
            </div>
          </div>
        </div>
        <div class="StatisticsArea">
            <c:if test="${user.spec.toString().equals('RACER')}">
            <div class="inside_block_wrapper">
                <div class="infotab" >
                    <center>
                        <h3>Статистика</h3> </center>
                    <table class="infotable" style="font-size: 18pt; text-align: left">
                        <tr><td>Количество очков в сзоне - ${pointsCount}</td></tr>
                        <tr><td>Количество заездов в сезоне - ${racingsCount}</td></tr>
                        <tr><td>Лучшее место в текущем сезоне - ${bestPlace}</td></tr>
                        <tr><td>Среднее место на финише за сезон - ${averagePlaceAtSeason}</td></tr>
                        <tr><td>Общее количество заездов - ${allRaceCount}</td></tr>
                        <tr><td>Средний результат на всех заездах - ${averagePlaceAtAll}</td></tr>
                        <tr><td>Лучшее время гонки - <fmt:formatDate value="${getBestTrackTime[0][0]}" pattern="HH:mm:ss" />
                        Трасса - ${getBestTrackTime[0][1]} </td></tr>
                        <tr><td>Выиграно кубков мира - ${cupsWon}</td></tr>
                        <tr><td>Участвовал в ${champCount} чемпионатах</td></tr>
                    </table>
                </div>
            </div>
            </c:if>
        </div>
        <div class="UserAchArea">
            <div class="inside_block_wrapper" style="height: 95%">
               <div class="infotab" style="height: 93.5%">
                   <center><h3>Достижения</h3></center>
                   <c:if test="${user.spec.toString().equals('RACER')}">
                       <c:if test="${getRacerProfileTable == null}">
                           <h4>Данных пока нет...</h4>
                       </c:if>
                       <c:if test="${getRacerProfileTable != null}">
                           <h4>Чемпионаты</h4>
                   <table class="infotable" border="1" style="font-size: 18pt; text-align: left">
                       <c:forEach items="${getRacerProfileTable}" var="result">
                           <tr>
                               <td><c:out value="${result[1]}"/> <c:out value="${result[0]}"/></td>
                               <td><c:out value="${result[2]}"/> место</td>
                           </tr>
                       </c:forEach>
                   </table>
                   </c:if>
                   </c:if>
                   <c:if test="${!(user.spec.toString().equals('RACER'))}">
                       <c:if test="${ach == null}">
                           <h4>Данных пока нет...</h4>
                       </c:if>
                       <c:if test="${ach != null}">
                           <h4>Кубок конструкторов</h4>
                           <table class="infotable" border="1" style="font-size: 18pt; text-align: left">
                               <c:forEach items="${ach}" var="res">
                                   <tr>
                                       <td>Сезон <c:out value="${res[0]}"/>: место <c:out value="${res[1]}"/></td>
                               </c:forEach>
                           </table>
                       </c:if>
                   </c:if>
               </div>
        </div>
    </div>
</div>
</div>
</body>
</html>