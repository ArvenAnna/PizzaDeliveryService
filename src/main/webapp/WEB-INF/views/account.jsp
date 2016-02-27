<%-- 
    Document   : account
    Created on : 27.02.2016, 18:18:56
    Author     : Dimitriy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-18" style="top: 40px;">
        
        Здравствуйте,<c:out value="${customer.name}"/>!
        Ваши контактные данные:
        телефон: <c:out value="${customer.tel}"/>
        адрес: ${customer.address.city}
        <c:out value="${customer.card.sum}"/>
        
        <c:out value="${customer.address.apartment}"/>
        <c:out value="${customer.address.street}"/>
        <c:out value="${customer.address.house}"/>
         
 
    </div>
</div>
