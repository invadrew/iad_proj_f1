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
                    <input type="button" class="res-selector" style="margin: 2%" value="Добавить" onclick="addUser()">
                    <br>
                    <label id="error" hidden> Не все поля заполнены </label>
                    <label id="busy" hidden> Логин уже существует </label>
                    <label id="reg-ready" hidden> Готово </label>
                </form>
            </div>
            <div class="infotab">
                <center><h3>Объявление гонки</h3></center>
                Текущий сезон: 2019
                <br><br>
                <form>
                    <label for="race-date">Дата Гонки</label>
                    <input type="date" class="res-selector" id="race-date">
                    <label for="race-time">Время Гонки</label>
                    <input type="time" class="res-selector" id="race-time">
                    <br>
                    <label for="champ">Чемпионат</label>
                    <input type="text" id="champ" class="res-selector">
                    <label for="track">Трасса</label>
                    <input type="text" id="track" class="res-selector">
                    <br>
                    <label for="race-num">Максимум участников</label>
                    <input type="number" min="2" id="race-num" class="res-selector" style="width: 100px">
                    <label for="team-num">Максимум команд</label>
                    <input type="number" min="2" id="team-num" class="res-selector" style="width: 100px">
                    <br>
                    <input type="submit" class="res-selector" value="Объявить">
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="../scripts/AdminPageScript.js"> </script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
</body>
</html>