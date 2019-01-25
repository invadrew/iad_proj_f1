<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/MainPageStyle.css">
    <script type="text/javascript" src="/scripts/MainPageScript.js"> </script>
    <title> Добро пожаловать! </title>
</head>
<body>
    <div class="grid-container">
        <div class="MainPageHeader" >
            <div class="HeaderContainer">
                <img src="/pictures/Formula_1_logo.jpg">
            </div>
        </div>
        <div class="LoginForm">
            <div class="inside_block_wrapper">
                <h2>Вход в систему</h2>
               <form id = "login" action="/security_check" method="post">
                   <label for="userName">Логин</label>
                   <input type="text" id="userName" name="uN">
                   <br> <br>
                   <label for="pass">Пароль</label>
                   <input type=password id="pass" name="uP">
                   <br> <br>
                   <input type="submit" value="Войти" id="getIn">
               </form>
            </div>
        </div>
        <div class="GreetingsBlock">
            <div class="inside_block_wrapper">
            <p><i> Добро пожаловать в самую современную систему для гонщиков и команд "Формулы-1",
            где вы найдёте всё, что Вам необходимо для успешной карьеры и
                бесконечного процветания во всемирно известных автомобильных гонках </i> </p>
            </div>
        </div>
        <div class="MainPageResultsList">
            <div class="inside_block_wrapper">
                <h2> Рейтинг команд</h2>
                <table class = "MainPageResTable">
                    <tr> 1111111111111111111 </tr>
                    <tr> 2222222222222222222 </tr>
                    <tr> 3333333333333333333 </tr>
                    <tr> 4444444444444444444 </tr>
                    <tr> 5555555555555555555 </tr>
                    <tr> 1111111111111111111 </tr>
                    <tr> 2222222222222222222 </tr>
                    <tr> 3333333333333333333 </tr>
                    <tr> 4444444444444444444 </tr>
                    <tr> 5555555555555555555 </tr>
                    <tr> 1111111111111111111 </tr>
                    <tr> 2222222222222222222 </tr>
                    <tr> 3333333333333333333 </tr>
                    <tr> 4444444444444444444 </tr>
                    <tr> 5555555555555555555 </tr>
                    <tr> 1111111111111111111 </tr>
                    <tr> 2222222222222222222 </tr>
                    <tr> 3333333333333333333 </tr>
                    <tr> 4444444444444444444 </tr>
                    <tr> 5555555555555555555 </tr>
                    <tr> 1111111111111111111 </tr>
                    <tr> 2222222222222222222 </tr>
                    <tr> 3333333333333333333 </tr>
                    <tr> 4444444444444444444 </tr>
                    <tr> 5555555555555555555 </tr>
                </table>
            </div>
        </div>
        <div class="CurrentChampTable">
            <div class="inside_block_wrapper">
                <h2> Текущее событие </h2>
                <table class = "MainPageResTable">
                    <tr> 1111111111111111111 </tr>
                    <tr> 2222222222222222222 </tr>
                    <tr> 3333333333333333333 </tr>
                    <tr> 4444444444444444444 </tr>
                    <tr> 5555555555555555555 </tr>
                </table>
            </div>
        </div>
        <div class="LatestNewsBlock">
            <div class="inside_block_wrapper">
                    <h1> Последние новости </h1>
                    <br/>
                    11111111111111111111111
                    11111111111111111111111
                    11111111111111111111111
                    11111111111111111111111
                    11111111111111111111111

            </div>
        </div>
    </div>
</body>
</html>