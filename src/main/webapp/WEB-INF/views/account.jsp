<%-- 
    Document   : account
    Created on : 27.02.2016, 18:18:56
    Author     : Dimitriy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-18" style="top: 40px;">

        <div class="row" style="">
            <div class="col-lg-10">
                <h4>Здравствуйте, ${customer.name}!</h4>
                <h5>На Вашей бонусной карте <b>${customer.card.sum}</b> грн.</h5>
                <c:if test="${!orders.isEmpty()}">
                    <b>Ваши текущие заказы:</b><br>

                    <c:forEach var="order" items="${orders}">
                        Номер заказа: ${order.id}
                        <form action="${path}/app/customer/cancelorder" method="POST">
                            <input type="hidden" name = "orderId" value="${order.id}"/>
                            <button type="submit" name ="cancel" class="btn btn-danger">Отменить заказ</button>
                            <sec:csrfInput />
                        </form>
                        <ol>

                            <c:forEach var="detail" items="${order.details}">
                                <li>
                                    Пицца ${detail.pizza.name}, ${detail.pizza.pizzaType}<br>
                                    Цена: ${detail.price}
                                </li>
                            </c:forEach>

                        </ol>

                        Дата заказа: ${order.date}<br>
                        Статус заказа: ${order.status}<br>
                        Сумма: ${order.pureCost}<br>
                        Сумма к оплате:  ${order.rateCost}<br>


                        <form action="${path}/app/customer/continueshoping" method="POST">
                            <input type="hidden" name = "orderId" value="${order.id}"/>
                            <button type="submit" name ="continue" class="btn btn-success">Продолжить покупки</button>
                            <sec:csrfInput />
                        </form>

                    </c:forEach>
                </c:if>
            </div>
            <div class="col-lg-14">
                <h5>Ваши контактные данные:</h5>
                <ol>
                    <li>
                        <span>телефон: <span>${customer.tel}</span></span><a id="tel" href=""> Изменить</a>
                        <table class="table" style="display: none">
                            <tbody>
                                <tr>
                                    <td>Измените телефон:</td>
                                    <td><input type="text" size="40" value="${customer.tel}"></td>
                                    <td><button type="submit" class="accept btn btn-success glyphicon glyphicon-ok" value=""></button></td>
                                </tr>
                            </tbody>
                        </table>
                    </li>
                    <li>
                        <span>адрес: <span>${customer.address.city}</span>, <span>${customer.address.street}</span>, <span>${customer.address.house}</span>, <span>${customer.address.apartment}</span></span><a id="address" href=""> Изменить</a>
                        <table class="table" style="display: none">
                            <tbody>
                                <tr>
                                    <td>Измените город:</td>
                                    <td><input class="city" type="text" size="40" value="${customer.address.city}"></td>
                                </tr>
                                <tr>
                                    <td>Измените улицу:</td>
                                    <td><input class="street" type="text" size="40" value="${customer.address.street}"></td>
                                </tr>
                                <tr>
                                    <td>Измените дом:</td>
                                    <td><input class="house" type="text" size="40" value="${customer.address.house}"></td>
                                </tr>
                                <tr>
                                    <td>Измените квартиру:</td>
                                    <td><input class="appartment" type="text" size="40" value="${customer.address.apartment}"></td>
                                    <td><button type="submit" class="accept btn btn-success glyphicon glyphicon-ok" value=""></button></td>
                                </tr>
                            </tbody>
                        </table>
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#tel").on('click', changeTel);
        $("#address").on('click', changeAddress);
        $("#tel+table>tbody>tr>td>button").on("click", addTel);
        $("#address+table>tbody>tr>td>button").on("click", addAddress);
    });

    function addTel(event) {
        var newTel = $("#tel+table>tbody>tr>td>input").val();
        var prevEl = $("#tel").prev();
        var chPrevElem = $(prevEl).children();
        chPrevElem.each(function (i, nel) {
            var sp = $(nel);
            sp.text(newTel);
        });
        $("#tel+table").css({"display": "none"});
    }
    ;

    function addAddress(event) {
        var newCity = $("#address+table>tbody>tr>td>.city").val();
        var newStreet = $("#address+table>tbody>tr>td>.street").val();
        var newHouse = $("#address+table>tbody>tr>td>.house").val();
        var newAppartment = $("#address+table>tbody>tr>td>.appartment").val();
        var arrAddress = [newCity, newStreet, newHouse, newAppartment];
        var prevElAddress = $("#address").prev();
        var chPrevElemAddress = $(prevElAddress).children();
        chPrevElemAddress.each(function (i, nel) {
            var sp = $(nel);
            sp.text(arrAddress[i]);
        });
        $("#address+table").css({"display": "none"});
    }
    ;

    function changeTel(event) {
        $("#tel+table").css({"display": "table"});
        var tel = "463563053";
        ajaxTemplate("${path}/app/customer/changetel", tel);
        return false;
    }
    ;

    function changeAddress(event) {
        $("#address+table").css({"display": "table"});
        var address = JSON.stringify({city: "dfsdafasd", street: "hdgka"});
        ajaxTemplate("${path}/app/customer/changeaddress", address);
        return false;
    }
    ;

    function ajaxTemplate(url, data) {

        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers = {};
        headers[csrfHeader] = csrfToken;

        $.ajax({
            type: "POST",
            url: url,
            headers: headers,
            contentType: "application/json",
            data: data,
            success: function (data) {
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
        });
    }
    ;



</script>
