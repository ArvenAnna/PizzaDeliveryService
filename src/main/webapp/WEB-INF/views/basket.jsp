<%-- 
    Document   : basket
    Created on : 20.02.2016, 20:09:51
    Author     : Alex
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class=""  style="width:300px; height: 1000px; background-color: yellow; top: 60px; position: fixed; z-index: 101;">
    <span class="" style="">В Вашей корзине <span class="" style="">0</span> товаров:</span></br>
    <span class="errorMessage" style=""></span>
    <div class="" id="basket" style="">
        <div id="divbasket">
            <img class="typeImage" src="resources/foto/cart.jpg" alt="Pizza image" style="margin-left: 40px"/></br></br>
        </div>
        <div style="text-align: center;"> 
            Сумма заказа: <span class="" style=""><b>0</b></span> грн.</br>
            Сумма со скидкой: <span class="" style=""><b>0</b></span> грн.</br>
        </div>

    </div>

    <form action="accept_order" method="POST" >
        <input id="submitOrder" disabled="disabled" type="submit" class="btn btn-success"  value="Оформить заказ"/>
        <sec:csrfInput />
    </form>

</div>
