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
                    <c:param name="id" value="${member[3]}"/>
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
                <c:if test="${!(user.spec.toString().equals('RACER'))}">
                <br>
                    <c:if test="${(!currName.equals(user.login)) && currUserSpec.toString().equals('MANAGER') && team.equals('Нет команды')}">
                        FFFFFFFFFFFFFFF
                        <!-- TODO: button to invite usr to team-->
                    </c:if>
                    <br>
                    <br>
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
                        <!-- TODO: team join requests-->
                        <h3>Запросы на добавление деталей/болидов</h3>
                        <!-- TODO: confirm mechanics and constructors actions -->
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
                        <h3>Новости</h3>
                            ${bpStatus}
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