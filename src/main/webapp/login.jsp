<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Quick-Tilbud Service
        <br>
    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>

    <jsp:body>

        <h3>Log ind her</h3>

        <form action="login" method="post">
            <label for="username">Email: </label>
            <br>
            <input type="text" id="username" name="username"/>
            <br>
            <label for="password">Kodeord: </label>
            <br>
            <input type="password" id="password" name="password"/>
            <br>
            <input type="submit"  value="Log in"/>
        </form>

    </jsp:body>
</t:pagetemplate>