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
        <c:forEach  var="users" items="${sessionScope.users}">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col" id="email">Email</th>
                <th scope="col" id="fullname">Navn</th>
                <th scope="col" id="phone">Telefon</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th class="align-middle">${users.email}</th>
                <td class="align-middle">${users.fullname}</td>
                <td class="align-middle">${users.phone}</td>
            </tr>
            </tbody>
        </c:forEach>
        </table>

    </jsp:body>

</t:pagetemplate>