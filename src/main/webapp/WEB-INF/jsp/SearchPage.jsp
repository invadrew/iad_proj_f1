<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/SearchPageStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Регистрация на гонку</title>
</head>
<body>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="MainArea">
        <div class="inside_block_wrapper">
            <div class="infotab">
                <h3 style="text-align: center"> Результаты поиска </h3>
            </div>
            <c:if test="${!(users.isEmpty())}">
            <div class="infotab">
                <h4 style="text-align: center">Члены команд</h4>
                <c:forEach items="${users}" var="user">
                    <c:url value="/profile" var="uUrl">
                        <c:param name="id" value="${user.userId}"/>
                    </c:url>
                <a class="redirHref" href="${uUrl}">
                    <label style="float: left; margin-left: 15%" class="redirHref"> ${user.name} ${user.surname} </label>
                </a>
                    <br> <br>
                </c:forEach>
            </div>
            </c:if>
            <c:if test="${!(sponsors.isEmpty())}">
                <div class="infotab">
                    <h4 style="text-align: center">Спонсоры</h4>
                    <c:forEach items="${sponsors}" var="sponsor">
                            <label style="float: left; margin-left: 15%" class="redirHref"> ${sponsor.name} </label>
                        <br> <br>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${!(teams.isEmpty())}">
            <div class="infotab">
                <h4 style="text-align: center">Команды</h4>
                <c:forEach items="${teams}" var="team">
                    <c:url value="/team" var="uUrl">
                        <c:param name="id" value="${team.id}"/>
                    </c:url>
                    <a class="redirHref" href="${uUrl}">
                        <label style="float: left; margin-left: 15%" class="redirHref"> ${team.name} </label>
                    </a>
                    <br> <br>
                </c:forEach>
            </div>
            </c:if>
            <c:if test="${(teams.isEmpty()) && (users.isEmpty()) && (sponsors.isEmpty())}">
                <div class="infotab">
                    <h4 style="text-align: center">Ничего не найдено!</h4>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>