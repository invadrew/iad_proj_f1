<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.rogo.inv.iadprojf1.service.UserService" %>
<%@ page import="com.rogo.inv.iadprojf1.entity.TeamMember" %>
<%@ page import="com.rogo.inv.iadprojf1.service.TeamMemberService" %>
<%@ page import="com.rogo.inv.iadprojf1.entity.Team" %>
<%@ page import="com.rogo.inv.iadprojf1.service.TeamService" %>
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
<%!
    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;
%>
<c:set var="thisUserTeamName" value="${team}"/>
<% Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = authentication.getName();
    pageContext.setAttribute("currName", name);

    TeamMember teamMember = teamMemberService.findByUserId(userService.findByLogin(name).getId());
    Team team = teamMember.getTeam();
    pageContext.setAttribute("currUserTeam", team.getId());
    pageContext.setAttribute("ifCanBuy", teamMember.getCanBuy());

    Team profileOwnerTeam = teamService.findByName(pageContext.getAttribute("thisUserTeamName").toString());
    pageContext.setAttribute("profileOwnerTeam", profileOwnerTeam);

%>
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
                    <c:if test="${!currName.equals(user.login) && user.spec.toString().equals('MANAGER') && profileOwnerTeam.equals(null)}">
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
                    <center><h3>Мои действия</h3></center>
                    
                    <c:if test="${user.spec.toString().equals('MANAGER')}">
                        <h4>Создание команды</h4>
                        <c:if test="${currUserTeam != null}">
                            У вас уже есть своя команда
                        </c:if>
                        <c:if test="${currUserTeam == null}">
                            <!-- TODO: add form to create team-->
                        </c:if>
                        <h4>Выдача прав на покупку</h4>
                        <!-- TODO: giving buy ability -->
                        <h4>Состояние регистрации команды в гонке</h4>
                        <!-- TODO: registration info-->
                        <h4>Запросы на вступление в команду</h4>
                        <!-- TODO: team join requests-->
                        <h4>Запросы на добавление деталей/болидов</h4>
                        <!-- TODO: confirm mechanics and constructors actions -->
                        <h4>Новости</h4>
                        <!-- TODO: here will be all results of different requests-->
                    </c:if>
                    
                    <c:if test="${user.spec.toString().equals('MECHANIC') || user.spec.toString().equals('CONSTRUCTOR')}">
                        <h4>Право на добавление деталей/болидов</h4>
                        <c:if test="${ifCanBuy}">
                            У вас есть право на управление комплектующими без подтверждения
                        </c:if>
                        <c:if test="${!ifCanBuy}">
                            <!-- TODO: send request to get buy ability-->
                        </c:if>
                        <h4>Новости</h4>
                        <!-- TODO: here will be all results of different requests-->
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
</body>
</html>