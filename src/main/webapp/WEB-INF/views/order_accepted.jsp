<%-- 
    Document   : order_accepted
    Created on : 26.02.2016, 12:02:55
    Author     : Dimitriy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="order" value='<%= session.getAttribute("order")%>'/>


<c:choose>
    <c:when  test="${order==null}">
        <span class="" style="">В Вашей корзине <span class="" style="">0</span> товаров:</span></br>
    </c:when>
    <c:otherwise>
        dfcz
        <span class="" style="">В Вашей корзине <span class="" style="">${order.details.size()}</span> товаров:</span></br>
        <c:forEach var="detail" items="${order.details}">
            <div style="text-align: center;"> 
                <a href=""><span class="cart" style=""><b></b></span></br></a>
            </div>
            <img src="resources/foto/cart.jpg" alt="Pizza image" style="margin-left: 40px"/>
            <div style="text-align: center;"> 
                Цена: <span class="cart" style="">${detail.price}</span> грн.</br>
            </div>
            <div style="margin-left: 100px;">
                <button class="glyphicon glyphicon-plus"></button>
                <span class=""><b>1</b></span> шт.
                <button class="glyphicon glyphicon-minus"></button>
            </div>

        </c:forEach>
        <div style="text-align: center;"> 
            Всего к оплате: <span class="" style=""><b>${order.pureCost}</b></span> грн.</br>
        </div>
        <div class="text-center" style="">

        </div>
    </c:otherwise>
</c:choose>

