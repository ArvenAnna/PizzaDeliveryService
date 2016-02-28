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
    <div class="col-lg-18" style="top: 40px;">
        <table class="table table-bordered table-striped">
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
                    <tr>
                        <td>${pizza.id}</td>
                        <td>${pizza.name}</td>
                        <td>${pizza.price}</td>
                        <td>${pizza.pizzaType}</td>
                        <td>${pizza.description}</td>
                        <td>${pizza.foto}</td>
                        <td><button name ="" class="btn btn-success glyphicon glyphicon-ok"></button></td>
                        <td><button name ="" class="btn btn-danger glyphicon glyphicon-remove"></button></td>
                    </tr>
                </c:forEach>
            <tbody>
        </table>
    </div>
</div>