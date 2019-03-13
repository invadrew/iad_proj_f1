<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/RaceTimeRacerStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Гонка</title>
</head>
<body>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="TeamNameArea">
        <br>
        <center><label style="padding-top: 3px" ><b>${team.name}</b></label></center>
    </div>
    <c:if test="${!isRegistrated}">
        <label style="color: white; text-align: center">
            <br> <br> <br>
            Вас не было в заявке на участие в этой гонке

        </label>
    </c:if>
    <c:if test="${!ifStarted && isRegistrated}">
        <label style="color: white; text-align: center">
            <br> <br> <br>
            Заезд ещё не начался. Начало заезда: <fmt:formatDate value="${raceDateTime}" pattern="dd-MM-yyyy HH:mm" />

        </label>
    </c:if>
    <c:if test="${ifStarted && isRegistrated}">
    <div class="MainArea">
    <div class="LeftPart">
        <div class="RaceTimeArea">
            <div class="inside_block_wrapper" id="timeWrapper">
                <div class="infotab" id="timeBlock">
                    Время гонки: <label id="time"></label>
                </div>
            </div>
        </div>

        <div class="CarConditionArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <label style="text-decoration: underline">Болид: ${car.label} ${car.model}</label>
                    <h3 style="text-align: center">Состояние болида</h3>
                    <label>Топливо: 32 литра</label> <br>
                    <label>Шины: Нормально</label> <br>
                    <label>Каркас:
                        <c:if test="${carcase.condition.toString().equals('PERFECT')}">Идеально</c:if>
                        <c:if test="${carcase.condition.toString().equals('GOOD')}">Хорошо</c:if>
                        <c:if test="${carcase.condition.toString().equals('NORMAL')}">Нормально</c:if>
                        <c:if test="${carcase.condition.toString().equals('BAD')}">Плохо</c:if>
                        <c:if test="${carcase.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                    </label> <br>
                    <label>Шасси:
                        <c:if test="${chassis.condition.toString().equals('PERFECT')}">Идеально</c:if>
                        <c:if test="${chassis.condition.toString().equals('GOOD')}">Хорошо</c:if>
                        <c:if test="${chassis.condition.toString().equals('NORMAL')}">Нормально</c:if>
                        <c:if test="${chassis.condition.toString().equals('BAD')}">Плохо</c:if>
                        <c:if test="${chassis.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                    </label> <br>
                    <label>Двигатель:
                        <c:if test="${engine.condition.toString().equals('PERFECT')}">Идеально</c:if>
                        <c:if test="${engine.condition.toString().equals('GOOD')}">Хорошо</c:if>
                        <c:if test="${engine.condition.toString().equals('NORMAL')}">Нормально</c:if>
                        <c:if test="${engine.condition.toString().equals('BAD')}">Плохо</c:if>
                        <c:if test="${engine.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                    </label> <br>
                    <label>Электроника:
                        <c:if test="${electronics.condition.toString().equals('PERFECT')}">Идеально</c:if>
                        <c:if test="${electronics.condition.toString().equals('GOOD')}">Хорошо</c:if>
                        <c:if test="${electronics.condition.toString().equals('NORMAL')}">Нормально</c:if>
                        <c:if test="${electronics.condition.toString().equals('BAD')}">Плохо</c:if>
                        <c:if test="${electronics.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                    </label>
                </div>
            </div>
        </div>

        <div class="ServiceRequestArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <h3 style="text-align: center">Запрос обслуживания</h3>
                    <form>
                        <label>Пункт пит-стопа:</label> <br>
                        <c:forEach items="${pitStopPlaces}" var="place">
                        <label><input type="radio" name="place-select" value="${place.id}"> ${place.name} </label>
                        </c:forEach>
                         <br>
                        <label for="tire-change">Сменить резину:</label>
                        <select class="res-selector" id="tire-change">
                            <option>Не надо</option>
                            <option>Мягкие</option>
                            <option>Жесткие</option>
                        </select> <br>
                        Дозаправка <input type="number" min="0" class="res-selector" style="width: 20%"> литров
                        <br> <br>
                        Через <input type="number" min="0" class="res-selector" style="width: 5%"> кругов
                        <input type="submit" class="res-selector" value="Запросить">
                    </form>
                </div>
            </div>
        </div>

        <div class="RepairRequestArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <h3 style="text-align: center">Запрос ремонта</h3>
                    <form>
                        <label>Пункт пит-стопа:</label> <br>
                        <c:forEach items="${pitStopPlaces}" var="place">
                            <label><input type="radio" name="place-select" value="${place.id}"> ${place.name} </label>
                        </c:forEach>
                         <br>
                        <table style="margin: 2%">
                            <tr>
                                <th>Деталь</th>
                                <th>Причина</th>
                            </tr>
                            <tr>
                                <td>
                                    <label>
                                        <input type="checkbox"> Каркас
                                    </label>
                                </td>
                                <td>
                                    <input id="carc-reason" class="res-selector" type="text">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>
                                        <input type="checkbox"> Шасси
                                    </label>
                                </td>
                                <td>
                                    <input id="chass-reason" class="res-selector" type="text">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>
                                        <input type="checkbox"> Двигатель
                                    </label>
                                </td>
                                <td>
                                    <input id="eng-reason" class="res-selector" type="text">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>
                                        <input type="checkbox"> Электроника
                                    </label>
                                </td>
                                <td>
                                    <input id="elec-reason" class="res-selector" type="text">
                                </td>
                            </tr>
                        </table>
                        <input type="submit" value="Запросить" class="res-selector">
                    </form>
                </div>
            </div>
        </div>
    </div>
        <div class="RightPart">
        <div class="RaceMessageTableArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <table class="infotable" border="1" style="max-height: 30%">
                        <tr>
                            <th>Время</th>
                            <th>Сообщение</th>
                        </tr>
                        <tr>
                            <td>00:01:34</td>
                            <td>Тут будет очень длинное сообщение с информацией о пит-сто пе 0_0 0_0</td>
                        </tr>
                        <tr>
                            <td>00:01:34</td>
                            <td>Тут будет очень длинное сообщение с информацией о пит-сто пе 0_0 0_0</td>
                        </tr>
                        <tr>
                            <td>00:01:34</td>
                            <td>Тут будет очень длинное сообщение с информацией о пит-сто пе 0_0 0_0</td>
                        </tr>
                        <tr>
                            <td>00:01:34</td>
                            <td>Тут будет очень длинное сообщение с информацией о пит-сто пе 0_0 0_0</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div class="PitStopOfferArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <h3 style="text-align: center">Предложение пит-стопа</h3>
                    Пит-стоп на пункте А через 1 круг <br>
                    Тип: обслуживание <br>
                    Причина: дозаправка <br> <br>
                    <input type="button" class="res-selector" value="Принять">
                    <input type="button" class="res-selector" value="Отказать">
                </div>
            </div>
        </div>

        <div class="PiltotChangeRequestArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <center><h3>Запрос на смену пилота</h3></center>
                    <form>
                        <label>Пункт пит-стопа:</label> <br>
                        <c:forEach items="${pitStopPlaces}" var="place">
                            <label><input type="radio" name="place-select" value="${place.id}"> ${place.name} </label>
                        </c:forEach>
                         <br>
                        <label for="pilot-reason">Причина</label>
                        <input id="pilot-reason" class="res-selector" type="text">
                         <br>
                        <input type="submit" class="res-selector" value="Отправить запрос">
                    </form>
                </div>
            </div>
        </div>
        </div>
    </div>
    </c:if>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/RacersScript.js"></script>
<script type="text/javascript" src="../scripts/RaceTimeScript.js"></script>
</body>
</html>