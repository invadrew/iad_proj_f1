<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/RanksPageStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Рейтинг</title>
</head>
<body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/RanksPageScript.js"> </script>
<div class="grid-container">
    <!-- TODO: try to make ui component for header zone-->
    <div class="Header">
      <div class="Header-LogoContainer">
        <img src="/pictures/Formula_1_logo.jpg">
      </div>
        <div class="Header-UserInfo-container">
            Никита Рогаленко
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
    <!-- end to do-->
    <div class="RanksMainArea">
      <div class="inside_block_wrapper">
          <center><h2> Таблица результатов </h2></center>
          <br>
          <label for="season_selector">Выберите сезон:</label>
          <select id="season_selector">
              <c:forEach items="${seasonsList}" var="season">
                <option value="${season.year}"> <c:out value="${season.year}"/> </option>
              </c:forEach>
          </select>
          <br> <br>
          Выберите таблицу:
              <button class="res-selector" onclick="openRes('WorldCup')">Кубок мира</button>
              <button class="res-selector" onclick="openRes('ConstrCup')">Кубок конструкторов</button>
          <br>
          <div id="WorldCup" class="infotab" style="display:none">
              <h4 id="worldHeader">Кубок мира</h4>
             <table class="infotable" id="worldTable" border="1">
                 <tr>
                     <th>Место</th>
                     <th>Гонщик</th>
                     <th>Команда</th>
                     <th>Очки</th>
                 </tr>
             </table>
          </div>
          <div id="ConstrCup" class="infotab" style="display:none">
              <h4 id="constrHeader">Кубок конструкторов</h4>
              <table class="infotable" id="constrTable" border="1">
                  <tr>
                      <th>Место</th>
                      <th>Команда</th>
                      <th>Очки</th>
                  </tr>
              </table>
          </div>

      </div>
    </div>
</div>
</body>
</html>