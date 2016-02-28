<%-- 
    Document   : admin_orders
    Created on : 28.02.2016, 15:42:30
    Author     : Dimitriy
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-18" style="top: 40px;">
        <c:forEach var="order" items="${adminOrders}">
            ${order.id}
            ${order.date}
            ${order.status}
            ${order.customer.name}
            ${order.customer.tel}
            ${order.customer.address.city}
            ${order.customer.address.street}
            ${order.customer.address.house}
            ${order.customer.address.apartment}
            <c:forEach var="detail" items="${order.details}">
                ${detail.pizza.name}
                ${detail.price}
            </c:forEach>
            ${order.pureCost}
            ${order.rateCost}
        </c:forEach>
        <select name="status">
            <c:forEach var="status" items="${statuses}">
                <option>${status}</option>
            </c:forEach>
        </select>
    </div>
</div>
