<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
        <jsp:attribute name="header">
         Stykliste
    </jsp:attribute>
    <jsp:attribute name="footer">
        Stykliste
    </jsp:attribute>

    <jsp:body>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 33.3%" scope="col" id="order_id">Order ID</th>
                <th style="width: 33.3%" scope="col" id="status">Beskrivelse</th>
                <th style="width: 33.3%" scope="col" id="price">Antal</th>


            </tr>
            </thead>
            <tbody>
            <c:forEach var="itemList" items="${sessionScope.itemList}">
            <tr>
                <td>${itemList.orderId}</td>
                <td>${itemList.description}</td>
                <td>${itemList.quantity}</td>
            </tr>
            </tbody>
            </c:forEach>
        </table>
    </jsp:body>
</t:pagetemplate>