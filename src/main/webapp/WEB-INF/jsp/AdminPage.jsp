<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/AdminStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Панель администратора</title>
</head>
<body>
<div class="grid-container">
  <jsp:include page="Header.jsp"/>
    <div class="MainArea">
        <div class="inside_block_wrapper">
            <div class="infotab">
                <center><h3>Добавить пользователя</h3></center>
                <form>
                    <label for="new-login">Логин</label>
                    <input type="text" id="new-login" class="res-selector">
                    <label for="new-pass">Пароль</label>
                    <input type="text" id="new-pass" class="res-selector">
                    <br>
                    <label for="role-selector">Тип пользователя</label>
                    <select id="role-selector" class="res-selector" style="margin-top: 1%; margin-bottom: 1%" onchange="fieldsHandler()">
                        <option value="Racer" selected>Гонщик</option>
                        <option value="Mechanic">Механик</option>
                        <option value="Manager">Менеджер</option>
                        <option value="Constructor">Конструктор</option>
                        <option value="Sponsor">Спонсор</option>
                        <option value="Admin">Администратор</option>
                    </select>
                    <br>
                    <label for="new-name" id="new-name-id">Имя</label>
                    <input type="text" id="new-name" class="res-selector">
                    <label for="new-sur" id="new-sur-id">Фамилия</label>
                    <input type="text" id="new-sur" class="res-selector">
                    <label for="new-nazv" id="new-nazv-id" hidden>Название</label>
                    <input type="text" id="new-nazv" class="res-selector" hidden>
                    <br>
                    <label for="new-budg" id="new-budg-id" hidden>Бюджет</label>
                    <input type="number" value="0" min="0" id="new-budg" class="res-selector" hidden>
                    <br>
                    <label id="photo-label"> Фото: </label>
                    <input type="file" id="photo" accept="image/*" class="res-selector" onchange="newFile(event)">
                    <br>
                    <input type="button" class="res-selector" style="margin: 2%" value="Добавить" onclick="addUser()">
                    <br>
                    <label id="error" hidden> Не все поля заполнены </label>
                    <label id="busy" hidden> Логин уже существует </label>
                    <label id="reg-ready" hidden> Готово </label>
                </form>
            </div>
            <div class="infotab">
                <center><h3>Объявление гонки</h3></center>
                <c:if test="${ifFinished}">
                Текущий сезон: ${currSeason.year}
                <br><br>
                <form>
                    <label for="race-date">Дата и время гонки</label>
                    <input type="datetime-local" class="res-selector" id="race-date">
                    <br>
                    <label for="champ">Чемпионат</label>
                    <input type="text" id="champ" class="res-selector">
                    <label for="track">Трасса</label>
                    <input type="text" id="track" class="res-selector">
                    <br>
                    <label for="race-num"> Кругов </label>
                    <input type="number" min="1" id="race-num" class="res-selector" value="1" style="width: 100px">
                    <label for="team-num">Максимум команд</label>
                    <input type="number" min="2" id="team-num" class="res-selector" value="2" style="width: 100px">
                    <br>
                    <label for="country">Страна</label>
                    <input type="text" id="country" class="res-selector">
                    <br>
                    <input type="button" class="res-selector" value="Объявить" onclick="addRace()">
                    <br>
                    <label id="race-ready" hidden> Готово </label>
                    <label id="race-error" hidden> Не все поля заполнены </label>
                    <label id="too-early" hidden> Выбранная дата неактуальна </label>
                </form>
                </c:if>
                <c:if test="${!ifFinished}">
                    Гонка уже объявлена, идет регистрация
                    <br> <br>
                </c:if>
            </div>
            <div class="infotab">
                <center><h3>Запросы на создание команд</h3></center>
                <table class="infotable" style="text-align: center; margin: 3%;">
                <c:forEach items="${teamsOnReview}" var="team">
                    <tr id="tRow${team[0]}">
                        <td>${team[1]}</td>
                        <td><input placeholder="Комментарий" type="text" id="tComment${team[0]}" class="res-selector"></td>
                        <td><input type="button" value="Принять" id="tAccept${team[0]}" class="res-selector"
                                   onclick="handleTeamRequest(${team[0]},true,${team[5]})"/></td>
                        <td><input type="button" value="Отказать" id="tRefuse${team[0]}" class="res-selector"
                                   onclick="handleTeamRequest(${team[0]},false, ${team[5]})"/></td>
                    </tr>
                </c:forEach>
                </table>
            </div>
            <div class="infotab">
                <center><h3>Запросы на регистрацию в гонке</h3></center>
                    <table class="infotable" style="text-align: center; margin: 3%;" border="1">
                        <c:if test="${!regsOnReview.isEmpty()}">
                        <tr>
                            <th>Сезон</th>
                            <th>Чемпионат</th>
                            <th>Дата гонки</th>
                            <th>Команда</th>
                            <th>Первый пилот</th>
                            <th>Второй пилот</th>
                            <th>Комментарий</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </c:if>
                        <c:forEach items="${regsOnReview}" var="reg">
                            <tr id="regRow${reg[0]}${reg[1]}">
                                <td>${reg[4]}</td>
                                <td>${reg[3]}</td>
                                <td><fmt:formatDate value="${reg[2]}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                                <td>
                                    <c:url value="/team" var="uUrl">
                                        <c:param name="id" value="${reg[1]}"/>
                                    </c:url>
                                    <a class="redirHref" href="${uUrl}">
                                        ${reg[5]}
                                    </a>
                                </td>
                                <td>
                                    <c:url value="/profile" var="uUrl">
                                        <c:param name="id" value="${reg[8]}"/>
                                    </c:url>
                                    <a class="redirHref" href="${uUrl}">
                                        ${reg[6]}
                                    </a>
                                </td>
                                <td>
                                    <c:url value="/profile" var="uUrl">
                                        <c:param name="id" value="${reg[9]}"/>
                                    </c:url>
                                    <a class="redirHref" href="${uUrl}">
                                        ${reg[7]}
                                    </a>
                                </td>
                                <td><input type="text" placeholder="Комментарий" id="rComment${reg[0]}${reg[1]}" class="res-selector"/></td>
                                <td><input type="button" class="res-selector" value="Подтвердить" onclick="handleRaceRegRequest(${reg[0]},${reg[1]},true)"></td>
                                <td><input type="button" class="res-selector" value="Отказать" onclick="handleRaceRegRequest(${reg[0]},${reg[1]},false)"></td>
                            </tr>
                        </c:forEach>
                    </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="../scripts/AdminPageScript.js"> </script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
</body>
</html>