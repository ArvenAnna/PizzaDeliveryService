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

        $.ajax({
            type: "POST",
            url: "addpizza/" + buttonValue + "",
            headers: headers,
            contentType: "application/json",
            //data: pobj,
            success: function (data) {

                alert(JSON.stringify(data, " ", 4));
                
//                $("#divbasket").load("resources/htmlTemplates/basket.html", "html");
//                if (data["exception"]) {
//                    $(".errorMessage").html("блблабла");
//                };
//                var firstpizza = data['order']['details'][0]['pizza']['id'];
//                alert(firstpizza);
//                
//                for (i = 0, i < data['order']['details'].lenghts, i++) {
//                    var ipizza = data['order']['details'][i]['pizza']['id'];
//                    var icount = 1;
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

