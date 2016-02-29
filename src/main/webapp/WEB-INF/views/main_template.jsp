<%-- 
    Document   : main_template
    Created on : 20.02.2016, 15:53:17
    Author     : Dimitriy
--%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <sec:csrfMetaTags />
        <link href="${path}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${path}/resources/js/jquery.js"></script>
        <title>PizzaDeliveryService</title>
    </head>
    <body>
        <tiles:insertAttribute name="menu"/>
        <tiles:insertAttribute name="basket"/>
        <tiles:insertAttribute name="grid_6-18"/>
        <tiles:insertAttribute name="footer"/>
    
    </body>
</html>

<script>
    $(document).ready(function () {
    $(".addPizza").on('click', {url: "${path}/app/order/addpizza", except: "В Вашей корзине слишком много пицц"}, editPizzaButtons);
    showOrder();
});

function showOrder() {
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;
    $("#submitOrder").removeAttr("disabled");
    $.ajax({
        type: "POST",
        url: "${path}/app/order/showorder",
        headers: headers,
        contentType: "application/json",
        success: function (data) {
            jsonProcessing(data);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        complete: function (data) {
        }
    });
}

function editPizzaButtons(event) {
    var button = $(event.target);
    var buttonValue = button.val();
    var url = event.data.url;
    //var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;
    $("#submitOrder").removeAttr("disabled");
    $.ajax({
        type: "POST",
        url: url + "/" + buttonValue + "",
        headers: headers,
        contentType: "application/json",
        success: function (data) {
            jsonProcessing(data);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        complete: function (data) {
        }
    });
}

function jsonProcessing(data) {
    //alert(JSON.stringify(data, " ", 4));

    if (data["exception"]) {
        $(".errorMessage").html("<span style='color: red; font-weight: bold'>" + event.data.except + "</span>");
    } else {
        $(".errorMessage").html('')
    }
    ;
    if (data['order']) {
        var idArr = [];
        for (i = 0; i < data['order']['details'].length; i++) {
            if (idArr[data['order']['details'][i]["pizza"]["id"]] !== undefined) {
                idArr[data['order']['details'][i]["pizza"]["id"]]["countPizza"]++;
            } else {
                idArr[data['order']['details'][i]["pizza"]["id"]] = {};
                idArr[data['order']['details'][i]["pizza"]["id"]]["countPizza"] = 1;
                idArr[data['order']['details'][i]["pizza"]["id"]]["namePizza"] = data['order']['details'][i]["pizza"]["name"];
                idArr[data['order']['details'][i]["pizza"]["id"]]["typePizza"] = data['order']['details'][i]["pizza"]["pizzaType"];
                idArr[data['order']['details'][i]["pizza"]["id"]]["pricePizza"] = data['order']['details'][i]["price"];
                idArr[data['order']['details'][i]["pizza"]["id"]]["pizzaFoto"] = data['order']['details'][i]["pizza"]["foto"];
            }
            ;
        }
        ;
    
        $("#divbasket").html("");
        for (i = 0; i < idArr.length; i++) {
            if (idArr[i] != null) {
                
                $("#divbasket").append("<div id='" + i + "'></div>");
                $("#" + i).append('<div class="" style="text-align: center;"><a href=""><span id="" class="cart" style="font-weight: bold; color: black">xxx</span><br></a></div><img src="" class="center-block" alt="Pizza image" width="160" height="150"/><div style="text-align: center;">Цена: <span class="cart" style="">0</span> грн.</br></div><div style="margin-left: 100px;"><button class="glyphicon glyphicon-plus" name="addpizza"></button><span class=""> <b>1</b> </span> шт.<button class="glyphicon glyphicon-minus" name="delpizza"></button></div><br>');
                $("#" + i + " > div > a > span").html("Пицца " + idArr[i]["namePizza"]);
                $("#" + i + " > div > .cart").html(idArr[i]["pricePizza"]);
                $("#" + i + " > div > span > b").html(idArr[i]["countPizza"]);
                $("#" + i + " > img").attr("src", "${path}/resources/foto/" + idArr[i]["pizzaFoto"]);
                $("#" + i + " > div > .glyphicon-plus").attr("value", i);
                $("#" + i + " > div > .glyphicon-minus").attr("value", i);
            }
            ;
        }
        ;
        $(".summ1").html(data['order']['rateCost']);
        $(".summ2").html(data['order']['pureCost']);
        $(".countItems").html(data['order']['details'].length);
        $(".glyphicon-plus").on('click', {url: "${path}/app/order/addpizza", except: "В Вашей корзине слишком много пицц"}, editPizzaButtons);
        $(".glyphicon-minus").on('click', {url: "${path}/app/order/delpizza", except: "В Вашей корзине уже нет пицц"}, editPizzaButtons);
    }
}
;
</script>
