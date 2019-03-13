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
    <c:if test="${!ifStarted}">
        <label style="color: white; text-align: center">
            <br> <br> <br>
            Заезд ещё не начался. Начало заезда: <fmt:formatDate value="${raceDateTime}" pattern="dd-MM-yyyy HH:mm" />

        </label>
    </c:if>
    <c:if test="${ifStarted}">
    <div class="MainArea">
    <div class="LeftPart">
        <div class="RaceTimeArea">
            <div class="inside_block_wrapper" id="timeWrapper">
                <div class="infotab" id="timeBlock">
                    <label>Тут в общем должно быть время гонки</label>
                </div>
            </div>
        </div>

        <div class="CarConditionArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <label style="text-decoration: underline">Болид: Ferrari 488 pista spider</label>
                    <h3 style="text-align: center">Состояние болида</h3>
                    <label>Топливо: 32 литра</label> <br>
                    <label>Шины: Нормально</label> <br>
                    <label>Каркас: Нормально</label> <br>
                    <label>Шасси: Нормально</label> <br>
                    <label>Двигатель: Нормально</label> <br>
                    <label>Электроника: Нормально</label>
                </div>
            </div>
        </div>

        <div class="ServiceRequestArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <h3 style="text-align: center">Запрос обслуживания</h3>
                    <form>
                        <label>Пункт пит-стопа:</label>
                        <label><input type="radio" name="place-select"> А </label>
                        <label><input type="radio" name="place-select"> B </label>
                        <label><input type="radio" name="place-select"> C </label> <br>
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
                        <label>Пункт пит-стопа:</label>
                        <label><input type="radio" name="place-select"> А </label>
                        <label><input type="radio" name="place-select"> B </label>
                        <label><input type="radio" name="place-select"> C </label> <br>
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
                                    <select class="res-selector">
                                        <option> придумаем</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>
                                        <input type="checkbox"> Шасси
                                    </label>
                                </td>
                                <td>
                                    <select class="res-selector">
                                        <option> придумаем</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>
                                        <input type="checkbox"> Двигатель
                                    </label>
                                </td>
                                <td>
                                    <select class="res-selector">
                                        <option> придумаем</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>
                                        <input type="checkbox"> Электроника
                                    </label>
                                </td>
                                <td>
                                    <select class="res-selector">
                                        <option> придумаем</option>
                                    </select>
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
                        <label>Пункт пит-стопа:</label>
                        <label><input type="radio" name="place-select"> А </label>
                        <label><input type="radio" name="place-select"> B </label>
                        <label><input type="radio" name="place-select"> C </label> <br>
                        <label for="pilot-reason">Причина</label>
                        <select class="res-selector" id="pilot-reason">
                            <option>hello there</option>
                        </select> <br>
                        <input type="submit" class="res-selector" value="Отправить запрос">
                    </form>
                </div>
            </div>
        </div>
        </div>
    </div>
    </c:if>
</div>