/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $(".addPizza").on('click', function (event) {
        var button = $(event.target);
        var buttonValue = button.val();
        var obj = {id: buttonValue};
        var pobj = JSON.stringify(obj);

        $.ajax({
            type: "POST",
            url: "addpizza/" + buttonValue,
            contentType: "application/json",
            data: pobj,
            success: function (data) {
                alert(data);
                var orderJson = data;
                var a = JSON.stringify(orderJson, "", 4);
                //alert(orderJson[]);
                //alert(data['order']['details'].length);
                alert(a);
                var pizzasList = data['order']['details'];
                //alert(pizzasList);
                var pizza = data["order"]["details"][0]["pizza"]["name"];
                var pizzaStr = pizza.toString();
                alert(pizzaStr);
                $("#divbasket").load("resources/htmlTemplates/basket.html");
                $("#order").live("click", function () {
                    alert($("#pizza1").text());
                    
                });
                var cartElements = document.getElementsByClassName("cart");
                alert(cartElements[0]);
                //cartElements[0].innerHTML = pizzaStr;
                //var parseOrderJson = JSON.parse(orderJson);

                //alert("jopa");

                //alert(JSON.stringify(data));
                //var j = JSON.parse(data);
                //alert(data['name']);
//                    var f = JSON.stringify(data);
//                    alert(JSON.stringify(data));
//                    alert(JSON.parse(data));

            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function (data) {
            }
        });
        var elements = document.getElementsByClassName(buttonValue);

        cartElements[0].innerHTML = elements[0].innerHTML;
        cartElements[1].innerHTML = elements[2].innerHTML;

    });
});

