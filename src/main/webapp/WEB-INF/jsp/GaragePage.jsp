<%@ page import="java.util.List" %>
<%@ page import="com.rogo.inv.iadprojf1.entity.ComponentCondition" %>
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
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
                          <!--   <form> -->
                                <label for="compType">Тип</label>
                                <select class="res-selector" id="compType" style="width: 70% !important;" onchange="chooseFilter()">
                                    <option selected value="any">Любой</option>
                                    <option value="carcase">Каркас</option>
                                    <option value="chassis">Шасси</option>
                                    <option value="engine">Двигатель</option>
                                    <option value="electronics">Электроника</option>
                                </select>

                              <!--  <input type="submit" value="Показать" class="res-selector" style="margin-top: 7%">
                            </form> -->
                        </div>

                            <div class="infotab" id="carcase_filter" hidden>

                            </div>

                            <div class="infotab" id="chassis_filter" hidden>
                                dfgfdgd
                            </div>

                            <div class="infotab" id="engine_filter" hidden>
                                dfgdf
                            </div>

                            <div class="infotab" id="electronics_filter" hidden>
                                dfgdfgfd
                            </div>

                    </div>
                </div>

            </div>
        </div>
        <div class="WorkingArea">
            <div class="inside_block_wrapper">

                <div class="infotab">
                    <div class="cars-main-zone" id="carsZone">
                        <c:forEach items="${cars}" var="car">
                            <div class="infotab">
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
                            Марка: ${car.label}
                            <br>
                            Модель: ${car.model}
                            <br>
                            Год выпуска: ${car.creationYear}
                            <br>
                            <c:if test="${(car.isReady)}">
                                <c:out value="Готов к гонке"/>
                            </c:if>
                            <c:if test="${!(car.isReady)}">
                                <c:out value="Не готов к гонке"/>
                            </c:if>
                        </div>
                        <div class="DeatilsInfoArea">
                            <table class="infotable" border="1" style="text-align: left">
                                <tr>
                                    <th>Тип</th>
                                    <th>Описание комплектующего</th>
                                    <th>Состояние</th>
                                </tr>
                                <tr>
                                    <td>Каркас</td>
                                    <td> Спойлер: ${carcases.get(cars.indexOf(car)).rearWing}.
                                        Дуги безопасности: ${carcases.get(cars.indexOf(car)).safetyArcs} .
                                        Крылья: ${carcases.get(cars.indexOf(car)).wings} </td>
                                    <td>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Двигатель</td>
                                    <td> Мод. ${engines.get(cars.indexOf(car)).model}, ход поршня: ${engines.get(cars.indexOf(car)).stroke} мм,
                                            ${engines.get(cars.indexOf(car)).cyclinders} цил., ${engines.get(cars.indexOf(car)).capacity} литров,
                                            ${engines.get(cars.indexOf(car)).mass} кг
                                    </td>
                                    <td>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Шасси</td>
                                    <td> Мод. ${chassis.get(cars.indexOf(car)).model}, ширина: ${chassis.get(cars.indexOf(car)).width},
                                        высота: ${chassis.get(cars.indexOf(car)).height} </td>
                                    <td>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Электроника </td>
                                    <td> Телеметрия: ${electronics.get(cars.indexOf(car)).telemetry}.
                                        Сист. контроля: ${electronics.get(cars.indexOf(car)).controlSystem}</td>
                                    <td>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
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
                        </c:forEach>
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
                                <c:forEach items="${carcaseStorage}" var="carc">
                                <tr>
                                    <td>${carc.material}</td>
                                    <td>${carc.rearWing}</td>
                                    <td>${carc.safetyArcs}</td>
                                    <td>${carc.wings}</td>
                                    <td>
                                        <c:if test="${carc.condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${carc.condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${carc.condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${carc.condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${carc.condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
                                </tr>
                                </c:forEach>
                            </table>

                            <h2>Шасси</h2>
                            <table class="infotable" border="1" style="font-size: 16pt" id="chassTable">
                                <tr>
                                    <th>Модель</th>
                                    <th>Высота</th>
                                    <th>Ширина</th>
                                    <th>Состояние</th>
                                </tr>
                               <c:forEach items="${chassisStorage}" var="chass">
                                <tr>
                                    <td>${chass.model}</td>
                                    <td>${chass.height}</td>
                                    <td>${chass.width}</td>
                                    <td>
                                        <c:if test="${chass.condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${chass.condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${chass.condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${chass.condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${chass.condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
                                </tr>
                               </c:forEach>
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
                                <c:forEach items="${enginesStorage}" var="eng">
                                <tr>
                                    <td>${eng.model}</td>
                                    <td>${eng.cyclinders}</td>
                                    <td>${eng.capacity}</td>
                                    <td>${eng.mass}</td>
                                    <td>${eng.stroke}</td>
                                    <td>
                                        <c:if test="${eng.condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${eng.condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${eng.condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${eng.condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${eng.condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
                                </tr>
                                </c:forEach>
                            </table>

                            <h2>Электроника</h2>
                            <table class="infotable" border="1" style="font-size: 16pt" id="elecTable">
                                <tr>
                                    <th>Телеметрия</th>
                                    <th>Система контроля</th>
                                    <th>Состояние</th>
                                </tr>
                                <c:forEach items="${electronicsStorage}" var="electr">
                                <tr>
                                    <td>${electr.telemetry}</td>
                                    <td>${electr.controlSystem}</td>
                                    <td>
                                        <c:if test="${electr.condition.equals(ComponentCondition.PERFECT)}">Идеальное</c:if>
                                        <c:if test="${electr.condition.equals(ComponentCondition.GOOD)}">Хорошее</c:if>
                                        <c:if test="${electr.condition.equals(ComponentCondition.NORMAL)}">Нормальное</c:if>
                                        <c:if test="${electr.condition.equals(ComponentCondition.BAD)}">Плохое</c:if>
                                        <c:if test="${electr.condition.equals(ComponentCondition.AWFUL)}">Ужасное</c:if>
                                    </td>
                                </tr>
                                </c:forEach>
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