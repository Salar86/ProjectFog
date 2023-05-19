<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Quick-Byg Tilbud Service
    </jsp:attribute>

    <jsp:attribute name="footer">
        Quick-Byg Tilbud Service
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user != null}">
            <p>Velkommen "${sessionScope.user.username}".</p>

        </c:if>

        <c:if test="${sessionScope.user == null}">
            <br>
            <p>Har du allerede en bruger? <a
                    href="login.jsp">Log ind</a></p>
            <p>Ellers kan du oprette en <a
                    href="newUser.jsp">her</a></p>

        </c:if>

    </jsp:body>

</t:pagetemplate>