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
        //alert(buttonValue);
  
        var elements = document.getElementsByClassName(buttonValue);
        var cartElements = document.getElementsByClassName("cart");
        cartElements[0].innerHTML = elements[0].innerHTML;
        cartElements[1].innerHTML = elements[2].innerHTML;

        $.ajax({
            type: "POST",
            url: "addpizza",
            contentType: "application/json",
            data: pobj,
            success: function (data) {
                //alert(data);
                //alert(data.toString)
                //var f = JSON.parse(data);
                //alert(f[0]);

            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function (data) {
            }

        });
        return false;
    });
});

