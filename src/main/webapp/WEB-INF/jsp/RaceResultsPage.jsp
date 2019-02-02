<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/RanksPageStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Архив гонок</title>
</head>
<body>
<div class="grid-container">
   <jsp:include page="Header.jsp"/>
    <div class="RanksMainArea">
        <div class="inside_block_wrapper">
            <center><h2> Результаты прошедших гонок </h2></center>
            <br>
            <label for="season_selector">Выберите сезон:</label>
            <select id="season_selector">
                <option> hello there</option>
            </select>
            <br> <br>
            <label for="champ_selector">Выберите чемпионат:</label>
            <select id="champ_selector">
                <option> hello there</option>
            </select>
            <br>
            <div id="WorldCup" class="infotab" >
                <br>
                <table class="infotable" border="1">
                    <tr>
                        <th>Место</th>
                        <th>Гонщик</th>
                        <th>Команда</th>
                        <th>Очки</th>
                        <th>Время гонки</th>
                    </tr>
                    <tr>
                        <td>Место</td>
                        <td>Гонщик</td>
                        <td>Команда</td>
                        <td>Очки</td>
                        <td>Очки</td>
                    </tr>
                    <tr>
                        <td>Место</td>
                        <td>Гонщик</td>
                        <td>Команда</td>
                        <td>Очки</td>
                        <td>Очки</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>