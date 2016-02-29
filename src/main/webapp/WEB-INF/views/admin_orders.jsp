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

            <select name="status">
                <c:forEach var="status" items="${statuses}">
                    <option>${status}</option>
                </c:forEach>
            </select>
            <button class="accept" value="${order.id}">ghbvtybnm</button>
        </c:forEach>
    </div>
</div>

<script>
    $(document).ready(function () {
        $(".accept").on('click', setStatus);
    });

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
</script>