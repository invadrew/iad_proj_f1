<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/RaceTimeMechanicStyle.css">
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
                       Время гонки: <label id="time"></label>
                    </div>
                </div>
            </div>

            <c:forEach items="${pitStopPlaces}" var="place">
            <div class="pit-stop-place-condition">
                <div class="inside_block_wrapper">
                    <div class="infotab">
                        <label style="text-decoration: underline">Пит-стоп: ${place.name}</label> <br> <br>
                        <label>Шины: Жесткие х${place.tough}, Мягкие х${place.soft} </label> <br>
                        <label>Топливо: ${place.fuel} литров</label> <br> <br>
                    </div>
                </div>
            </div>
            </c:forEach>

            <div class="pit-stop-transfer-area">
                <div class="inside_block_wrapper">
                    <div class="infotab">
                        <h3 style="text-align: center">Перемещение ресурсов</h3>
                        <form>
                            <label for="place-from-transfer">Из</label>
                            <select class="res-selector" id="place-from-transfer">
                                <c:forEach items="${pitStopPlaces}" var="place">
                                    <option value="${place.id}">${place.name}</option>
                                </c:forEach>
                            </select>
                            <label for="place-to-transfer">В</label>
                            <select class="res-selector" id="place-to-transfer">
                                <c:forEach items="${pitStopPlaces}" var="place">
                                    <option value="${place.id}">${place.name}</option>
                                </c:forEach>
                            </select> <br>
                            <label for="item-transfer">Что перемещать:</label>
                            <select class="res-selector" id="item-transfer">
                                <option value="tough">Жесткие шины</option>
                                <option value="soft">Мягкие шины</option>
                                <option value="fuel">Топливо</option>
                            </select> <br>
                            <label for="transfer-num">Количество:</label>
                            <input type="number" min="1" value="1" id="transfer-num" class="res-selector"> <br>
                            <label> Время перемещения: 10 секунд </label> <br>
                           <label hidden id="transfer-same"> Выберите разные пункты </label> <br>
                            <label hidden id="transfer-not-enough">На пункте нет столько</label> <br>
                            <label hidden id="transfer-empty">Введите количество</label>
                            <label hidden id="transfer-wait">Ожидаем</label> <br>
                            <label hidden id="transfer-ready">Готово</label>
                            <br> <br>
                            <input type="button" class="res-selector" value="Переместить" onclick="doTransfer()">
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

            <div class="cars-info-area">

                <div class="car-info">
                    <div class="inside_block_wrapper">
                        <div class="infotab">
                            <label style="text-decoration: underline">Болид: ${firstCar.label} ${firstCar.model}</label>
                            <h3 style="text-align: center">Состояние болида</h3>
                            <label>Топливо: 32 литра</label> <br>
                            <label>Шины: Нормально</label> <br>
                            <label>Каркас:
                                <c:if test="${fCarcase.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${fCarcase.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${fCarcase.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${fCarcase.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${fCarcase.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
                            <label>Шасси:
                                <c:if test="${fChassis.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${fChassis.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${fChassis.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${fChassis.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${fChassis.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
                            <label>Двигатель:
                                <c:if test="${fEngine.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${fEngine.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${fEngine.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${fEngine.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${fEngine.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
                            <label>Электроника:
                                <c:if test="${fElectronics.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${fElectronics.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${fElectronics.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${fElectronics.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${fElectronics.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label>
                            <br> <br>
                            <form>
                                <label>Причина пит-стопа</label>
                                <input type="text" class="res-selector" name="offer-reason"> <br> <br>
                                <input type="submit" class="res-selector" value="Предложить пит-стоп">
                            </form>
                        </div>
                    </div>
                </div>

                <div class="car-info">
                    <c:if test="${secondCar != null}">
                    <div class="inside_block_wrapper">
                        <div class="infotab">
                            <label style="text-decoration: underline">Болид: ${secondCar.label} ${secondCar.model}</label>
                            <h3 style="text-align: center">Состояние болида</h3>
                            <label>Топливо: 84 литра</label> <br>
                            <label>Шины: Нормально</label> <br>
                            <label>Каркас:
                                <c:if test="${sCarcase.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${sCarcase.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${sCarcase.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${sCarcase.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${sCarcase.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
                            <label>Шасси:
                                <c:if test="${sChassis.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${sChassis.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${sChassis.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${sChassis.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${sChassis.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
                            <label>Двигатель:
                                <c:if test="${sEngine.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${sEngine.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${sEngine.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${sEngine.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${sEngine.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
                            <label>Электроника:
                                <c:if test="${sElectronics.condition.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${sElectronics.condition.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${sElectronics.condition.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${sElectronics.condition.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${sElectronics.condition.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label>
                            <br> <br>
                            <form>
                                <label>Причина пит-стопа</label>
                                <input type="text" class="res-selector" name="offer-reason"> <br> <br>
                                <input type="submit" class="res-selector" value="Предложить пит-стоп">
                            </form>
                        </div>
                    </div>
                    </c:if>
                </div>

            </div>

            <div class="pit-stop-request">
                <div class="inside_block_wrapper">
                    <div class="infotab">
                        <center><h3>Запрос пит-стопа</h3></center>
                            <table class="infotable" style="text-align: left;" border="1" >
                                <tr>
                                    <td>Болид: А</td>
                                    <td>Гонщик: Никита Рогаленко</td>
                                    <td>Пит-стоп: В</td>
                                </tr>
                                <tr>
                                    <td>Время: 00:19:48</td>
                                    <td>Тип: обслуживание</td>
                                    <td>Цель: смена резины на мягкие</td>
                                </tr>
                            </table>
                        Через 1 круг(ов) <br> <br>
                        <form>
                        <table >
                            <tr>
                                <td>Принять:</td>
                                <td>
                                    <label for="accept-to">Ехать к пункту</label>
                                    <input type="text" id="accept-to" class="res-selector" style="width: 15%">
                                </td>
                                <td><input type="submit" class="res-selector" value="Готово"></td>
                            </tr>
                            <tr>
                                <td>Отказать:</td>
                                <td><input type="text" class="res-selector" placeholder="Комментарий"></td>
                                <td><input type="submit" class="res-selector" value="Готово"></td>
                            </tr>
                        </table>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </div>
    </c:if>

</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/MechanicsScript.js"></script>
<script type="text/javascript" src="../scripts/RaceTimeScript.js"></script>
</body>
</html>