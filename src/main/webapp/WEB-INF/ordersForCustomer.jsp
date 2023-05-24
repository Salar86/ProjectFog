<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
        <jsp:attribute name="header">
         Bestillingsoversigt
    </jsp:attribute>
    <jsp:attribute name="footer">
        Bestillingsstatus
    </jsp:attribute>

    <jsp:body>
            <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 25%" scope="col" id="order_id">Order ID</th>
                <th style="width: 25%" scope="col" id="status">Status</th>
                <th style="width: 25%" scope="col" id="price">Pris</th>
                <th style="width: 25%" scope="col" id="buy">Køb</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="orders" items="${sessionScope.history}">
            <tr>
                <td>${orders.orderId}</td>
                <td>${orders.status}</td>
                <td>${orders.price}</td>
                <td><c:if test="${orders.status == 'TILBUD AFGIVET'}">
                    <form action="showordersforadmin" method="post">
                    <input type="submit" value="Køb">
                    <input type="hidden" value="${orders.orderId}" name="orderId">
                </form>
                </c:if>
                </td>
            </tr>
            </tbody>
        </c:forEach>
        </table>
    </jsp:body>
</t:pagetemplate>