<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Brugeroversigt
    </jsp:attribute>

    <jsp:attribute name="footer">
        Brugeroversigt
    </jsp:attribute>

    <jsp:body>

            <table class="table table-striped table-hover">
            <tr>
                <th style="width: 25%" scope="col" id="email">Email</th>
                <th style="width: 25%" scope="col" id="fullname">Navn</th>
                <th style="width: 25%" scope="col" id="phone">Telefon</th>
                <th style="width: 25%" scope="col" id="delete">Slet bruger</th>
            </tr>
            <tbody>
            <c:forEach  var="users" items="${sessionScope.users}">
            <tr>
                <td>${users.email}</td>
                <td>${users.fullname}</td>
                <td>${users.phonenumber}</td>
                <td><form action="showusers" method="post"><input type="submit" value="Slet"><input type="hidden" value="${users.userId}" name="userId"></form></td>
            </tr>
            </tbody>
        </c:forEach>
        </table>
    </jsp:body>
</t:pagetemplate>