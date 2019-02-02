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
   <jsp:include page="Header.jsp"/>
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