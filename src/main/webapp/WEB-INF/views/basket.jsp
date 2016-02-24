<%-- 
    Document   : basket
    Created on : 20.02.2016, 20:09:51
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="order" value='<%= session.getAttribute("order")%>'/>

<div class="" id="basket" style="width:300px; background-color: yellow; top: 60px; position: fixed; z-index: 101;">
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
                <button class="btn btn-success" >Оформить заказ</button>
            </div>
        </c:otherwise>
    </c:choose>



</div>
