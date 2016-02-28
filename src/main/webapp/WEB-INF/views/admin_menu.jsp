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
        <c:forEach var="pizza" items="${adminPizzas}">
            ${pizza.id}
            ${pizza.name}
            ${pizza.price}
            ${pizza.pizzaType}
            ${pizza.description}
            ${pizza.foto}
        </c:forEach>
    </div>
</div>