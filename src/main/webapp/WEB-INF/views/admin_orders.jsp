<%-- 
    Document   : admin_orders
    Created on : 28.02.2016, 15:42:30
    Author     : Dimitriy
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6"></div>
    <div class="col-lg-18" style="top: 40px;">

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>№</th>
                    <th>Дата</th>
                    <th>Статус</th>
                    <th>Клиент</th>
                    <th>Детали</th>
                    <th>Цена</th>
                    <th>К оплате</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${adminOrders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.date}</td>
                        <td>${order.status}</td>
                        <td>
                            <button class="customer_button glyphicon glyphicon-menu-down"> ${order.customer.name}</button>
                            <table class="table table-bordered" style="display: none">
                                <thead>
                                    <tr>
                                        <th>Телефон</th>
                                        <th>Город</th>
                                        <th>Улица</th>
                                        <th>Дом</th>
                                        <th>Квартира</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${order.customer.tel}</td>
                                        <td>${order.customer.address.city}</td>
                                        <td>${order.customer.address.street}</td>
                                        <td>${order.customer.address.house}</td>
                                        <td>${order.customer.address.apartment}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            <button class="list_pizza_button glyphicon glyphicon-menu-down" value=""> Детали</button>
                            <table class="table table-bordered" style="display: none">
                                <thead>
                                    <tr>
                                        <th>Пицца</th>
                                        <th>Цена</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="detail" items="${order.details}">
                                        <tr>
                                            <td>${detail.pizza.name}</td>
                                            <td>${detail.price}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                        <td>${order.pureCost}</td>
                        <td>${order.rateCost}</td>
                        <td>
                            <select name="status">
                                <c:forEach var="status" items="${statuses}">
                                    <option>${status}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><button class="accept btn btn-success glyphicon glyphicon-ok" value="${order.id}"></button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    $(document).ready(function () {
        $(".accept").on('click', setStatus);
        $(".customer_button").on('click', show_hideCustomerDetails);
        $(".list_pizza_button").on('click', show_hideOrderDetails);
    });
    function show_hideOrderDetails(event) {
        var button = $(event.target);
        var table = $(button).next();

        if (button.hasClass("glyphicon-menu-down")) {
            table.css({"display": "table"});
            button.removeClass("glyphicon-menu-down");
            button.addClass("glyphicon-menu-up");
        } else {
            table.css({"display": "none"});
            button.removeClass("glyphicon-menu-up");
            button.addClass("glyphicon-menu-down");
        }
        ;
    }
    ;
    function ajaxTemplate(url, data) {

        data = JSON.stringify(data);
        alert(data);

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


    function show_hideCustomerDetails(event) {
        var button = $(event.target);
        var table = $(button).next();
        var x = button.attr("class");
        if (x == "customer_button glyphicon glyphicon-menu-down") {
            table.removeAttr("style");
            button.attr("class", "customer_button glyphicon glyphicon-menu-up");
        } else {
            table.attr("style", "display: none");
            button.attr("class", "customer_button glyphicon glyphicon-menu-down");
        }
        ;
    }
    ;

    function setStatus(event) {
        var button = $(event.target);
        var id = button.val();
        alert(id);
        var select = button.prev();
        var status = select.val();
        var data = {orderId: id, status: status};
        ajaxTemplate("${path}/app/admin/order/changestatus", data);
        // insert var status in tag with order.status
        return false;
    }
    ;

</script>