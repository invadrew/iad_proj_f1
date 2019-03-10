<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/UserProfileStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Профиль пользователя</title>
</head>
<body>
<div class="grid-container">
   <jsp:include page="Header.jsp"/>
    <div class="MainArea">
        <div class="UserPhotoArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <div class="pic-container">
                        <img src="/pictures/rogoVK.jpg">
                    </div>
                </div>
        </div>
        </div>
        <div class="UserInfoArea">
          <div class="inside_block_wrapper"  style="height: 95%">
            <div class="infotab" style="height: 93.5%">
                <center><h2>${nameSurname}</h2></center>
                <c:if test="${!team.equals('Нет команды')}">
                <c:url value="/team" var="uUrl">
                    <c:param name="id" value="${hisTeamId}"/>
                </c:url>
                <a class="redirHref" href="${uUrl}">
                    Команда: ${team}
                </a>
                </c:if>
                <c:if test="${team.equals('Нет команды')}">
                    Команда: ${team}
                </c:if>
                <br> <br>
                Должность: ${spec}
                <br>
                <br>
                   <%-- <c:if test="${(!currName.equals(user.login)) && currUserSpec.equals('MANAGER') && team.equals('Нет команды')}">
                        <center> <input type="button" class="res-selector" value="Позвать в команду"> </center>
                    </c:if> --%>
                    <br>
                    <br>
                <c:if test="${!(user.spec.toString().equals('RACER'))}">
                    <i>Статистика пользователя совпадает с командной</i>
                </c:if>
            </div>
          </div>
        </div>
        <div class="StatisticsArea">
            <c:if test="${user.spec.toString().equals('RACER')}">
            <div class="inside_block_wrapper">
                <div class="infotab" >
                    <center>
                        <h3>Статистика</h3> </center>
                    <table class="infotable" style="font-size: 18pt; text-align: left">
                        <tr><td>Количество очков в сзоне - ${pointsCount}</td></tr>
                        <tr><td>Количество заездов в сезоне - ${racingsCount}</td></tr>
                        <tr><td>Лучшее место в текущем сезоне - ${bestPlace}</td></tr>
                        <tr><td>Среднее место на финише за сезон - ${averagePlaceAtSeason}</td></tr>
                        <tr><td>Общее количество заездов - ${allRaceCount}</td></tr>
                        <tr><td>Средний результат на всех заездах - ${averagePlaceAtAll}</td></tr>
                        <tr><td>Лучшее время гонки - <fmt:formatDate value="${getBestTrackTime[0][0]}" pattern="HH:mm:ss" />
                        Трасса - ${getBestTrackTime[0][1]} </td></tr>
                        <tr><td>Выиграно кубков мира - ${cupsWon}</td></tr>
                        <tr><td>Участвовал в ${champCount} чемпионатах</td></tr>
                    </table>
                </div>
            </div>
            </c:if>
            <c:if test="${currName.equals(user.login)}">
            <div class="inside_block_wrapper">
                <div class="infotab" >
                    <center><h2>Мои действия</h2></center>
                    
                    <c:if test="${user.spec.toString().equals('MANAGER')}">
                        <h3>Создание команды</h3>
                        <c:if test="${currUserTeam != null}">
                            У вас уже есть своя команда
                        </c:if>
                        <c:if test="${currUserTeam == null}">
                            <form>
                                <label for="crNewTeam">Введите название </label>
                                <input type="text" class="res-selector" id="crNewTeam"/>
                                <input type="button" value="Отправить заявку" class="res-selector" onclick="sendTeamRequest(${currUserId})">
                                <br>
                                <label id="teamSent" hidden>Отправлено</label>
                                <label id="teamEmpty" hidden>Введите название</label>
                                <label id="teamBusy" hidden>Название занято</label>
                            </form>
                        </c:if>
                        <c:if test="${!team.equals('Нет команды')}">
                        <h3>Выдача прав на покупку</h3>
                        <c:if test="${currUserTeam != null}">
                        <c:if test="${constrsAndMechs.isEmpty()}">
                            В команде сейчас нет механиков и конструкторов без права на покупку
                        </c:if>
                        <c:if test="${!constrsAndMechs.isEmpty()}">
                        <label for="chooseToGiveBuy">Выберите конструктора или механика</label>
                        <select id="chooseToGiveBuy" class="res-selector">
                            <c:forEach items="${constrsAndMechs}" var="candidate">
                                <option value="${candidate[0]}"> ${candidate[1]} ${candidate[2]} </option>
                            </c:forEach>
                        </select>
                        <input type="button" class="res-selector" value="Выдать" onclick="giveBuyAbility()">
                        </c:if>
                        </c:if>
                        <c:if test="${currUserTeam == null}">
                            У вас пока нет команды
                        </c:if>
                        <h3>Состояние регистрации команды в гонке</h3>
                        <c:if test="${currRaceRegStatus == null}">
                            Вы ещё не посылали заявку на гонку
                        </c:if>
                        <c:if test="${currRaceRegStatus != null}">
                            <c:out value="${currRaceRegStatus}"/>
                        </c:if>
                        <h3>Запросы на вступление в команду</h3>
                            <table class="infotable" style="text-align: center; margin: 3%; font-size: 18pt">
                        <c:forEach items="${onReviewTeamMems}" var="member">
                            <tr id="tmRow${member[0]}">
                                <td>
                                    <c:url value="/profile" var="uUrl">
                                        <c:param name="id" value="${member[0]}"/>
                                    </c:url>
                                    <a class="redirHref" href="${uUrl}">
                                        ${member[1]}
                                    </a>
                                </td>
                                <td>${member[2]}</td>
                                <td><input type="button" value="Принять" class="res-selector" onclick="confirmTeamMember(${member[0]}, true)" /></td>
                                <td><input type="button" value="Отказать" class="res-selector" onclick="confirmTeamMember(${member[0]}, false)" /></td>
                            </tr>
                        </c:forEach>
                            </table>
                        <h3>Запросы на добавление деталей/болидов</h3>
                        <c:if test="${!carsToConfirm.isEmpty()}">
                            <h4>Болиды</h4>
                                <c:forEach items="${carsToConfirm}" var="car">
                                    <div id="carBlock${car.id}">
                                    ${car.label} ${car.model}
                                    <br>
                                    Каркас: ${carsCarc.get(carsToConfirm.indexOf(car)).rearWing}-${carsCarc.get(carsToConfirm.indexOf(car)).safetyArcs}-
                                     ${carsCarc.get(carsToConfirm.indexOf(car)).wings} ,
                                     Шасси: ${carsChass.get(carsToConfirm.indexOf(car)).model} (h:${carsChass.get(carsToConfirm.indexOf(car)).height},w:${carsChass.get(carsToConfirm.indexOf(car)).width})
                                    <br>
                                    Двигатель: ${carsEng.get(carsToConfirm.indexOf(car)).model} ${{carsEng.get(carsToConfirm.indexOf(car)).cyclinders}} цил.
                                    ${carsEng.get(carsToConfirm.indexOf(car)).capacity} л. ,
                                     Электроника: ${carsElec.get(carsToConfirm.indexOf(car)).telemetry} сист. контроля: ${carsElec.get(carsToConfirm.indexOf(car)).controlSystem}
                                    <br>
                                    <input type="text" placeholder="Комментарий" class="res-selector" id="carComm${car.id}"/>
                                    <input type="button" class="res-selector" value="Принять" onclick="confirmCar(${car.id}, true)">
                                    <input type="button" class="res-selector" value="Отказать" onclick="confirmCar(${car.id}, false)">
                                        <label hidden id="notEnoughCar${car.id}">Недостаточно денег</label>
                                    <br>
                                    </div>
                                </c:forEach>
                        </c:if>
                        <c:if test="${!carcToConfirm.isEmpty()}">
                            <h4>Каркасы</h4>
                                <table class="infotable" style="text-align: center; margin: 3%; font-size: 14pt" border="1">
                                    <tr>
                                        <th>Спойлер</th>
                                        <th>Дуги безопасности</th>
                                        <th>Крылья</th>
                                        <th>Цена</th>
                                        <th>Комментарий</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                 <c:forEach items="${carcToConfirm}" var="carc">
                                     <input type="hidden" value="${carc.price}" id="carcPrice${carc.id}"/>
                                    <tr>
                                        <td>${carc.rearWing}</td>
                                        <td>${carc.safetyArcs}</td>
                                        <td>${carc.wings}</td>
                                        <td>${carc.price}</td>
                                        <td><input type="text" id="carcComm${carc.id}" class="res-selector" placeholder="Комментарий"/> </td>
                                        <td><input type="button" value="Принять" class="res-selector" onclick="confirmDetailCarc(true,${carc.id},${budget})"/></td>
                                        <td><input type="button" value="Отказать" class="res-selector" onclick="confirmDetailCarc(false,${carc.id},${budget})"/></td>
                                    </tr>
                                     <br>
                                     <label hidden id="carcNotE${carc.id}">Недостаточно денег</label>
                                </c:forEach>
                                </table>
                        </c:if>
                        <c:if test="${!chassToConfirm.isEmpty()}">
                            <h4>Шасси</h4>
                             <table class="infotable" style="text-align: center; margin: 3%; font-size: 14pt" border="1">
                                <tr>
                                <th>Модель</th>
                                <th>Высота</th>
                                <th>Ширина</th>
                                <th>Цена</th>
                                    <th>Комментарий</th>
                                <th></th>
                                <th></th>
                                </tr>
                                <c:forEach items="${chassToConfirm}" var="chass">
                                    <input type="hidden" value="${chass.price}" id="chassPrice${chass.id}"/>
                                <tr>
                                    <td>${chass.model}</td>
                                    <td>${chass.height}</td>
                                    <td>${chass.width}</td>
                                    <td>${chass.price}</td>
                                    <td><input type="text" id="chassComm${chass.id}" class="res-selector" placeholder="Комментарий"/> </td>
                                    <td><input type="button" value="Принять" class="res-selector" onclick="confirmDetailChass(true,${chass.id},${budget})"/></td>
                                    <td><input type="button" value="Отказать" class="res-selector" onclick="confirmDetailChass(false,${chass.id},${budget})"/></td>
                                </tr>
                                    <br>
                                    <label hidden id="chassNotE${chass.id}">Недостаточно денег</label>
                                </c:forEach>
                             </table>
                        </c:if>
                        <c:if test="${!engToConfirm.isEmpty()}">
                            <h4>Двигатели</h4>
                        <table class="infotable" style="text-align: center; margin: 3%; font-size: 14pt" border="1">
                                <tr>
                                    <th>Модель</th>
                                    <th>Цилиндров</th>
                                    <th>Объем</th>
                                    <th>Масса</th>
                                    <th>Ход поршня</th>
                                    <th>Цена</th>
                                    <th>Комментарий</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <c:forEach items="${engToConfirm}" var="eng">
                                    <input type="hidden" value="${eng.price}" id="engPrice${eng.id}"/>
                                    <tr>
                                        <td>${eng.model}</td>
                                        <td>${eng.cyclinders}</td>
                                        <td>${eng.capacity}</td>
                                        <td>${eng.mass}</td>
                                        <td>${eng.stroke}</td>
                                        <td>${eng.price}</td>
                                        <td><input type="text" id="engComm${eng.id}" class="res-selector" placeholder="Комментарий"/> </td>
                                        <td><input type="button" value="Принять" class="res-selector" onclick="confirmDetailEng(true,${eng.id},${budget})"/></td>
                                        <td><input type="button" value="Отказать" class="res-selector" onclick="confirmDetailEng(false,${eng.id},${budget})"/></td>
                                    </tr>
                                    <br>
                                    <label hidden id="engNotE${eng.id}">Недостаточно денег</label>
                                </c:forEach>
                        </table>
                        </c:if>
                        <c:if test="${!elecToConfirm.isEmpty()}">
                            <h4>Электроника</h4>
                    <table class="infotable" style="text-align: center; margin: 3%; font-size: 14pt" border="1">
                        <tr>
                            <th>Телеметрия</th>
                            <th>Сист. контроля</th>
                            <th>Цена</th>
                            <th>Комментарий</th>
                            <th></th>
                            <th></th>
                        </tr>
                                <c:forEach items="${elecToConfirm}" var="elec">
                                    <input type="hidden" value="${elec.price}" id="elecPrice${elec.id}"/>
                                    <tr>
                                        <td>${elec.telemetry}</td>
                                        <td>${elec.controlSystem}</td>
                                        <td>${elec.price}</td>
                                        <td><input type="text" id="elecComm${elec.id}" class="res-selector" placeholder="Комментарий"/> </td>
                                        <td><input type="button" value="Принять" class="res-selector" onclick="confirmDetailElec(true,${elec.id},${budget})"/></td>
                                        <td><input type="button" value="Отказать" class="res-selector" onclick="confirmDetailElec(false,${elec.id},${budget})"/></td>
                                    </tr>
                                    <br>
                                    <label hidden id="elecNotE${elec.id}">Недостаточно денег</label>
                                </c:forEach>
                    </table>
                        </c:if>
                        <h3>Запросы на получение права на покупку</h3>
                        <table class="infotable" style="text-align: center; margin: 3%;" border="1">
                        <c:forEach items="${constrsAndMechsRev}" var="rev">
                            <tr id="bpRow${rev[0]}">
                                <td>${rev[1]} ${rev[2]}</td>
                                <td>Комментарий пользователя: ${rev[4]}</td>
                                <td><input type="text" placeholder="Ваш комментарий" class="res-selector" id="bpSComm${rev[0]}"></td>
                                <td><input type="button" value="Подтвердить" class="res-selector" onclick="confirmBuyAbility(${rev[0]}, true)"/></td>
                                <td><input type="button" value="Отказать" class="res-selector" onclick="confirmBuyAbility(${rev[0]}, false)"/></td>
                            </tr>
                        </c:forEach>
                        </table>
                        </c:if>
                        <h3>Новости</h3>
                        <c:forEach items="${acceptedNames}" var="accepted">
                            Было принято название команды ${accepted[0]}. Комментарий: ${accepted[1]}
                            <br>
                        </c:forEach>
                        <c:forEach items="${refusedNames}" var="refused">
                            Было отклонено название команды ${refused[0]}. Комментарий ${refused[1]}
                            <br>
                        </c:forEach>
                    </c:if>
                    
                    <c:if test="${user.spec.toString().equals('MECHANIC') || user.spec.toString().equals('CONSTRUCTOR')}">
                        <c:if test="${!team.equals('Нет команды')}">
                        <h3>Право на добавление деталей/болидов</h3>
                        <c:if test="${ifCanBuy}">
                            У вас есть право на управление комплектующими без подтверждения
                        </c:if>
                        <c:if test="${!ifCanBuy}">
                            <form>
                                <input type="hidden" value="${user.login}" id="askButton"/>
                                <input type="text" placeholder="Комментарий" class="res-selector" id="bpComm"/>
                            <input type="button" value="Запросить право на покупку" class="res-selector" onclick="requestBuyAbility()" />
                            <label id="bpreqSent" hidden>Готово</label>
                            </form>
                        </c:if>
                        </c:if>
                        <h3>Новости</h3>
                            ${bpStatus}
                        <br>
                        <c:forEach items="${carsSt}" var="info">
                            ${info}
                            <br>
                        </c:forEach>
                        <c:forEach items="${carcaseSt}" var="info">
                            ${info}
                            <br>
                        </c:forEach>
                        <c:forEach items="${chassisSt}" var="info">
                            ${info}
                            <br>
                        </c:forEach>
                        <c:forEach items="${engineSt}" var="info">
                            ${info}
                            <br>
                        </c:forEach>
                        <c:forEach items="${electronicsSt}" var="info">
                            ${info}
                            <br>
                        </c:forEach>
                        <br>
                        ${teamMess}
                    </c:if>

                    <c:if test="${user.spec.toString().equals('RACER')}">
                        <h3>Новости</h3>
                        ${teamMess}
                    </c:if>

                </div>
            </div>
            </c:if>
        </div>
        <div class="UserAchArea">
            <div class="inside_block_wrapper" style="height: 95%">
               <div class="infotab" style="height: 93.5%">
                   <center><h3>Достижения</h3></center>
                   <c:if test="${user.spec.toString().equals('RACER')}">
                       <c:if test="${getRacerProfileTable == null}">
                           <h4>Данных пока нет...</h4>
                       </c:if>
                       <c:if test="${getRacerProfileTable != null}">
                           <h4>Чемпионаты</h4>
                   <table class="infotable" border="1" style="font-size: 18pt; text-align: left">
                       <c:forEach items="${getRacerProfileTable}" var="result">
                           <tr>
                               <td><c:out value="${result[1]}"/> <c:out value="${result[0]}"/></td>
                               <td><c:out value="${result[2]}"/> место</td>
                           </tr>
                       </c:forEach>
                   </table>
                   </c:if>
                   </c:if>
                   <c:if test="${!(user.spec.toString().equals('RACER'))}">
                       <c:if test="${ach == null}">
                           <h4>Данных пока нет...</h4>
                       </c:if>
                       <c:if test="${ach != null}">
                           <h4>Кубок конструкторов</h4>
                           <table class="infotable" border="1" style="font-size: 18pt; text-align: left">
                               <c:forEach items="${ach}" var="res">
                                   <tr>
                                       <td>Сезон <c:out value="${res[0]}"/>: место <c:out value="${res[1]}"/></td>
                               </c:forEach>
                           </table>
                       </c:if>
                   </c:if>
               </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/ProfileScript.js"> </script>
</body>
</html>