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

        $.ajax({
            type: "GET",
            url: "addpizza/" + buttonValue + "",
            contentType: "application/json",
            data: pobj,
            success: function (data) {
                alert("jopa");
                alert(data);
                alert(JSON.stringify(data));
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
        var cartElements = document.getElementsByClassName("cart");
        cartElements[0].innerHTML = elements[0].innerHTML;
        cartElements[1].innerHTML = elements[2].innerHTML;

    });
});

