<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/SponsorProfileStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Профиль спонсора</title>
</head>
<body>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="MainArea">
        <div class="LeftArea"></div>
        <div class="SponsorPhotoArea">
        <div class="inside_block_wrapper" style="height: 95%">
            <div class="infotab" style="height: 93.5%">
                <div class="pic-container">
                    <img src="/pictures/MonsterEnergy.jpeg">
                </div>
            </div>
        </div>
        </div>
        <div class="SponsorInfoArea">
            <div class="inside_block_wrapper" style="height: 95%">
                <div class="infotab" style="height: 93.5%">
                <center><h3>${sponsor.name}</h3></center>
                    Бюджет: ${sponsor.budget}
                    <br> <br>
                    Спонсировано команд: ${teamCount}
                    <br> <br>
                    Суммарно потрачено: ${sumMoney}
                </div>
            </div>
        </div>
        <div class="SponsoringArea">
            <div class="inside_block_wrapper">
                <center><h3>Спонсирование</h3></center>
                <c:forEach items="${sponsorings}" var="sponsoring" >
                <div class="sptab">
                    <div class="sp-team-info">
                        <c:url value="/team" var="uUrl">
                            <c:param name="id" value="${sponsoring[1]}"/>
                        </c:url>
                        <a class="redirHref" href="${uUrl}">
                            <h3>${sponsoring[0]}</h3>
                        </a>
                        Суммарно потрачено: ${sponsoring[2]}
                        <br>
                       <% Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                       String name = authentication.getName();
                       pageContext.setAttribute("currName", name);
                       %>
                        <c:if test="${currName.equals(userName)}">
                        <form>
                            <input type="hidden" value="${sponsoring[1]}" id="teamId${sponsoring[1]}">
                            <input type="hidden" value="${sponsor.budget}" id="allMoney">
                            <input type="hidden" value="${sponsor.userId}" id="spId${sponsor.userId}">
                            <label for="moneySp${sponsoring[1]}">Спонсировать</label>
                            <input type="number" class="res-selector" id="moneySp${sponsoring[1]}" >
                            <input type="button" class="res-selector" value="Перевести" onclick="sendMoney(${sponsoring[1]}, ${sponsor.userId})">
                            <br>
                            <label id="notEnough${sponsoring[1]}" hidden> Недостаточно денег </label>
                            <label id="done" hidden> Успешно </label>
                        </form>
                        </c:if>
                    </div>
                    <div class="sp-team-story">
                        <center>
                            <table class="infotable" border="1">
                                <tr>
                                    <th> Дата </th>
                                    <th> Потрачено </th>
                                </tr>
                                <c:forEach items="${sponsoring[3]}" var="sp_info">
                                <tr>
                                    <c:if test="${sp_info.date != null}">
                                    <td>
                                    <fmt:formatDate value="${sp_info.date}" pattern="dd-MM-yyyy" />
                                    </td>
                                    </c:if>
                                        <c:if test="${sp_info.spMoney != 0.0}">
                                    <td>
                                            ${sp_info.spMoney}
                                    </td>
                                        </c:if>
                                </tr>
                                </c:forEach>
                            </table>
                        </center>
                    </div>
                </div>
                </c:forEach>
                    <c:if test="${currName.equals(userName)}">
                <div class="infotab">
                        <button class="res-selector" id="addTeamButtonOpener" onclick="openTeamAdd()">Добавить команду</button>
                    <br>
                        <div id="addTeamSpForm" hidden>
                            <form>
                                <label for="teamToSponse">Введите название команды</label>
                                <input type="text" id="teamToSponse" class="res-selector">
                                <input type="button" value="Подтвердить" class="res-selector" onclick="sendNewTeam(${sponsor.userId})">
                                <br> <label id="noTeam" hidden> Нет такой команды </label>
                            </form>
                        </div>
                </div>
                    </c:if>
            </div>
        </div>
        <div class="RightArea"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/SponsorsScript.js"> </script>
</body>
</html>