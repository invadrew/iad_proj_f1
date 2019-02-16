<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="Header">
    <script type="text/javascript" src="../scripts/HeaderScript.js"> </script>
    <div class="Header-LogoContainer">
        <img src="/pictures/Formula_1_logo.jpg">
    </div>
    <div class="Header-UserInfo-container">
        <sec:authorize access="!(hasAuthority('ADMIN'))">
            ${name}
        </sec:authorize>
        <sec:authorize access="hasAuthority('ADMIN')">
            Панель администратора
        </sec:authorize>
        <form action="/logout">
            <input type="submit" value="Выйти" id="out">
        </form>
    </div>
    <div class="UserPhoto-container">
        <sec:authorize access="!(hasAuthority('ADMIN'))">
            <img src="/pictures/rogoVK.jpg">
        </sec:authorize>
    </div>
</div>
<div class="HeaderMenu">
    <ul>
        <li><a href="/profile">Профиль</a></li>
        <li><a href="/team">Команда</a></li>
        <li><a href="MainPage.jsp">Гараж</a></li>
        <li><a href="/race-reg">Заезды</a></li>
        <li><a href="/ranks">Рейтинг</a></li>
        <li><a href="MainPage.jsp">Сообщения</a></li>
    </ul>
    <div class="searcher">
        <form>
            <input type="text" placeholder="Поиск по сайту" id="search">
            <input type="submit" value="Найти" id="find" onclick="window.location = ('/search?toSearch' + document.getElementById('search').value)">
        </form>
    </div>
</div>