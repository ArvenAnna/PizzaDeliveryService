<%-- 
    Document   : grid_8-8-8
    Created on : 20.02.2016, 20:43:36
    Author     : Alex
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-lg-8">
        <div>pizza1</div>
    </div>
    <div class="col-lg-8">
        <div>pizza1</div>
    </div>
    <div class="col-lg-8">
        <div>pizza1</div>
    </div>
</div>

<c:forEach var="pizza" items="${somePizzas}"> <!-- Цикл по списку сообщений -->
    
    <%-- <img src=
         "<s:url value="/resources"/>/foto.JPG"
         onError=
         "this.src=’<s:url value="/resources/images"/>/spitter_avatar.png’;"/> --%>

        <c:out value="${pizza.id}"/>
        <c:out value="${pizza.name}"/>
        <c:out value="${pizza.pizzaType}"/>
        <c:out value="${pizza.price}" />
        <c:out value="${pizza.description}"/>
        <c:out value="${pizza.foto}"/>
        
   
</c:forEach>