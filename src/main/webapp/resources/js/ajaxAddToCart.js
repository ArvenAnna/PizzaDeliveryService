/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
$(".addPizza").on('click', {url:"addpizza", except: "В Вашей корзине слишком много пицц"}, addPizzaButton);
var event = {data: {url:"showorder"}};
addPizzaButton(event);
});

function addPizzaButton (event) {
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
            //data: pobj,
            success: function (data) {
                alert(JSON.stringify(data, " ", 4));
                
                if (data["exception"]) {
                    $(".errorMessage").html("<span style='color: red; font-weight: bold'>" + event.data.except + "</span>");
                } else {$(".errorMessage").html('')}
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
                alert(idArr);
                alert(JSON.stringify(idArr, " ", 4));
                $("#divbasket").html("");
                for (i = 0; i < idArr.length; i++) {
                        if (idArr[i] != null) {
                            
                            $("#divbasket").append("<div id='" + i +"'></div>");
                            $("#" + i).append('<div class="" style="text-align: center;"><a href=""><span id="" class="cart" style="">xxx</span><br></a></div><img src="" alt="Pizza image" style="margin-left: 40px"/><div style="text-align: center;">Цена: <span class="cart" style="">0</span> грн.</br></div><div style="margin-left: 100px;"><button class="glyphicon glyphicon-plus" name="addpizza"></button><span class=""> <b>1</b> </span> шт.<button class="glyphicon glyphicon-minus" name="delpizza"></button></div>');
                            $("#" + i + " > div > a > span").html(idArr[i]["namePizza"]);
                            $("#" + i + " > div > .cart").html(idArr[i]["pricePizza"]);
                            $("#" + i + " > div > span > b").html(idArr[i]["countPizza"]);
                            $("#" + i + " > div > .glyphicon-plus").attr("value", i);
                            $("#" + i + " > div > .glyphicon-minus").attr("value", i);
                        };
                };
                $(".summ1").html(data['order']['rateCost']); 
                $(".summ2").html(data['order']['pureCost']);
                $(".countItems").html(data['order']['details'].length);
                $(".glyphicon-plus").on('click', {url:"addpizza", except: "В Вашей корзине слишком много пицц"}, addPizzaButton);
                $(".glyphicon-minus").on('click', {url:"delpizza", except: "В Вашей корзине уже нет пицц"}, addPizzaButton);
            } 
                },
                    
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function (data) {
            }

        });   
        }
