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
                <th style="width: 12.5%" scope="col" id="orderId">OrdreID</th>
                <th style="width: 12.5%" scope="col" id="length">Længde</th>
                <th style="width: 12.5%" scope="col" id="width">Bredde</th>
                <th style="width: 12.5%" scope="col" id="price">Pris</th>
                <th style="width: 12.5%" scope="col" id="status">Status</th>
                <th style="width: 12.5%" scope="col" id="userId">Bruger</th>
                <th style="width: 12.5%" scope="col" id="delete">Slet ordre</th>
                <th style="width: 12.5%" scope="col" id="update">Generer stykliste</th>
            </tr>
            <tbody>
            <c:forEach  var="orders" items="${sessionScope.orders}">
            <tr>
                <td>${orders.orderId}</td>
                <td>${orders.length}</td>
                <td>${orders.width}</td>
                <td>${orders.price}<form action="modifyorders" method="get"><input type="hidden" value="${orders.orderId}" name="orderId">
                    <label for="priceupdate"></label>
                    <input type="text" id="priceupdate" name="priceupdate"/>
                        <input type="submit" value="Afsend tilbud"/>
                </form>
                </td>
                <td>${orders.status} <form action="modifyorders" method="post"><input type="hidden" value="${orders.orderId}" name="orderId">
                    <label for="statusupdate"></label>
                    <select id="statusupdate" name="statusupdate">
                    <option value="TILBUD AFGIVET">TILBUD AFGIVET</option>
                    <option value="TILBUD AFVIST">TILBUD AFVIST</option>
                    <option value="AFVENTER TILBUD">AFVENTER TILBUD</option>
                    <input type="submit" value="Opdater"/>
                    </select>
                </form>
                </td>
                <td>${orders.userId}</td>
                <td><form action="showordersforadmin" method="post"><input type="submit" value="Slet"><input type="hidden" value="${orders.orderId}" name="orderId"></form></td>
                <td>
                    <form action="generateitemlist" method="get">
                    <input type="submit" value="Stykliste">
                    <input type="hidden" value="${orders.orderId}" name="orderId">
                    <input type="hidden" value="${orders.width}" name="width">
                    <input type="hidden" value="${orders.length}" name="length">
                </form> </td>
            </tr>
            </tbody>
        </c:forEach>
        </table>
    </jsp:body>
</t:pagetemplate>