<%-- 
    Document   : order_accepted
    Created on : 26.02.2016, 12:02:55
    Author     : Dimitriy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-18" style="top: 40px;">
        <span class="" style=""><b>Вы заказали пицц: </b><span class="" style="">${order.details.size()}</span> шт.</span></br>
        <c:forEach var="detail" items="${order.details}">
            <hr/>
            Название: <span class="cart" style="">${detail.pizza.name}</span><br>
            Цена: <span class="cart" style="">${detail.price}</span> грн.<br>
            <hr/>
        </c:forEach>
        
            Сумма: <span class="" style=""><b>${order.pureCost}</b></span> грн.</br>
            <b>Всего к оплате: <span class="" style=""><b>${order.rateCost}</b></span> грн.</b></br>
        
    </div>
</div>




