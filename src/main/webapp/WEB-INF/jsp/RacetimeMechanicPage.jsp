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
                        <label id="placeName${place.id}" style="text-decoration: underline">Пит-стоп: ${place.name}</label> <br> <br>
                        <label id="shini${place.id}">Шины: Жесткие х${place.tough}, Мягкие х${place.soft} </label> <br>
                        <label id="fuel${place.id}">Топливо: ${place.fuel} литров</label> <br> <br>
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

            <div class="cars-info-area">

                <div class="car-info">
                    <div class="inside_block_wrapper">
                        <div class="infotab">
                            <label style="text-decoration: underline">Болид: ${firstCar.label} ${firstCar.model}</label>
                            <h3 style="text-align: center">Состояние болида</h3>
                            <label>Топливо: ${firstCar.fuel} литра</label> <br>
                            <label>Шины:
                                <c:if test="${firstCar.tires.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${firstCar.tires.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${firstCar.tires.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${firstCar.tires.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${firstCar.tires.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
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
                                <label for="offer-reason${firstCar.id}">Причина пит-стопа</label>
                                <select class="res-selector" id="offer-reason${firstCar.id}" onchange="chooseRepair(${firstCar.id})">
                                    <option value="service" selected>Обслуживание</option>
                                    <option value="repair">Ремонт</option>
                                </select>  <br> <br>
                                <div id="service-menu${firstCar.id}">
                                    <label>Пункт пит-стопа:</label> <br>
                                    <c:forEach items="${pitStopPlaces}" var="place">
                                        <label><input type="radio" name="place-select${firstCar.id}" value="${place.id}"> ${place.name} </label>
                                    </c:forEach>
                                    <br>
                                    <label for="tire-change${firstCar.id}">Сменить резину:</label>
                                    <select class="res-selector" id="tire-change${firstCar.id}">
                                        <option value="NONE">Не надо</option>
                                        <option value="SOFT">Мягкие</option>
                                        <option value="TOUGH">Жесткие</option>
                                    </select> <br>
                                    <label for="fuel-serv${firstCar.id}"> Дозаправка </label>
                                    <input type="number" min="0" id="fuel-serv${firstCar.id}" value="0" class="res-selector" style="width: 20%"> литров
                                    <br> <br>
                                    <input type="text" placeholder="Комментарий" class="res-selector" id="askServiceComment${firstCar.id}">
                                    <br>
                                    <input type="button" class="res-selector" value="Предложить" onclick="offerService(${firstCar.id})">
                                    <label hidden id="service-not-enough${firstCar.id}">На пункте нет столько</label>
                                    <label hidden id="service-ready${firstCar.id}">Отправлено</label>
                                    <label hidden id="service-error${firstCar.id}">Выберите пит-стоп</label>
                                </div>
                                <div id="repair-menu${firstCar.id}" hidden>
                                    <input type="text" class="res-selector" placeholder="Комментарий" id="repair-offer-comm${firstCar.id}" >
                                    <input type="button" class="res-selector" value="Предложить пит-стоп" onclick="offerRepair(${firstCar.id})">
                                    <br> <label hidden id="of-rep-ready${firstCar.id}">Готово</label>
                                </div>
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
                            <label>Топливо: ${secondCar.fuel} литра</label> <br>
                            <label>Шины:
                                <c:if test="${secondCar.tires.toString().equals('PERFECT')}">Идеально</c:if>
                                <c:if test="${secondCar.tires.toString().equals('GOOD')}">Хорошо</c:if>
                                <c:if test="${secondCar.tires.toString().equals('NORMAL')}">Нормально</c:if>
                                <c:if test="${secondCar.tires.toString().equals('BAD')}">Плохо</c:if>
                                <c:if test="${secondCar.tires.toString().equals('AWFUL')}">Ужасно</c:if>
                            </label> <br>
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
                                <label for="offer-reason${secondCar.id}">Причина пит-стопа</label>
                                <select class="res-selector" id="offer-reason${secondCar.id}" onchange="chooseRepair(${secondCar.id})">
                                    <option value="service" selected>Обслуживание</option>
                                    <option value="repair">Ремонт</option>
                                </select>  <br> <br>
                                <div id="service-menu${secondCar.id}">
                                    <label>Пункт пит-стопа:</label> <br>
                                    <c:forEach items="${pitStopPlaces}" var="place">
                                        <label><input type="radio" name="place-select${secondCar.id}" value="${place.id}"> ${place.name} </label>
                                    </c:forEach>
                                    <br>
                                    <label for="tire-change${secondCar.id}">Сменить резину:</label>
                                    <select class="res-selector" id="tire-change${secondCar.id}">
                                        <option value="NONE">Не надо</option>
                                        <option value="SOFT">Мягкие</option>
                                        <option value="TOUGH">Жесткие</option>
                                    </select> <br>
                                    <label for="fuel-serv${secondCar.id}"> Дозаправка </label>
                                    <input type="number" min="0" id="fuel-serv${secondCar.id}" value="0" class="res-selector" style="width: 20%"> литров
                                    <br> <br>
                                    <input type="text" placeholder="Комментарий" class="res-selector" id="askServiceComment${secondCar.id}">
                                    <br>
                                    <input type="button" class="res-selector" value="Предложить" onclick="offerService(${secondCar.id})">
                                    <label hidden id="service-not-enough${secondCar.id}">На пункте нет столько</label>
                                    <label hidden id="service-ready${secondCar.id}">Отправлено</label>
                                    <label hidden id="service-error${secondCar.id}">Выберите пит-стоп</label>
                                </div>
                                <div id="repair-menu${secondCar.id}" hidden>
                                    <input type="text" class="res-selector" placeholder="Комментарий" id="repair-offer-comm${secondCar.id}" >
                                    <input type="button" class="res-selector" value="Предложить пит-стоп" onclick="offerRepair(${secondCar.id})">
                                    <br> <label hidden id="of-rep-ready${secondCar.id}">Готово</label>
                                </div>
                            </form>
                        </div>
                    </div>
                    </c:if>
                </div>

            </div>

            <div class="pit-stop-request">
                <div class="inside_block_wrapper">
                    <div class="infotab">
                        <center><h3>Запросы пит-стопа</h3></center>
                        <c:forEach items="${pilChang_review}" var="change">
                            <h4>Смена пилота</h4>
                        <table class="infotable" style="text-align: left;" border="1" >
                            <tr>
                                <td>Болид ${pilChang_review_cars.get(pilChang_review.indexOf(change)).label}
                                        ${pilChang_review_cars.get(pilChang_review.indexOf(change)).model}</td>
                                <td>Пилот ${pilChang_review_pilots.get(pilChang_review.indexOf(change)).name}
                                        ${pilChang_review_pilots.get(pilChang_review.indexOf(change)).surname}</td>
                                <td>Пит-стоп ${pilChang_review_places.get(pilChang_review.indexOf(change)).name}</td>
                                <td>Комментарий: ${change.comment}</td>
                            </tr>
                            <tr>
                                <td><input type="text" placeholder="Ваш ответ" class="res-selector" id="pcAnsw${change.id}"></td>
                                <td>
                                    <select class="res-selector" id="pilot4Change${change.id}">
                                    <c:forEach items="${freePilots}" var="pilot">
                                        <option value="${pilot.userId}">${pilot.name} ${pilot.surname}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                                <td><input type="button" value="Принять" class="res-selector" onclick="handleChangePilot(${change.id}, true)"></td>
                                <td><input type="button" value="Отказать" class="res-selector" onclick="handleChangePilot(${change.id}, false)" ></td>
                            </tr>
                        </table>
                        </c:forEach>
                        <c:forEach items="${repair_review}" var="repair">
                            <h4>Ремонт</h4>
                            <table class="infotable" style="text-align: left;" border="1" >
                                <tr>
                                    <td>Болид: ${repair_review_cars.get(repair_review.indexOf(repair)).label} ${repair_review_cars.get(repair_review.indexOf(repair)).model} </td>
                                    <td>Комментарий: ${repair.comment}</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><input type="text" placeholder="Ваш ответ" class="res-selector" id="repAnsw${repair.id}"></td>
                                    <td><input type="button" value="Принять" class="res-selector" onclick="handleRepair(${repair.id}, true)"></td>
                                    <td><input type="button" value="Отказать" class="res-selector" onclick="handleRepair(${repair.id}, false)" ></td>
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
                            <input type="button" class="res-selector" value="Принять" onclick="handleService(${service.id}, true)"/>
                            <input type="button" class="res-selector" value="Отказать" onclick="handleService(${service.id}, false)"/>
                        </c:forEach>
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