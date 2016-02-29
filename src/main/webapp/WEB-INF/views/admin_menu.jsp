<%-- 
    Document   : admin_menu
    Created on : 28.02.2016, 15:42:18
    Author     : Dimitriy
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-2" style="top: 40px;">
        <table class="table table-responsive table-bordered table-striped" style="width: 500px">
            <thead>
                <tr>
                    <th>№</th>
                    <th>Название пиццы</th>
                    <th>Цена</th>
                    <th>Тип пиццы</th>
                    <th>Описание пиццы</th>
                    <th>Фото</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pizza" items="${adminPizzas}">
                    <tr id="${pizza.id}" class="pizzarow">
                        <td>${pizza.id}</td>
                        <td>${pizza.name}</td>
                        <td>${pizza.price}</td>
                        <td>${pizza.pizzaType}</td>
                        <td>${pizza.description}</td>
                        <td>${pizza.foto}</td>
                        <td><button value="${pizza.id}" class="accept btn btn-success glyphicon glyphicon-ok"></button></td>
                        <td><button value="${pizza.id}" class="remove btn btn-danger glyphicon glyphicon-remove"></button></td>
                    </tr>
                </c:forEach>
                <tr id="new">
                    <td></td>
                    <td><input id="name" type="text" size="10"/></td>
                    <td><input id="price" type="text" size="3"/></td>
                    <td>
                        <select id="pizzaType">
                            <c:forEach var="type" items="${pizzaTypes}">
                                <option>${type}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" id="description"/></td>
                    <td><input type="file" id="foto"/></td>
                    <td><button id ="add" class="btn btn-success glyphicon glyphicon-ok"></button></td>
                    <td></td>
                </tr>
            <tbody>
        </table>
    </div>
</div>

<script>
    $(document).ready(function () {
        $(".accept").on('click', savePizzaChanges);
        $(".remove").on('click', removePizza);
        $("#add").on('click', addNewPizza);
        $(".pizzarow").on('click', showInputs);
        $("#foto").on('change', prepareLoad);

        var files = "";

        function prepareLoad(event)
        {
            files = event.target.files;
        }

        function processFileUpload()
        {
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var headers = {};
            headers[csrfHeader] = csrfToken;

            var oMyForm = new FormData();
            oMyForm.append("file", files[0]);
            $.ajax({
                dataType: 'json',
                url: "${path}/app/admin/pizza/upload",
                data: oMyForm,
                headers: headers,
                type: "POST",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false
            });
        }

        function addNewPizza(event) {
            var name = $('#name').val();
            var price = $('#price').val();
            var pizzaType = $('#pizzaType').val();
            var description = $('#description').val();
            if (files == "") {
                var foto = "";
            } else {
                var foto = files[0].name;
            }
            processFileUpload();
            pizza = {name: name, price: price, pizzaType: pizzaType, description: description, foto: foto};
            ajaxAdd("${path}/app/admin/pizza/addNewPizza", pizza);
            $('#new input').val("");
            return false;
        }
        ;

    });

    function showInputs(event) {
        var td = $(event.target);
        var tr = td.parent();
        var children = tr.children();
        var inner = children.children();
        if (!inner.is('input')) {
            children.each(function (i, elem) {
                if (i == 3) {
                    var text = $(elem).text();
                    var oldSelect = $('#pizzaType');
                    var select = "<select>" + oldSelect.html() + "</select>";
                    $(elem).html(select);
                    select = $(elem).children();
                    var options = select.children();
                    
                    options.each(function (i, nel) {
                        var opt = $(nel).text();
                        if (opt == text) {
                            select.val(opt);

                        }
                    });
                }
                if (i == 1 || i == 2 || i == 4 || i == 5) {
                    $(elem).html("<input value = '" + $(elem).text() + "'/>");
                }
            });
        }

    }
    ;

    function savePizzaChanges(event) {
        var button = $(event.target);
        var td = button.parent();
        var tr = td.parent();
        var children = tr.children();
        var input = children.children();
        if (input.is('input')) {
            var pizzaInfo = new Array();


            pizzaInfo[0] = tr.attr('id');

            input.each(function (i, elem) {
                if (i < children.length - 2 && $(elem)) {
                    pizzaInfo[i + 1] = $(elem).val();
                }
            });
            var pizza = {id: pizzaInfo[0], name: pizzaInfo[1], price: pizzaInfo[2], pizzaType: pizzaInfo[3], description: pizzaInfo[4], foto: pizzaInfo[5]};
            alert(JSON.stringify(pizza));
            ajaxUpdate("${path}/app/admin/pizza/savePizza", pizza);
            children.each(function (i, elem) {
                if (i > 0 && i < children.length - 2) {
                    $(elem).html("<td>" + pizzaInfo[i] + "</td>");
                }
            });

        }
        return false;
    }
    ;

    function removePizza(event) {
        var button = $(event.target);
        var id = button.val();
        $('#' + id).remove();
        ajaxTemplatePathVariable("${path}/app/admin/pizza/removePizza/" + id);
        return false;
    }
    ;

    function ajaxUpdate(url, data) {
        data = JSON.stringify(data);

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

    function ajaxAdd(url, data) {

        data = JSON.stringify(data);

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
                $('#new').before('<tr id="' + data['id'] + '" class="pizzarow">' +
                        '<td>' + data['id'] + '</td>' +
                        '<td>' + data['name'] + '</td>' +
                        '<td>' + data['price'] + '</td>' +
                        '<td>' + data['pizzaType'] + '</td>' +
                        '<td>' + data['description'] + '</td>' +
                        '<td>' + data['foto'] + '</td>' +
                        '<td><button value="' + data['id'] + '" class="accept btn btn-success glyphicon glyphicon-ok"></button></td>' +
                        '<td><button value="' + data['id'] + '" class="remove btn btn-danger glyphicon glyphicon-remove"></button></td></tr>');
                $(".accept").on('click', savePizzaChanges);
                $(".remove").on('click', removePizza);
                $(".pizzarow").on('click', showInputs);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
        });
    }
    ;

    function ajaxTemplatePathVariable(url) {

        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers = {};
        headers[csrfHeader] = csrfToken;

        $.ajax({
            type: "POST",
            url: url,
            headers: headers,
            contentType: "application/json",
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