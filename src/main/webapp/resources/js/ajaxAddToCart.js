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

        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers = {};
        headers[csrfHeader] = csrfToken;
        $("#submitOrder").removeAttr("disabled");

        $.ajax({
            type: "POST",
            url: "addpizza/" + buttonValue + "",
            headers: headers,
            contentType: "application/json",
            //data: pobj,
            success: function (data) {

                alert(JSON.stringify(data, " ", 4));

//                if (data["exception"]) {
//                    $(".errorMessage").html("блблабла");
//                }
//                ;
//                alert(data['order']['details'].length);
//                var idArr = [];
//                for (i = 0; i < data['order']['details'].length; i++) {
//
//                    if (idArr[data['order']['details'][i]["pizza"]["id"]] !== undefined) {
//                        idArr[data['order']['details'][i]["pizza"]["id"]]++;
//                    } else {
//                        idArr[data['order']['details'][i]["pizza"]["id"]] = 1;
//                    }
//                    ;
//                }
//                ;
//                var count = 0;
//                for (i = 0; i < idArr.length; i++) {
//
//                    if (idArr[i] !== undefined) {
//                        count++;
//                        $("#divbasket").append("<div id='" + i + "'></div>");
//                        $("#" + i + "").load("resources/htmlTemplates/basket.html", "html");
//                        for (j = 0; j < data['order']['details'].length; i++) {
//                            if (data['order']['details'][j]["pizza"]["id"] === idArr[i]) {
//                                var pizzaName = data['order']['details'][j]["pizza"]["name"];
//                            };
//                        };
//                        $("#" + i + " > div > a > span").html(pizzaName);
//                    };
//                };


            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function (data) {
            }
        });

//        var elements = document.getElementsByClassName(buttonValue);
//
//        cartElements[0].innerHTML = elements[0].innerHTML;
//        cartElements[1].innerHTML = elements[2].innerHTML;


    });
});

