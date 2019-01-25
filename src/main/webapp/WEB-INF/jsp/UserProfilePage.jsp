<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            Никита Рогаленко
            <form action="/logout">
                <input type="submit" value="Выйти" id="out"> </input>
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
            <li><a href="RanksPage.html">Рейтинг</a></li>
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
                <center><h2>Никита Рогаленко</h2></center>
                Команда: RogoNemRacing
                <br> <br>
                Должность: Гонщик
            </div>
          </div>
        </div>
        <div class="StatisticsArea">
            <div class="inside_block_wrapper">
                <div class="infotab" >
                    <center>
                        <h3>Статистика</h3> </center>
                    <table class="infotable" style="font-size: 18pt; text-align: left">
                        <tr><td>Текущее место - 1</td></tr>
                        <tr><td>Количество очков в сзоне - 30</td></tr>
                        <tr><td>Количество заездов в сезоне - 3</td></tr>
                        <tr><td>Лучшее место в текущем сезоне - 1</td></tr>
                        <tr><td>Среднее место на финише за сезон - 2</td></tr>
                        <tr><td>Общее количество заездов - 5</td></tr>
                        <tr><td>Средний результат на всех заездах - 2.6</td></tr>
                        <tr><td>Лучшее время гонки - 00:34:23 - Dragon Quest</td></tr>
                        <tr><td>Выиграно кубков мира - 2</td></tr>
                        <tr><td>Участвовал в 4 чемпионатах</td></tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="UserAchArea">
            <div class="inside_block_wrapper" style="height: 95%">
               <div class="infotab" style="height: 93.5%">
                   <center><h3>Достижения</h3></center>
                   <table class="infotable" border="1" style="font-size: 18pt; text-align: left">
                       <tr>
                           <td>Чемпионат ла-ла-ла-ла</td>
                           <td>23 место</td>
                       </tr>
                       <tr>
                           <td>Чемпионат ла-ла-ла-ла</td>
                           <td>23 место</td>
                       </tr>
                       <tr>
                           <td>Чемпионат ла-ла-ла-ла</td>
                           <td>23 место</td>
                       </tr>
                       <tr>
                           <td>Чемпионат ла-ла-ла-ла</td>
                           <td>23 место</td>
                       </tr>
                       <tr>
                           <td>Чемпионат ла-ла-ла-ла</td>
                           <td>23 место</td>
                       </tr>
                   </table>
               </div>
        </div>
    </div>
</div>
</div>
</body>
</html>