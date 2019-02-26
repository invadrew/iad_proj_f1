<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/TeamProfileStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Профиль команды</title>
</head>
<body>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="TeamNameArea">
        <br>
        <center><label style="padding-top: 3px" ><b>${team.name}</b></label></center>
    </div>
    <div class="MainArea">
        <div class="TeamInfoArea">
            <div class="inside_block_wrapper">
                <div class="infotab" style="font-size: 16pt">
                    <center><h3>Основная информация</h3></center>
                    Очков в текущем сезоне: ${seasonPoints}
                    <br> <br>
                    Лучшее место по итогам сезона: ${bestPlace}
                    <br> <br>
                    Гонщиков: ${racers}
                    <br>
                    Механиков: ${mechanics}
                    <br>
                    Конструкторов: ${constrs}
                    <br>
                    Менеджеров: ${managers}
                    <br><br>
                    <c:if test="${bestRacer != null}">
                        <c:url value="/profile" var="uUrl">
                            <c:param name="id" value="${bestRacer[0][3]}"/>
                        </c:url>
                        <a class="redirHref" href="${uUrl}">
                            Лучший гонщик: ${bestRacer[0][1]} ${bestRacer[0][2]}
                        </a>
                    </c:if>
                </div>
            </div>
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <center><h3>Информация о спонсорах</h3>
                    Бюджет: ${team.budget}
                        <br> <br>
                        <table class="infotable">
                            <tr>
                                <th> Спонсоры:</th>
                            </tr>
                            <c:if test="${sponsors != null}">
                                <c:forEach items="${sponsors}" var="sp">
                                <tr><td>
                                    <c:url value="/sponsor" var="uUrl">
                                        <c:param name="id" value="${sp[1]}"/>
                                    </c:url>
                                    <a class="redirHref" href="${uUrl}">
                                        <c:out value="${sp[0]}"/>
                                    </a>
                                </td></tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${sponsors == null}">
                                <tr><td> Пока нет спонсоров</td></tr>
                            </c:if>
                        </table>
                    </center>
                </div>
            </div>
        </div>
        <div class="TeamMembersTableArea">
            <div class="inside_block_wrapper" style="height: 95%">
                <div class="infotab" style="height: 93.5%">
                    <center><h3>Члены команды</h3>
                        <c:if test="${members == null}">
                            Пока никого нет...
                        </c:if>
                        <c:if test="${members != null}">
                    <table class="infotable" border="1">
                        <tr>
                            <th>№</th>
                            <th>Участник</th>
                            <th>Должность</th>
                        </tr>
                        <c:forEach items="${members}" var="member">
                            <tr>
                                <td>${members.indexOf(member)+1}</td>
                                <td>
                                <c:url value="/profile" var="uUrl">
                                    <c:param name="id" value="${member[3]}"/>
                                </c:url>
                                <a class="redirHref" href="${uUrl}">
                                        ${member[0]} ${member[1]}
                                </a>
                                </td>
                                <td>${member[2]}</td>
                            </tr>
                        </c:forEach>
                    </table>
                        </c:if>
                    </center>
                </div>
            </div>
        </div>
        <div class="TeamAchievArea">
          <div class="inside_block_wrapper" style="height: 95%">
              <div class="infotab" style="height: 93.5%">
                  <center>
                      <h3>Достижения кубка конструкторов</h3>
                      <c:if test="${achiv == null}">
                          Пока нет достижений...
                      </c:if>
                      <c:if test="${achiv != null}">
                      <table class="infotable" border="1">
                          <tr>
                              <th>Сезон</th>
                              <th>Место</th>
                          </tr>
                          <c:forEach items="${achiv}" var="ac">
                              <tr>
                                  <td><c:out value="${ac[0]}"/></td>
                                  <td><c:out value="${ac[1]}"/></td>
                              </tr>
                          </c:forEach>
                      </table>
                      </c:if>
                  </center>
              </div>
          </div>
        </div>
    </div>
</div>
</body>
</html>