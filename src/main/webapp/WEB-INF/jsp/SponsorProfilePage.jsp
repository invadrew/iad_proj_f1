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
                        <h3>${sponsoring[0]}</h3>
                        Суммарно потрачено: ${sponsoring[2]}
                        <br>
                        <form>
                            <label for="moneySp">Спонсировать</label>
                            <input type="number" id="moneySp" >
                            <input type="submit" class="res-selector" value="Перевести">
                        </form>
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
                                    <td> <fmt:formatDate value="${sp_info.date}" pattern="dd-MM-yyyy" /> </td>
                                    <td> ${sp_info.spMoney} </td>
                                </tr>
                                </c:forEach>
                            </table>
                        </center>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
        <div class="RightArea"></div>
        </div>
    </div>
</div>
</body>
</html>