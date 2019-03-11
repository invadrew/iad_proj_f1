<%@ page import="java.util.List" %>
<%@ page import="com.rogo.inv.iadprojf1.entity.ComponentCondition" %>
<%@ page import="java.util.ArrayList" %>
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
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="TeamNameArea">
        <br>
        <center><label style="padding-top: 3px" ><b>${team.name}</b></label></center>
        <center><c:if test="${team == null}">У вас пока нет команды ...</c:if></center>
    </div>
    <div class="MainArea">
        <div class="CarSelectArea">
            <div class="inside_block_wrapper">
                <div class="infotab"> <center>
                    <input type="button" class="res-selector" value="Болиды" onclick="chooseCars()"> <br>
                    <input type="button" class="res-selector" value="Склад" onclick="chooseStore()"> <br>
                    <c:if test="${team != null}">
                    <sec:authorize access="hasAuthority('MECHANIC')">
                    <input type="button" class="res-selector" value="Заменить деталь" onclick="chooseChange()">
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('CONSTRUCTOR')">
                        <input type="button" class="res-selector" value="Добавить деталь" onclick="location.href='/add_detail'">
                    </sec:authorize>
                    </c:if>
                </center>
                </div>
                <div class="FilterArea" hidden id="filter">
                    <div class="inside_block_wrapper" style="background: #2f2727">

                        <div class="infotab">
                            <center><h3>Фильтр</h3></center>

                                <label for="compType">Тип</label>
                                <select class="res-selector" id="compType" style="width: 70% !important;" onchange="chooseFilter()">
                                    <option selected value="any">Любой</option>
                                    <option value="carcase">Каркас</option>
                                    <option value="chassis">Шасси</option>
                                    <option value="engine">Двигатель</option>
                                    <option value="electronics">Электроника</option>
                                </select>

                        </div>

                            <div class="infotab" id="carcase_filter" hidden>
                                <form>
                                    <% List<Object> carcMaterial = new ArrayList<>();
                                       pageContext.setAttribute("material", carcMaterial);%>
                                    <label for="carc-material">Материал</label>
                                    <select class="res-selector" id="carc-material" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${carcaseStorage}" var="carc">
                                            <c:if test="${!material.contains(carc.material)}">
                                              <option value="${carc.material}">${carc.material}</option>
                                                <c:set var="mat" value="${carc.material}"/>
                                                <% carcMaterial.add(pageContext.getAttribute("mat"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>

                                    <% List<Object> carcRWing = new ArrayList<>();
                                        pageContext.setAttribute("Rwing", carcRWing);%>
                                    <label for="carc-rareWing">Спойлер</label>
                                    <select class="res-selector" id="carc-rareWing" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${carcaseStorage}" var="carc">
                                            <c:if test="${!Rwing.contains(carc.rearWing)}">
                                            <option value="${carc.rearWing}">${carc.rearWing}</option>
                                                <c:set var="rwing" value="${carc.rearWing}"/>
                                                <% carcRWing.add(pageContext.getAttribute("rwing"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>

                                    <% List<Object> carcSArcs = new ArrayList<>();
                                        pageContext.setAttribute("carcSArcs", carcSArcs);%>
                                    <label for="carc-safetyArcs">Дуги безопасности</label>
                                    <select class="res-selector" id="carc-safetyArcs" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${carcaseStorage}" var="carc">
                                            <c:if test="${!carcSArcs.contains(carc.safetyArcs)}">
                                            <option value="${carc.safetyArcs}">${carc.safetyArcs}</option>
                                                <c:set var="sarcs" value="${carc.safetyArcs}"/>
                                                <% carcSArcs.add(pageContext.getAttribute("sarcs"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>

                                    <% List<Object> carcWings = new ArrayList<>();
                                        pageContext.setAttribute("carcWings", carcWings);%>
                                    <label for="carc-wings">Крылья</label>
                                    <select class="res-selector" id="carc-wings" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${carcaseStorage}" var="carc">
                                            <c:if test="${!carcWings.contains(carc.wings)}">
                                            <option value="${carc.wings}">${carc.wings}</option>
                                                <c:set var="wings" value="${carc.wings}"/>
                                                <% carcWings.add(pageContext.getAttribute("wings"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>

                                    <label for="carc-cond">Состояние</label>
                                    <select class="res-selector" id="carc-cond" style="width: 70% !important;">
                                        <option value="ANY">Любое</option>
                                        <option value="PERFECT">Идеальное</option>
                                        <option value="GOOD">Хорошее</option>
                                        <option value="NORMAL">Нормальное</option>
                                        <option value="BAD">Плохое</option>
                                        <option value="AWFUL">Ужасное</option>
                                    </select>
                                    <br>

                                    <input type="button" class="res-selector" value="Показать" style="margin-top: 4%" onclick="sendCarcaseData(event)" >
                                </form>
                            </div>

                            <div class="infotab" id="chassis_filter" hidden>
                                <form>

                                    <% List<Object> chassModel = new ArrayList<>();
                                        pageContext.setAttribute("chassModel", chassModel);%>
                                    <label for="chass-model">Модель</label>
                                    <select class="res-selector" id="chass-model" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${chassisStorage}" var="chass">
                                            <c:if test="${!chassModel.contains(chass.model)}">
                                                <option value="${chass.model}">${chass.model}</option>
                                                <c:set var="chModel" value="${chass.model}"/>
                                                <% chassModel.add(pageContext.getAttribute("chModel"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>
                                    Высота
                                    <br>
                                    <label for="height-from">От</label>
                                    <input type="number" min="0" class="res-selector" id="height-from" style="width: 30% !important;">
                                    <label for="height-to">до</label>
                                    <input type="number" min="0" class="res-selector" id="height-to" style="width: 30% !important;">
                                    <br>
                                    Ширина
                                    <br>
                                    <label for="width-from">От</label>
                                    <input type="number" min="0" class="res-selector" id="width-from" style="width: 30% !important;">
                                    <label for="width-to">до</label>
                                    <input type="number" min="0" class="res-selector" id="width-to" style="width: 30% !important;">
                                    <br>

                                    <label for="chass-cond">Состояние</label>
                                    <select class="res-selector" id="chass-cond" style="width: 70% !important;">
                                        <option value="ANY">Любое</option>
                                        <option value="PERFECT">Идеальное</option>
                                        <option value="GOOD">Хорошее</option>
                                        <option value="NORMAL">Нормальное</option>
                                        <option value="BAD">Плохое</option>
                                        <option value="AWFUL">Ужасное</option>
                                    </select>
                                    <br>

                                    <input type="button" class="res-selector" value="Показать" style="margin-top: 4%" onclick="sendChassisData(event)" >
                                </form>
                            </div>

                            <div class="infotab" id="engine_filter" hidden>

                                <form>

                                    <% List<Object> engModel = new ArrayList<>();
                                        pageContext.setAttribute("engModel", engModel);%>
                                    <label for="eng-model">Модель</label>
                                    <select class="res-selector" id="eng-model" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${enginesStorage}" var="eng">
                                            <c:if test="${!engModel.contains(eng.model)}">
                                                <option value="${eng.model}">${eng.model}</option>
                                                <c:set var="engModel" value="${eng.model}"/>
                                                <% engModel.add(pageContext.getAttribute("engModel"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>

                                    Цилиндров
                                    <br>
                                    <label for="cycl-from">От</label>
                                    <input type="number" min="0" class="res-selector" id="cycl-from" style="width: 30% !important;">
                                    <label for="cycl-to">до</label>
                                    <input type="number" min="0" class="res-selector" id="cycl-to" style="width: 30% !important;">
                                    <br>
                                    Объем
                                    <br>
                                    <label for="cap-from">От</label>
                                    <input type="number" min="0" class="res-selector" id="cap-from" style="width: 30% !important;">
                                    <label for="cap-to">до</label>
                                    <input type="number" min="0" class="res-selector" id="cap-to" style="width: 30% !important;">
                                    <br>
                                    Масса
                                    <br>
                                    <label for="mass-from">От</label>
                                    <input type="number" min="0" class="res-selector" id="mass-from" style="width: 30% !important;">
                                    <label for="mass-to">до</label>
                                    <input type="number" min="0" class="res-selector" id="mass-to" style="width: 30% !important;">
                                    <br>
                                    Ход поршня
                                    <br>
                                    <label for="stroke-from">От</label>
                                    <input type="number" min="0" class="res-selector" id="stroke-from" style="width: 30% !important;">
                                    <label for="stroke-to">до</label>
                                    <input type="number" min="0" class="res-selector" id="stroke-to" style="width: 30% !important;">
                                    <br>

                                    <label for="eng-cond">Состояние</label>
                                    <select class="res-selector" id="eng-cond" style="width: 70% !important;">
                                        <option value="ANY">Любое</option>
                                        <option value="PERFECT">Идеальное</option>
                                        <option value="GOOD">Хорошее</option>
                                        <option value="NORMAL">Нормальное</option>
                                        <option value="BAD">Плохое</option>
                                        <option value="AWFUL">Ужасное</option>
                                    </select>
                                    <br>

                                    <input type="button" class="res-selector" value="Показать" style="margin-top: 4%" onclick="sendEngineData(event)" >
                                </form>

                            </div>

                            <div class="infotab" id="electronics_filter" hidden>
                                <form>

                                    <% List<Object> elecTel = new ArrayList<>();
                                        pageContext.setAttribute("elecTel", elecTel);%>
                                    <label for="elec-tel">Телеметрия</label>
                                    <select class="res-selector" id="elec-tel" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${electronicsStorage}" var="elec">
                                            <c:if test="${!elecTel.contains(elec.telemetry)}">
                                                <option value="${elec.telemetry}">${elec.telemetry}</option>
                                                <c:set var="telemetry" value="${elec.telemetry}"/>
                                                <% elecTel.add(pageContext.getAttribute("telemetry"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>

                                    <% List<Object> elecCS = new ArrayList<>();
                                        pageContext.setAttribute("elecCS", elecCS);%>
                                    <label for="elec-cs">Система контроля</label>
                                    <select class="res-selector" id="elec-cs" style="width: 70% !important;">
                                        <option value="any">Любой</option>
                                        <c:forEach items="${electronicsStorage}" var="elec">
                                            <c:if test="${!elecCS.contains(elec.controlSystem)}">
                                                <option value="${elec.controlSystem}">${elec.controlSystem}</option>
                                                <c:set var="cs" value="${elec.controlSystem}"/>
                                                <% elecCS.add(pageContext.getAttribute("cs"));%>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <br>

                                    <label for="elec-cond">Состояние</label>
                                    <select class="res-selector" id="elec-cond" style="width: 70% !important;">
                                        <option value="ANY">Любое</option>
                                        <option value="PERFECT">Идеальное</option>
                                        <option value="GOOD">Хорошее</option>
                                        <option value="NORMAL">Нормальное</option>
                                        <option value="BAD">Плохое</option>
                                        <option value="AWFUL">Ужасное</option>
                                    </select>
                                    <br>

                                    <input type="button" class="res-selector" value="Показать" style="margin-top: 4%" onclick="sendElectronicsData(event)" >
                                </form>
                            </div>

                    </div>
                </div>

            </div>
        </div>
        <div class="WorkingArea">
            <div class="inside_block_wrapper">

                <div class="infotab">
                    <c:if test="${team == null}"> У вас нет команды </c:if>
                    <div class="cars-main-zone" id="carsZone">
                        <c:if test="${cars.size() == 0}">
                            <h3 style="text-align: center;">Нет болидов</h3>
                        </c:if>
                        <c:forEach items="${cars}" var="car">
                            <div class="infotab">
                        <div class="PhotoArea">
                            <div class="inside_block_wrapper" style="background: #2f2727">
                                <div class="infotab">
                            <div style="width: 50%; height: 50%; position: relative; margin-left: 25%; margin-right: 25%">
                            <img src="${carPhotos.get(cars.indexOf(car)).path}" style="width: 100%; height: 100%;">
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
                            <sec:authorize access="hasAuthority('MECHANIC')">

                                <c:if test="${canBuy}">
                                <form>
                                    <input type="hidden" value="${car.id}">
                                    <input type="button" value="Разобрать болид" id="car-destroyer${car.id}" class="res-selector" style="width: 30% !important;" onclick="confirmDisass(${car.id})">
                                    <br>
                                    <label id="really${car.id}" hidden>Вы уверены?</label>
                                    <br>
                                    <input type="button" id="yes${car.id}" value="Да" class="res-selector" style="width: 10% !important; margin-right: 4%" hidden onclick="disassCar(${car.id})">
                                    <input type="button" id="no${car.id}" value="Нет" class="res-selector" style="width: 10% !important; margin-left: 4%" hidden onclick="goBack(${car.id})">
                                </form>
                                </c:if>
                            </sec:authorize>
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
                                        Дуги безопасности: ${carcases.get(cars.indexOf(car)).safetyArcs}.
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
                            <table class="infotable" style="text-align: left">
                                <c:if test="${!carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">
                                <tr id="ca${car.id}">
                                    <td>Корпус</td>
                                    <td>Цена:
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">
                                            <fmt:formatNumber value="${carcases.get(cars.indexOf(car)).price / 10}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">
                                            <fmt:formatNumber value="${carcases.get(cars.indexOf(car)).price / 9}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">
                                            <fmt:formatNumber value="${carcases.get(cars.indexOf(car)).price / 8}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${carcases.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">
                                            <fmt:formatNumber value="${carcases.get(cars.indexOf(car)).price / 7}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                    </td>
                                    <td><input type="button" value="Починить" class="res-selector" onclick="repairCarc(${car.id})"></td>
                                    <td id="notEnoughMoneyCarc${car.id}" hidden> Недостаточно денег </td>
                                </tr>
                                </c:if>
                                <c:if test="${!chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">
                                <tr id="chs${car.id}">
                                    <td>Шасси</td>
                                    <td>Цена:
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">
                                            <fmt:formatNumber value="${chassis.get(cars.indexOf(car)).price / 10}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">
                                            <fmt:formatNumber value="${chassis.get(cars.indexOf(car)).price / 9}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">
                                            <fmt:formatNumber value="${chassis.get(cars.indexOf(car)).price / 8}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${chassis.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">
                                            <fmt:formatNumber value="${chassis.get(cars.indexOf(car)).price / 7}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                    </td>
                                    <td><input type="button" value="Починить" class="res-selector" onclick="repairChass(${car.id})"></td>
                                    <td id="notEnoughMoneyChass${car.id}" hidden> Недостаточно денег </td>
                                </tr>
                                </c:if>
                                <c:if test="${!engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">
                                <tr id="en${car.id}">
                                    <td>Двигатель</td>
                                    <td>Цена:
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">
                                            <fmt:formatNumber value="${engines.get(cars.indexOf(car)).price / 10}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">
                                            <fmt:formatNumber value="${engines.get(cars.indexOf(car)).price / 9}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">
                                            <fmt:formatNumber value="${engines.get(cars.indexOf(car)).price / 8}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${engines.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">
                                            <fmt:formatNumber value="${engines.get(cars.indexOf(car)).price / 7}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                    </td>
                                    <td><input type="button" value="Починить" class="res-selector" onclick="repairEng(${car.id})"></td>
                                    <td id="notEnoughMoneyEng${car.id}" hidden> Недостаточно денег </td>
                                </tr>
                                </c:if>
                                <c:if test="${!electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.PERFECT)}">
                                <tr id="el${car.id}">
                                    <td>Электроника</td>
                                    <td>Цена:
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.GOOD)}">
                                            <fmt:formatNumber value="${electronics.get(cars.indexOf(car)).price / 10}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.NORMAL)}">
                                            <fmt:formatNumber value="${electronics.get(cars.indexOf(car)).price / 9}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.BAD)}">
                                            <fmt:formatNumber value="${electronics.get(cars.indexOf(car)).price / 8}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                        <c:if test="${electronics.get(cars.indexOf(car)).condition.equals(ComponentCondition.AWFUL)}">
                                            <fmt:formatNumber value="${electronics.get(cars.indexOf(car)).price / 7}" type="number" maxFractionDigits="0"/>
                                        </c:if>
                                    </td>
                                    <td><input type="button" value="Починить" class="res-selector" onclick="repairElec(${car.id})"></td>
                                    <td id="notEnoughMoneyElec${car.id}" hidden> Недостаточно денег </td>
                                </tr>
                                </c:if>
                            </table>
                        </div>
                        </sec:authorize>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="storage-area" >

                        <div class="StoreTableArea" id="storage" style="width: 100%; margin: 2%" hidden >
                            <h1 style="text-align: center">Склад</h1>
                            <h2 id="carcTabLabel">Каркасы</h2>
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

                            <h2 id="chassTabLabel">Шасси</h2>
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

                            <h2 id="engTabLabel">Двигатели</h2>
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

                            <h2 id="elecTabLabel">Электроника</h2>
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

                    <c:if test="${team != null}">
                    <div class="DetailChange" id="changes" hidden>
                            <center><h3>Замена оборудования</h3></center>
                            <sec:authorize access="hasAuthority('MECHANIC')">
                            <form>
                                <label for="car-select">Выберите болид</label>
                                <select id="car-select" class="res-selector" style="width: 30% !important;" onchange="selectCompType()">
                                    <c:forEach items="${cars}" var="car">
                                    <option value="${car.id}">${car.label} ${car.model}</option>
                                    </c:forEach>
                                </select> <br>
                                <label for="change-type-select">Тип детали</label>
                                <select id="change-type-select" class="res-selector" style="width: 30% !important;" onchange="selectCompType()">
                                    <option value="Carcase" selected>Каркас</option>
                                    <option value="Chassis">Шасси</option>
                                    <option value="Engine">Двигатель</option>
                                    <option value="Electronics">Электроника</option>
                                </select> <br>

                                <div id="carcForChange">
                                    <label id="currCarc"> Текущий элемент: </label> <br>
                                <label for="change-detail-carc">Заменить на</label>
                                <select id="change-detail-carc" class="res-selector" style="width: 30% !important;">
                                    <c:forEach items="${freeCarc}" var="fcarc">
                                    <option value="${fcarc.id}">${fcarc.rearWing} ${fcarc.safetyArcs} ${fcarc.wings}</option>
                                    </c:forEach>
                                    <c:if test="${freeCarc.isEmpty()}">
                                        <br>
                                        Нет свободных деталей
                                    </c:if>
                                </select> <br>
                                    <c:if test="${canBuy}">
                                <input type="button" class="res-selector" value="Подтвердить" style="width: 30% !important;" onclick="change_carcase()">
                                    </c:if>
                                    <c:if test="${!canBuy}">
                                        Вы не можете совершить это действие, обратитесь к менеджеру за правами
                                    </c:if>
                                </div>

                                <div id="chassForChange" hidden>
                                    <label id="currChass"> Текущий элемент: </label> <br>
                                    <label for="change-detail-chass">Заменить на</label>
                                    <select id="change-detail-chass" class="res-selector" style="width: 30% !important;">
                                        <c:forEach items="${freeChass}" var="fchass">
                                        <option value="${fchass.id}">${fchass.model} (h:${fchass.height} w:${fchass.width})</option>
                                        </c:forEach>
                                        <c:if test="${freeChass.isEmpty()}">
                                            <br>
                                            Нет свободных деталей
                                        </c:if>
                                    </select> <br>
                                    <c:if test="${canBuy}">
                                    <input type="button" class="res-selector" value="Подтвердить" style="width: 30% !important;" onclick="change_chassis()">
                                    </c:if>
                                    <c:if test="${!canBuy}">
                                        Вы не можете совершить это действие, обратитесь к менеджеру за правами
                                    </c:if>
                                </div>

                                <div id="engForChange" hidden>
                                    <label id="currEng"> Текущий элемент: </label> <br>
                                    <label for="change-detail-eng">Заменить на</label>
                                    <select id="change-detail-eng" class="res-selector" style="width: 30% !important;">
                                        <c:forEach items="${freeEng}" var="feng">
                                        <option value="${feng.id}">${feng.model} ${feng.cyclinders} цил. ${feng.capacity} л.</option>
                                        </c:forEach>
                                        <c:if test="${freeEng.isEmpty()}">
                                            <br>
                                            Нет свободных деталей
                                        </c:if>
                                    </select> <br>
                                    <c:if test="${canBuy}">
                                    <input type="button" class="res-selector" value="Подтвердить" style="width: 30% !important;" onclick="change_engine()">
                                    </c:if>
                                    <c:if test="${!canBuy}">
                                        Вы не можете совершить это действие, обратитесь к менеджеру за правами
                                    </c:if>
                                </div>

                                <div id="elecForChange" hidden>
                                    <label id="currElec"> Текущий элемент: </label> <br>
                                    <label for="change-detail-elec">Заменить на</label>
                                    <select id="change-detail-elec" class="res-selector" style="width: 30% !important;">
                                        <c:forEach items="${freeElec}" var="felec">
                                        <option value="${felec.id}">${felec.telemetry} ${felec.controlSystem}</option>
                                        </c:forEach>
                                        <c:if test="${freeElec.isEmpty()}">
                                            <br>
                                            Нет свободных деталей
                                        </c:if>
                                    </select> <br>
                                    <c:if test="${canBuy}">
                                    <input type="button" class="res-selector" value="Подтвердить" style="width: 30% !important;" onclick="change_electronics()">
                                    </c:if>
                                    <c:if test="${!canBuy}">
                                        Вы не можете совершить это действие, обратитесь к менеджеру за правами
                                    </c:if>
                                </div>
                                <label id="neok" hidden> Нет свободных деталей</label>

                            </form>
                            </sec:authorize>
                    </div>
                    </c:if>

                </div>
            </div>



        </div>
    </div>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/GarageScript.js"> </script>
</body>
</html>