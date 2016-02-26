/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    //$("#links").load("/Main_Page #jq-p-Getting-Started li");
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
            data: pobj,
            success: function (data) {
                //alert(data["session_id"]);
                //alert(JSON.stringify(data));
                //$("#basket").load("resources/new.html #ss", "html");
                //alert('gg');
                //$("span").html(data["session_id"]);
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
//        var elements = document.getElementsByClassName(buttonValue);
//        var cartElements = document.getElementsByClassName("cart");
//        cartElements[0].innerHTML = elements[0].innerHTML;
//        cartElements[1].innerHTML = elements[2].innerHTML;

    });
});

