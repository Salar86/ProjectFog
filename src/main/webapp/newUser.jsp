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

        <form action="NewUser" method="post">
            <label for="username">Email: </label>
            <br>
            <input type="text" id="username" name="username"/>
            <br>
            <label for="name">Fulde navn: </label>
            <br>
            <input type="text" id="name" name="name"/>
            <br>
            <label for="phone">Telefon nr: </label>
            <br>
            <input type="text" id="phone" name="phone"/>
            <br>
            <label for="password">Vælg et kodeord: </label>
            <br>
            <input type="password" id="password" name="password"/>
            <br>
            <label for="password">Bekræft kodeord: </label>
            <br>
            <input type="password" id="confirmpassword" name="confirmpassword"/><br><br>
            <input type="submit"  value="Opret bruger"/>
        </form>

    </jsp:body>
</t:pagetemplate>