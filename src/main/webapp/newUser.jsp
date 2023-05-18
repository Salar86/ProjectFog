<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
            Quick-Tilbud brugeroprettelse
    </jsp:attribute>

    <jsp:body>

        <h4>Opret din Quick-Tilbud bruger her.</h4>
        <br>
        <br>

        <form action="newuser" method="post">
            <label for="email">Email: </label>
            <br>
            <input type="text" id="email" name="email"/>
            <br>
            <label for="fullname">Fulde navn: </label>
            <br>
            <input type="text" id="fullname" name="fullname"/>
            <br>
            <label for="phonenumber">Telefon nr: </label>
            <br>
            <input type="text" id="phonenumber" name="phonenumber"/>
            <br>
            <label for="password">Vælg et kodeord: </label>
            <br>
            <input type="password" id="password" name="password"/>
            <br>
            <label for="confirmpassword">Bekræft kodeord: </label>
            <br>
            <input type="password" id="confirmpassword" name="confirmpassword"/>
            <br>
            <br>
            <input type="submit"  value="Opret bruger"/>
            <br>
            ${requestScope.errormessage}
        </form>

    </jsp:body>
</t:pagetemplate>