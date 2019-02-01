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
<script type="text/javascript" src="../scripts/AdminPageScript.js"> </script>
<div class="grid-container">
    <!-- TODO: try to make ui component for header zone-->
    <div class="Header">
        <div class="Header-LogoContainer">
            <img src="/pictures/Formula_1_logo.jpg">
        </div>
        <div class="Header-UserInfo-container">
            Панель администратора
            <form action="/logout">
                <input type="submit" value="Выйти" id="out">
            </form>
        </div>
        <div class="UserPhoto-container">
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
                        <option value="Racer">Гонщик</option>
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
                    <input type="submit" class="res-selector" style="margin: 2%" value="Добавить">
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
</body>
</html>