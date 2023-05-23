<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Bestillingsoversigt
    </jsp:attribute>

    <jsp:attribute name="footer">
        Bestillingsoversigt
    </jsp:attribute>

    <jsp:body>

            <table class="table table-striped table-hover">
            <tr>
                <th style="width: 14.2%" scope="col" id="orderId">OrdreID</th>
                <th style="width: 14.2%" scope="col" id="length">Længde</th>
                <th style="width: 14.2%" scope="col" id="width">Bredde</th>
                <th style="width: 14.2%" scope="col" id="price">Pris</th>
                <th style="width: 14.2%" scope="col" id="status">Status</th>
                <th style="width: 14.2%" scope="col" id="userId">Bruger</th>
                <th style="width: 14.2%" scope="col" id="delete">Slet Ordre</th>
            </tr>
            <tbody>
            <c:forEach  var="orders" items="${sessionScope.orders}">
            <tr>
                <td>${orders.orderId}</td>
                <td>${orders.length}</td>
                <td>${orders.width}</td>
                <td>${orders.price}</td>
                <td>${orders.status}</td>
                <td>${orders.userId}</td>
                <td><form action="showordersforadmin" method="post"><input type="submit" value="Slet"><input type="hidden" value="${orders.orderId}" name="orderId"></form></td>
            </tr>
            </tbody>
        </c:forEach>
        </table>
    </jsp:body>
</t:pagetemplate>