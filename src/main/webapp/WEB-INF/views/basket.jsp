<%-- 
    Document   : basket
    Created on : 20.02.2016, 20:09:51
    Author     : Alex
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class=""  style="width:300px; height: 600px; background-color: yellow; top: 60px; position: fixed; z-index: 101; margin-top: 5px; overflow: auto;">
    <span class="" style="margin-top: 20px; margin-left: 10px">В Вашей корзине <span class="countItems" style="">0</span> товаров:</span><br><br>
    <span class="errorMessage" style=""></span>
    <div class="" id="basket" style="">
        <div id="divbasket">
            <img class="typeImage" src="${path}/resources/foto/cart.jpg" alt="Pizza image" style="margin-left: 40px"/><br><br>
        </div>
        <div class="summ" style="text-align: center;"> 
            Сумма заказа: <span class="summ1" style=""><b>0</b></span> грн.</br>
            Сумма со скидкой: <span class="summ2" style=""><b>0</b></span> грн.</br>
        </div>


    </div>

    <form action="${path}/app/order/acceptorder" method="POST" >
        <input id="submitOrder" disabled="disabled" type="submit" class="btn btn-success" style="margin-left: 80px" value="Оформить заказ"/>
        <sec:csrfInput />
    </form>

</div>
