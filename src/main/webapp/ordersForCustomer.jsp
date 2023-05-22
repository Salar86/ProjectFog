<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
        <jsp:attribute name="header">
             <input type="hidden" value="${sessionScope.user.userId}" name="userId">
         Bestillingsoversigt for ${sessionScope.user.email}
    </jsp:attribute>
    <jsp:attribute name="footer">
        Bestillingsstatus
    </jsp:attribute>

    <jsp:body>
        <input type="hidden" value="${sessionScope.user.userId}" name="userId">
        <c:forEach var="orders" items="${sessionScope.history}">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col" id="order_id">Order ID</th>
                <th scope="col" id="status">Status</th>
                <th scope="col" id="price">Price</th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="align-middle">${orders.orderId}</td>
                <td class="align-middle">${orders.status}</td>
                <td class="align-middle">${orders.price}</td>
            </tr>
            </tbody>
        </c:forEach>
        </table>
    </jsp:body>
</t:pagetemplate>