<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Users list</h1>
    <c:forEach items="${users}" var="user">
        <p>${user}</p>
    </c:forEach>
</body>
</html>
