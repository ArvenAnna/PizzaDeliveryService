/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
     
//        $("#addbutton").on('click', function (event) {
//            var button = $(event.target);
//            var buttonValue = button.val();
//            var obj = {id: buttonValue};
//            var pobj = JSON.stringify(obj);
//            alert(pobj);
//            alert('fdthtd');
// 
//            $.ajax({
//                type: "POST",
//                url: "addpizza",
//                contentType: "application/json",
//                data: pobj,
//                success: function (data) {
//                    alert(data);
////                    var f = JSON.stringify(data);
////                    alert(JSON.stringify(data));
////                    alert(JSON.parse(data));
//                    
//                },
//                error: function (xhr, ajaxOptions, thrownError) {
//                    alert(xhr.status);
//                            alert(thrownError);
//                },
//                complete: function (data) {
//                }
//
//            });
//            return false;
//        });

        $("#addbutton").on('click', function (event) {
            var button = $(event.target);
            var buttonValue = button.val();
            var obj = {id: buttonValue};
            var pobj = JSON.stringify(obj);
            alert(pobj);
            alert('fdthtd');
 
            $.ajax({
                type: "GET",
                url: "addpizza/"+buttonValue+"",
                contentType: "application/json",
                data: pobj,
                success: function (data) {
                    alert(data);
                    //var j = JSON.parse(data);
                    alert(data['cost']);
                    alert(data['rateCost']);
                    alert(data['exception']);
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
            return false;
        });
    });

