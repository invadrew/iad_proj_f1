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
            <div class="infotab">
                <c:forEach items="users" var="user">
                    ${user.name} ${user.surname}
                    <br> <br>
                </c:forEach>
            </div>
            <div class="infotab">
                <c:forEach items="teams" var="team">
                    ${team.name}
                    <br> <br>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>