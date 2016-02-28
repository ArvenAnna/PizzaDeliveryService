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
        <c:if test="${!orders.isEmpty()}">
            Ваши текущие заказы:

            <c:forEach var="order" items="${orders}">
                Номер заказа: ${order.id}
                <c:forEach var="detail" items="${order.details}">
                    Пицца ${detail.pizza.name}, ${detail.pizza.pizzaType}
                    Цена: ${detail.price}

                    ${detail.pizza.foto}

                </c:forEach>
                Дата заказа: ${order.date}
                Статус заказа: ${order.status}
                Сумма: ${order.pureCost}
                Сумма к оплате:  ${order.rateCost}

                <form action="cancelorder" method="POST">
                    <input type="hidden" name = "orderId" value="${order.id}"/>
                    <button type="submit" name ="cancel">Отменить заказ</button>
                    <sec:csrfInput />
                </form>
                <form action="continueshoping" method="POST">
                    <input type="hidden" name = "orderId" value="${order.id}"/>
                    <button type="submit" name ="continue">Продолжить покупки</button>
                    <sec:csrfInput />
                </form>

            </c:forEach>
        </c:if>
        Здравствуйте, ${customer.name}!
        Ваши контактные данные:
        телефон: ${customer.tel}
        <a id="tel" href=""> Изменить</a>

        город: ${customer.address.city}, 
        адрес: ${customer.address.street}, ${customer.address.house}, ${customer.address.apartment} 
        <a id= "address" href=""> Изменить</a>
        сумма на бонусной карте: ${customer.card.sum}
  
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
    };
    
    function changeAddress(event) {
        var address = JSON.stringify({city:"dfsdafasd", street:"hdgka"});
        ajaxTemplate("changeaddress", address);
        return false;
    };
    
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
    };
    
    

</script>
