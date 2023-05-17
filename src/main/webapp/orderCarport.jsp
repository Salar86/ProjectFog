<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
            Quick-Tilbud carportbestilling
    </jsp:attribute>

    <jsp:body>

        <h4>Bestil din carport her.</h4>
        <br>
        <br>

        <form action="OrderCarport" method="post">
            <label for="width">Carportens bredde: </label>
            <br>
            <select id="width" name="width">
            <option value="240 cm">240 CM</option>
            <option value="270 cm">270 CM</option>
            <option value="300 cm">300 CM</option>
            <option value="330 cm">330 CM</option>
            <option value="360 cm">360 CM</option>
            <option value="390 cm">390 CM</option>
            <option value="420 cm">420 CM</option>
            <option value="450 cm">450 CM</option>
            <option value="480 cm">480 CM</option>
            <option value="510 cm">510 CM</option>
            <option value="540 cm">540 CM</option>
            <option value="570 cm">570 CM</option>
            <option value="600 cm">600 CM</option>
            </select>
            <br>
            <label for="length">Carportens længde: </label>
            <br>
            <select id="length" name="length">
                <option value="240 cm">240 CM</option>
                <option value="270 cm">270 CM</option>
                <option value="300 cm">300 CM</option>
                <option value="330 cm">330 CM</option>
                <option value="360 cm">360 CM</option>
                <option value="390 cm">390 CM</option>
                <option value="420 cm">420 CM</option>
                <option value="450 cm">450 CM</option>
                <option value="480 cm">480 CM</option>
                <option value="510 cm">510 CM</option>
                <option value="540 cm">540 CM</option>
                <option value="570 cm">570 CM</option>
                <option value="600 cm">600 CM</option>
                <option value="630 cm">630 CM</option>
                <option value="660 cm">660 CM</option>
                <option value="690 cm">690 CM</option>
                <option value="720 cm">720 CM</option>
                <option value="750 cm">750 CM</option>
                <option value="780 cm">780 CM</option>
            </select>
            <br>
            <br>
            <input type="submit"  value="Send bestilling"/>
            <br>
        </form>

    </jsp:body>
</t:pagetemplate>