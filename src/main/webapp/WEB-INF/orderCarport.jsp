<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

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

        <form action="ordercarport" method="post">
            <input type="hidden" value="${sessionScope.user.userId}" name="userId">
            <label for="width">Carportens bredde: </label>
            <br>
            <select id="width" name="width">
            <option value="240">240 CM</option>
            <option value="270">270 CM</option>
            <option value="300">300 CM</option>
            <option value="330">330 CM</option>
            <option value="360">360 CM</option>
            <option value="390">390 CM</option>
            <option value="420">420 CM</option>
            <option value="450">450 CM</option>
            <option value="480">480 CM</option>
            <option value="510">510 CM</option>
            <option value="540">540 CM</option>
            <option value="570">570 CM</option>
            <option value="600">600 CM</option>
            </select>
            <br>
            <label for="length">Carportens længde: </label>
            <br>
            <select id="length" name="length">
                <option value="240">240 CM</option>
                <option value="270">270 CM</option>
                <option value="300">300 CM</option>
                <option value="330">330 CM</option>
                <option value="360">360 CM</option>
                <option value="390">390 CM</option>
                <option value="420">420 CM</option>
                <option value="450">450 CM</option>
                <option value="480">480 CM</option>
                <option value="510">510 CM</option>
                <option value="540">540 CM</option>
                <option value="570">570 CM</option>
                <option value="600">600 CM</option>
                <option value="630">630 CM</option>
                <option value="660">660 CM</option>
                <option value="690">690 CM</option>
                <option value="720">720 CM</option>
                <option value="750">750 CM</option>
                <option value="780">780 CM</option>
            </select>
            <br>
            <br>
            <input type="submit"  value="Send bestilling"/>
            <br>
            ${requestScope.success}
        </form>

    </jsp:body>
</t:pagetemplate>