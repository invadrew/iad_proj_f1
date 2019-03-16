<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="Header">
    <script type="text/javascript" src="../scripts/HeaderScript.js"> </script>
    <div class="Header-LogoContainer">
        <img src="/pictures/Formula_1_logo.jpg">
    </div>
    <div class="Header-UserInfo-container">
        <sec:authorize access="!hasAuthority('ADMIN')">
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
            <img src="${myPhoto}">
        </sec:authorize>
    </div>
</div>
<div class="HeaderMenu">
    <ul>
        <sec:authorize access="hasAnyAuthority('RACER','MANAGER','CONSTRUCTOR','MECHANIC')"> <li><a href="/profile">Профиль</a></li> </sec:authorize>
        <sec:authorize access="hasAuthority('SPONSOR')"> <li><a href="/sponsor">Профиль</a></li>  </sec:authorize>
        <sec:authorize access="hasAuthority('ADMIN')"> <li><a href="/admin">Профиль</a></li>  </sec:authorize>
        <sec:authorize access="hasAnyAuthority('RACER','MANAGER','CONSTRUCTOR','MECHANIC')"> <li><a href="/team">Команда</a></li> </sec:authorize>
        <sec:authorize access="hasAnyAuthority('RACER','MANAGER','CONSTRUCTOR','MECHANIC')"> <li><a href="/garage">Гараж</a></li> </sec:authorize>
        <li><a href="/race-reg">Заезды</a></li>
        <li><a href="/ranks">Рейтинг</a></li>
    </ul>
    <div class="searcher">
        <form  action="/search">
            <input type="text" name="toSearch" placeholder="Поиск по сайту" id="search">
            <input type="submit" value="Найти" id="find" onclick="sendSearch()" />
        </form>
    </div>
</div>