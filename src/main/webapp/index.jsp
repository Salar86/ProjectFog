<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         <h5>Lad os tegne din carport drøm!</h5>
        <h6>bestil et Quick-Byg tilbud gennem vores service.</h6>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Quick-Byg Tilbud Service
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of "${sessionScope.user.role}".</p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>Har du allerede en bruger? <a
                    href="login.jsp">Login</a></p>
            <p>Eller du kan oprette en <a
                    href="newUser.jsp">her</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>