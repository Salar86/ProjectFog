<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til Quick-Byg Tilbudsservice
    </jsp:attribute>

    <jsp:attribute name="footer">
        Brugerområde
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user.role != 'user'}">
            <p>Velkommen til admin siden.</p>
        </c:if>

        <c:if test="${sessionScope.user.role == 'user'}">
            <p>Velkommen ${sessionScope.user.fullname}</p>




        </c:if>

    </jsp:body>

</t:pagetemplate>