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
                        <form action="cancelorder" method="POST">
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


                        <form action="continueshoping" method="POST">
                            <input type="hidden" name = "orderId" value="${order.id}"/>
                            <button type="submit" name ="continue" class="btn btn-success">Продолжить покупки</button>
                            <sec:csrfInput />
                        </form>

                    </c:forEach>
                </c:if>
            </div>
            <div class="col-lg-10">
                <h5>Ваши контактные данные:</h5>
                <ol>
                    <li><span>телефон: ${customer.tel}</span><a id="tel" href=""> Изменить</a></li>
                    <li><span>адрес: ${customer.address.city}, ${customer.address.street}, ${customer.address.house}, ${customer.address.apartment}</span><a id= "address" href=""> Изменить</a></li>
                </ol>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#tel").on('click', changeTel);
        $("#address").on('click', changeAddress);
    });

    function changeTel(event) {
        var tel = "463563053";
        ajaxTemplate("changetel", tel);
        return false;
    }
    ;

    function changeAddress(event) {
        var address = JSON.stringify({city: "dfsdafasd", street: "hdgka"});
        ajaxTemplate("changeaddress", address);
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
