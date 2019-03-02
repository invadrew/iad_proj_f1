<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/DetailAddStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Добавить комплектующее</title>
</head>
<body>
<style>
    .res-selector {
        width: 20%;
    }
</style>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="TeamNameArea">
        <br>
        <center><label style="padding-top: 3px" ><b>${team.name}</b></label></center>
    </div>
    <div class="MainArea">
    <div class="inside_block_wrapper">
        <div class="infotab">
            <center><h3>Добавить болид</h3></center>
            <form>
                <label for="carLabel">Марка</label>
                <input type="text" id="carLabel" class="res-selector">
                <label for="carModel">Модель</label>
                <input type="text" id="carModel" class="res-selector">
                <br>
                <label for="carCarcSelect">Выберите каркас</label>
                <select id="carCarcSelect" class="res-selector" >
                    <c:forEach items="${freeCarc}" var="cCarc">
                    <option value="${cCarc.id}"> ${cCarc.rearWing} ${cCarc.safetyArcs} ${cCarc.wings} </option>
                    </c:forEach>
                    <c:if test="${freeCarc.isEmpty()}">
                        <br>
                        Нет свободных деталей
                    </c:if>
                </select>
                <br>
                <label for="carChassSelect">Выберите шасси</label>
                <select id="carChassSelect"  class="res-selector">
                    <c:forEach items="${freeChass}" var="cChass">
                    <option value="${cChass.id}"> ${cChass.model} (h:${cChass.height} w:${cChass.width}) </option>
                    </c:forEach>
                    <c:if test="${freeChass.isEmpty()}">
                        <br>
                        Нет свободных деталей
                    </c:if>
                </select>
                <br>
                <label for="carEngSelect">Выберите двигатель</label>
                <select id="carEngSelect" class="res-selector">
                    <c:forEach items="${freeEng}" var="cEng">
                    <option value="${cEng.id}"> ${cEng.model} ${cEng.cyclinders} цил. ${cEng.capacity} л. </option>
                    </c:forEach>
                    <c:if test="${freeEng.isEmpty()}">
                        <br>
                        Нет свободных деталей
                    </c:if>
                </select>
                <br>
                <label for="carElecSelect">Выберите электронику</label>
                <select id="carElecSelect" class="res-selector">
                    <c:forEach items="${freeElec}" var="cElec">
                    <option value="${cElec.id}"> ${cElec.telemetry} ${cElec.controlSystem} </option>
                    </c:forEach>
                    <c:if test="${freeElec.isEmpty()}">
                        <br>
                        Нет свободных деталей
                    </c:if>
                </select>
                <br>
                <input type="button" value="Добавить" class="res-selector" onclick="addCar()">
                <br>
                <label id="carOk" hidden> Готово </label>
                <label id="carFail" hidden> Не хватает деталей </label>
                <label id="carFailfields" hidden> Не все поля заполнены </label>
            </form>
        </div>
    </div>
        <div class="inside_block_wrapper">
            <div class="infotab">
                <center><h3>Выберите тип добавляемой детали</h3>
                <input type="button" value="Каркас" id="carcButt" class="res-selector" onclick="chooseCarcase()">
                <input type="button" value="Шасси" id="chButt" class="res-selector" onclick="chooseChassis()">
                <input type="button" value="Двигатель" id="engButt" class="res-selector" onclick="chooseEngine()">
                <input type="button" value="Электроника" id="elecButt" class="res-selector" onclick="chooseElectronics()">
                </center>
            </div>
        </div>
      <!-- Detail adding zones -->
        <div class="inside_block_wrapper" id="carcAddArea" hidden>
            <div class="infotab">
                <center><h3>Добавить каркас</h3></center>
                <form>
                    <label for="carcMaterial">Материал</label>
                    <input type="text" id="carcMaterial" class="res-selector">
                    <br>
                    <label for="rear-wing">Спойлер</label>
                    <input type="text" id="rear-wing" class="res-selector">
                    <br>
                    <label for="safe-arcs">Дуги безопасности</label>
                    <input type="text" id="safe-arcs" class="res-selector">
                    <br>
                    <label for="wings">Крылья</label>
                    <input type="text" id="wings" class="res-selector">
                    <br>
                    <label for="carcPrice">Цена</label>
                    <input type="number" id="carcPrice" class="res-selector" min="0">
                    <br>
                    <input type="submit" class="res-selector" value="Добавить">
                </form>
            </div>
        </div>

        <div class="inside_block_wrapper" id="chasAddArea" hidden>
            <div class="infotab">
                <center><h3>Добавить шасси</h3></center>
                <form>
                    <label for="chModel">Модель</label>
                    <input type="text" id="chModel" class="res-selector">
                    <br>
                    <label for="chHeight">Высота</label>
                    <input type="number" id="chHeight" class="res-selector" min="100" max="500">
                    <br>
                    <label for="chWidth">Ширина</label>
                    <input type="number" id="chWidth" class="res-selector" min="100" max="500">
                    <br>
                    <label for="chasPrice">Цена</label>
                    <input type="number" id="chasPrice" class="res-selector" min="0">
                    <br>
                    <input type="submit" class="res-selector" value="Добавить">
                </form>
            </div>
        </div>

        <div class="inside_block_wrapper" id="engAddArea" hidden>
            <div class="infotab">
                <center><h3>Добавить двигатель</h3></center>
                <form>
                    <label for="engModel">Модель</label>
                    <input type="text" id="engModel" class="res-selector">
                    <br>
                    <label for="cyclNum">Кол-во цилиндров</label>
                    <input type="number" id="cyclNum" class="res-selector" min="1">
                    <br>
                    <label for="capacity">Объём</label>
                    <input type="number" id="capacity" class="res-selector" min="0">
                    <br>
                    <label for="mass">Масса</label>
                    <input type="number" id="mass" class="res-selector" min="1">
                    <br>
                    <label for="stroke">Ход поршня</label>
                    <input type="number" id="stroke" class="res-selector" min="0">
                    <br>
                    <label for="engPrice">Цена</label>
                    <input type="number" id="engPrice" class="res-selector" min="0">
                    <br>
                    <input type="submit" class="res-selector" value="Добавить">
                </form>
            </div>
        </div>

        <div class="inside_block_wrapper" id="elecAddArea" hidden>
            <div class="infotab">
                <center><h3>Добавить электронику</h3></center>
                <form>
                    <label for="telemetry">Телеметрия</label>
                    <input type="text" id="telemetry" class="res-selector">
                    <br>
                    <label for="contrModel">Система контроля</label>
                    <input type="text" id="contrModel" class="res-selector">
                    <br>
                    <label for="elecPrice">Цена</label>
                    <input type="number" id="elecPrice" class="res-selector" min="0">
                    <br>
                    <input type="submit" class="res-selector" value="Добавить">
                </form>
            </div>
        </div>

</div>
</div>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/AddDetailsScript.js"> </script>

</body>
</html>