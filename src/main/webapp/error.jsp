<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="true" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Der er sket en fejl!
    </jsp:attribute>

    <jsp:attribute name="footer">
            Error page
    </jsp:attribute>

    <jsp:body>

        <c:if test="${pageContext.errorData.statusCode == 404 }">
            <p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
        </c:if>

        <c:if test="${pageContext.errorData.statusCode == 500 }">
            <p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
            <p>En serverfejl er opstået.</p>
        </c:if>


        <c:if test="${requestScope.errormessage != null}">
            <p>${requestScope.errormessage}</p>
        </c:if>

        <c:if test="${requestScope.errormessage  == null}">
            <p>Der er sket en ukendt fejl
                !</p>
        </c:if>

        <p>Tilbage til <a href="index.jsp">forsiden</a>,
            eller prøv at <a href="login.jsp">logge</a> ind igen.</p>

    </jsp:body>
</t:pagetemplate>