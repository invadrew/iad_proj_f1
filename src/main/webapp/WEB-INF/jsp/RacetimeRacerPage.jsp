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
            Вы не являетесь участником этой гонки

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
                    <label>Топливо: ${car.fuel} литра</label> <br>
                    <label>Шины:
                        <c:if test="${car.tires.toString().equals('PERFECT')}">Идеально</c:if>
                        <c:if test="${car.tires.toString().equals('GOOD')}">Хорошо</c:if>
                        <c:if test="${car.tires.toString().equals('NORMAL')}">Нормально</c:if>
                        <c:if test="${car.tires.toString().equals('BAD')}">Плохо</c:if>
                        <c:if test="${car.tires.toString().equals('AWFUL')}">Ужасно</c:if>
                    </label> <br>
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
                            <option value="NONE">Не надо</option>
                            <option value="SOFT">Мягкие</option>
                            <option value="TOUGH">Жесткие</option>
                        </select> <br>
                        <label for="fuel-serv"> Дозаправка </label>
                        <input type="number" min="0" id="fuel-serv" value="0" class="res-selector" style="width: 20%"> литров
                        <br> <br>
                        <input type="text" placeholder="Комментарий" class="res-selector" id="askServiceComment">
                        <br>
                        <input type="button" class="res-selector" value="Запросить" onclick="askService()">
                        <label hidden id="service-not-enough">На пункте нет столько</label>
                        <label hidden id="service-ready">Отправлено</label>
                        <label hidden id="service-error">Выберите пит-стоп</label>
                    </form>
                </div>
            </div>
        </div>

        <div class="RepairRequestArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <h3 style="text-align: center">Запрос ремонта</h3>
                    <input type="text" class="res-selector" placeholder="Комментарий" id ="repair-comment" /> <br>
                    <input type="button" value="Запросить" class="res-selector" onclick="askRepair()"> <label hidden id="repair-sent"> Отправлено </label>
                </div>
            </div>
        </div>
    </div>
        <div class="RightPart">
        <div class="RaceMessageTableArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <table class="infotable" id="transfersTable" border="1" style="max-height: 30%">
                        <tr>
                            <th>Время</th>
                            <th>Сообщение</th>
                        </tr>
                        <tr><td colspan="2">Трансферы</td></tr>
                        <c:forEach items="${transfers}" var="transfer">
                            <tr>
                                <td>${transfer.time}</td>
                                <td>Из пункта ${placesFrom.get(transfers.indexOf(transfer)).name} в пункт ${placesTo.get(transfers.indexOf(transfer)).name} перенесено:
                                    <c:if test="${transfer.transfer.toString().equals('TOUGH')}"> жесткие шины в количестве </c:if>
                                    <c:if test="${transfer.transfer.toString().equals('SOFT')}"> мягкие шины в количестве </c:if>
                                    <c:if test="${transfer.transfer.toString().equals('FUEL')}"> топливо в объеме </c:if>
                                        ${transfer.amount}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <table class="infotable" border="1" style="max-height: 30%">
                        <tr><td colspan="2">Замена пилотов</td></tr>
                        <c:forEach items="${pilChang_accept}" var="change">
                            <tr>
                                <td> ${change.time} </td>
                            <td> Болид ${pilChang_accept_cars.get(pilChang_accept.indexOf(change)).label} ${pilChang_accept_cars.get(pilChang_accept.indexOf(change)).model}.
                                Одобрена замена пилота ${pilChang_accept_pilots.get(pilChang_accept.indexOf(change)).name} ${pilChang_accept_pilots.get(pilChang_accept.indexOf(change)).surname}.
                                Комментарий: ${change.comment}</td>
                            </tr>
                        </c:forEach>
                        <c:forEach items="${pilChang_refuse}" var="change">
                            <tr>
                                <td> ${change.time} </td>
                                <td> Болид ${pilChang_refuse_cars.get(pilChang_refuse.indexOf(change)).label} ${pilChang_refuse_cars.get(pilChang_refuse.indexOf(change)).model}.
                                    Отклонена замена пилота ${pilChang_refuse_pilots.get(pilChang_refuse.indexOf(change)).name} ${pilChang_refuse_pilots.get(pilChang_refuse.indexOf(change)).surname}.
                                    Комментарий: ${change.comment}</td>
                            </tr>
                        </c:forEach>
                    </table>
                        <table class="infotable" border="1" style="max-height: 30%">
                            <tr><td colspan="2">Ремонт деталей</td></tr>
                            <c:forEach items="${repair_accept}" var="repair">
                                <tr>
                                    <td>
                                        Было одобрено тех. обслуживание болида ${repair_accept_cars.get(repair_accept.indexOf(repair)).label} ${repair_accept_cars.get(repair_accept.indexOf(repair)).model}
                                        Комментарий: ${repair.comment}
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:forEach items="${repair_refuse}" var="repair">
                                <tr>
                                    <td>
                                        Было отклонено тех. обслуживание болида ${repair_refuse_cars.get(repair_refuse.indexOf(repair)).label} ${repair_refuse_cars.get(repair_refuse.indexOf(repair)).model}
                                        Комментарий: ${repair.comment}
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <table class="infotable" border="1" style="max-height: 30%">
                            <tr><td colspan="2">Обслуживание болидов</td></tr>
                            <c:forEach items="${service_accept}" var="service">
                                <tr>
                                    <td>${service.time}</td>
                                    <td>Одобрен пит-стоп. Болид: ${service_accept_cars.get(service_accept.indexOf(service)).label} ${service_accept_cars.get(service_accept.indexOf(service)).model}.
                                         Пункт: ${service_accept_places.get(service_accept.indexOf(service)).name}.
                                        <c:if test="${service.tires.toString().equals('SOFT')}">
                                            Замена шин на мягкие
                                        </c:if>
                                        <c:if test="${service.tires.toString().equals('TOUGH')}">
                                            Замена шин на жесткие
                                        </c:if>
                                        <c:if test="${service.fuel > 0}">
                                            Дозаправка: ${service.fuel}
                                        </c:if>
                                        Комментарий: ${service.comment}
                                     </td>
                                </tr>
                            </c:forEach>
                            <c:forEach items="${service_refuse}" var="service">
                                <tr>
                                    <td>${service.time}</td>
                                    <td>Отклонен пит-стоп. Болид: ${service_refuse_cars.get(service_refuse.indexOf(service)).label} ${service_refuse_cars.get(service_refuse.indexOf(service)).model}.
                                        Пункт: ${service_refuse_places.get(service_refuse.indexOf(service)).name}.
                                        <c:if test="${service.tires.toString().equals('SOFT')}">
                                            Замена шин на мягкие
                                        </c:if>
                                        <c:if test="${service.tires.toString().equals('TOUGH')}">
                                            Замена шин на жесткие
                                        </c:if>
                                        <c:if test="${service.fuel > 0}">
                                            Дозаправка: ${service.fuel}
                                        </c:if>
                                        Комментарий: ${service.comment}
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                </div>
            </div>
        </div>

        <div class="PitStopOfferArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <h3 style="text-align: center">Предложение пит-стопа</h3>
                    <c:forEach items="${repair_review}" var="repair">
                    <table class="infotable" style="max-height: 30%" border="1">
                        <tr>
                            <td>Предложение ремонта</td>
                            <td>Комментарий: ${repair.comment}</td>
                            <td><input type="text" class="res-selector" id="rev-rep-comm${repair.id}" placeholder="Ваш ответ"></td>
                            <td><input type="button" class="res-selector" value="Принять" onclick="confirmRepair(${repair.id}, true)"></td>
                            <td><input type="button" class="res-selector" value="Отказать" onclick="confirmRepair(${repair.id}, false)" ></td>
                        </tr>
                    </table>
                    </c:forEach>
                    <c:forEach items="${service_review}" var="service">
                    <table class="infotable" style="text-align: left;" border="1" >
                        <tr>
                            <td>Болид: ${service_review_cars.get(service_review.indexOf(service)).label} ${service_review_cars.get(service_review.indexOf(service)).model} </td>
                            <td>Пит-стоп: ${service_review_places.get(service_review.indexOf(service)).name}</td>
                            <td>
                                <c:if test="${service.tires.toString().equals('SOFT')}">
                                    Замена шин на мягкие.
                                </c:if>
                                <c:if test="${service.tires.toString().equals('TOUGH')}">
                                    Замена шин на жесткие.
                                </c:if>
                                <c:if test="${service.fuel > 0}">
                                    Дозаправка: ${service.fuel}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Комментарий: ${service.comment}
                            </td>
                            <td><input type="text" placeholder="Ваш ответ" class="res-selector" id="servAnsw${service.id}"></td>
                        </tr>
                    </table>
                        <br>
                        <input type="button" class="res-selector" value="Принять" onclick="confirmService(${service.id}, true)"/>
                        <input type="button" class="res-selector" value="Отказать" onclick="confirmService(${service.id}, false)"/>
                    </c:forEach>
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
                            <label><input type="radio" name="pilot-place-select" value="${place.id}"> ${place.name} </label>
                        </c:forEach>
                         <br>
                        <label for="pilot-reason">Причина</label>
                        <input id="pilot-reason" class="res-selector" type="text">
                         <br>
                        <input type="button" class="res-selector" value="Отправить запрос" onclick="changePilot()">
                        <label hidden id="pilot-change-busy">Нет свободных пилотов</label>
                        <label hidden id="pilot-change-ready">Заявка отправлена</label>
                        <label hidden id="pilot-change-error">Выберите пит-стоп</label>
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