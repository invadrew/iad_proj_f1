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