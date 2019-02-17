<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/GarageStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Гараж</title>
</head>
<body>
<script type="text/javascript" src="../scripts/GarageScript.js"> </script>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="TeamNameArea">
        <br>
        <center><label style="padding-top: 3px" ><b>${team.name}</b></label></center>
    </div>
    <div class="MainArea">
        <div class="CarSelectArea">
            <div class="inside_block_wrapper">
                <div class="infotab"> <center>
                    <input type="button" class="res-selector" value="Болиды" onclick="chooseCars()"> <br>
                    <input type="button" class="res-selector" value="Склад" onclick="chooseStore()"> <br>
                    <sec:authorize access="hasAuthority('MECHANIC')">
                    <input type="button" class="res-selector" value="Заменить деталь" onclick="chooseChange()">
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('CONSTRUCTOR')">
                        <input type="button" class="res-selector" value="Добавить деталь" onclick="location.href='/add_detail'">
                    </sec:authorize>
                </center>
                </div>
                <div class="FilterArea" hidden id="filter">
                    <div class="inside_block_wrapper" style="background: #2f2727">
                        <div class="infotab">
                            <center><h3>Фильтр</h3></center>
                            <form>
                                <label for="compType">Тип</label>
                                <select class="res-selector" id="compType" style="width: 70% !important;">
                                    <option selected>Каркас</option>
                                    <option>Шасси</option>
                                    <option>Двигатель</option>
                                    <option>Электроника</option>
                                </select>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="WorkingArea">
            <div class="inside_block_wrapper">

                <div class="infotab">
                    <c:forEach items="${cars}" var="car"/>
                    <div class="cars-main-zone" id="carsZone">
                        <div class="PhotoArea">
                            <div class="inside_block_wrapper" style="background: #2f2727">
                                <div class="infotab">
                            <div style="width: 50%; height: 50%; position: relative; margin-left: 25%; margin-right: 25%">
                            <img src="/pictures/CarDemo.jpg" style="width: 100%; height: 100%;">
                            </div>
                                </div>
                            </div>
                        </div>
                        <div class="CarInfoArea">
                            Марка: АККАКД
                            <br>
                            Модель: уаыацуау
                            <br>
                            Год выпуска: 9428
                            <br>
                            Готовность: готов
                        </div>
                        <div class="DeatilsInfoArea">
                            <table class="infotable" border="1">
                                <tr>
                                    <th>Тип</th>
                                    <th>Описание комплектующего</th>
                                    <th>Состояние</th>
                                </tr>
                                <tr>
                                    <td>Тип</td>
                                    <td>Описание комплектующего</td>
                                    <td>Состояние</td>
                                </tr>
                                <tr>
                                    <td>Тип</td>
                                    <td>Описание комплектующего</td>
                                    <td>Состояние</td>
                                </tr>
                                <tr>
                                    <td>Тип</td>
                                    <td>Описание комплектующего</td>
                                    <td>Состояние</td>
                                </tr>
                                <tr>
                                    <td>Тип</td>
                                    <td>Описание комплектующего</td>
                                    <td>Состояние</td>
                                </tr>
                            </table>
                        </div>
                        <sec:authorize access="hasAuthority('MECHANIC')">
                        <div class="RepairArea">
                            <center><h3>Ремонт</h3></center>
                            <table class="infotable">
                                <tr>
                                    <td>Корпус</td>
                                    <td>Описание проблемы апавпавпва</td>
                                    <td>Цена: 4223423</td>
                                    <td><input type="button" value="Починить" class="res-selector"></td>
                                </tr>
                                <tr>
                                    <td>Корпус</td>
                                    <td>Описание проблемы апавпавпва</td>
                                    <td>Цена 4223423</td>
                                    <td><input type="button" value="Починить" class="res-selector"></td>
                                </tr>
                                <tr>
                                    <td>Корпус</td>
                                    <td>Описание проблемы апавпавпва</td>
                                    <td>Цена 4223423</td>
                                    <td><input type="button" value="Починить" class="res-selector"></td>
                                </tr>
                            </table>
                        </div>
                        </sec:authorize>
                    </div>

                    <div class="storage-area" >

                        <div class="StoreTableArea" id="storage" style="width: 100%; margin: 2%" hidden >
                            <h1 style="text-align: center">Склад</h1>
                            <h2>Каркасы</h2>
                            <table class="infotable" border="1"  id="carcTable" style="font-size: 16pt">
                                <tr>
                                    <th>Материал</th>
                                    <th>Спойлер</th>
                                    <th>Дуги безопасности</th>
                                    <th>Крылья</th>
                                    <th>Состояние</th>
                                </tr>
                                <tr>
                                    <td>Материал</td>
                                    <td>Спойлер</td>
                                    <td>Дуги безопасности</td>
                                    <td>Крылья</td>
                                    <td>Состояние</td>
                                </tr>  <tr>
                                <td>Материал</td>
                                <td>Спойлер</td>
                                <td>Дуги безопасности</td>
                                <td>Крылья</td>
                                <td>Состояние</td>
                            </tr>
                            </table>

                            <h2>Шасси</h2>
                            <table class="infotable" border="1" style="font-size: 16pt" id="chassTable">
                                <tr>
                                    <th>Модель</th>
                                    <th>Высота</th>
                                    <th>Ширина</th>
                                    <th>Состояние</th>
                                </tr>
                                <tr>
                                    <td>Модель</td>
                                    <td>Высота</td>
                                    <td>Ширина</td>
                                    <td>Состояние</td>
                                </tr>
                                <tr>
                                    <td>Модель</td>
                                    <td>Высота</td>
                                    <td>Ширина</td>
                                    <td>Состояние</td>
                                </tr>
                            </table>

                            <h2>Двигатели</h2>
                            <table class="infotable" border="1" style="font-size: 16pt"  id="engTable">
                                <tr>
                                    <th>Модель</th>
                                    <th>Кол-во Цилиндров</th>
                                    <th>Объем</th>
                                    <th>Масса</th>
                                    <th>Ход поршня</th>
                                    <th>Состояние</th>
                                </tr>
                                <tr>
                                    <td>Модель</td>
                                    <td>Кол-во Цилиндров</td>
                                    <td>Объем</td>
                                    <td>Масса</td>
                                    <td>Ход поршня</td>
                                    <td>Состояние</td>
                                </tr>
                                <tr>
                                    <td>Модель</td>
                                    <td>Кол-во Цилиндров</td>
                                    <td>Объем</td>
                                    <td>Масса</td>
                                    <td>Ход поршня</td>
                                    <td>Состояние</td>
                                </tr>
                            </table>

                            <h2>Электроника</h2>
                            <table class="infotable" border="1" style="font-size: 16pt" id="elecTable">
                                <tr>
                                    <th>Телеметрия</th>
                                    <th>Система контроля</th>
                                    <th>Состояние</th>
                                </tr>
                                <tr>
                                    <td>Телеметрия</td>
                                    <td>Система контроля</td>
                                    <td>Состояние</td>
                                </tr>
                                <tr>
                                    <td>Телеметрия</td>
                                    <td>Система контроля</td>
                                    <td>Состояние</td>
                                </tr>
                                <tr>
                                    <td>Телеметрия</td>
                                    <td>Система контроля</td>
                                    <td>Состояние</td>
                                </tr>
                            </table>

                        </div>
                    </div>

                    <div class="DetailChange" id="changes" hidden>
                            <center><h3>Замена оборудования</h3></center>
                            <sec:authorize access="hasAuthority('MECHANIC')">
                            <form>
                                <label for="car-select">Выберите болид</label>
                                <select id="car-select" class="res-selector" style="width: 30% !important;">
                                    <option>Hello there!</option>
                                </select> <br>
                                <label for="change-type-select">Тип детали</label>
                                <select id="change-type-select" class="res-selector" style="width: 30% !important;">
                                    <option>Hello there!</option>
                                </select> <br>
                                <label for="change-detail">Заменить на</label>
                                <select id="change-detail" class="res-selector" style="width: 30% !important;">
                                    <option>Hello there!</option>
                                </select> <br>
                                <input type="submit" class="res-selector" value="Подтвердить" style="width: 30% !important;">
                            </form>
                            </sec:authorize>
                    </div>

                </div>
            </div>



        </div>
    </div>
</div>