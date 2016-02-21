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
        <c:forEach var="pizza" items="${somePizzas}"> <!-- Цикл по списку сообщений -->
            <div style="width: 300px; background-color: yellow; margin: 5%;">
                <a href="">
                    <div style="text-align: center;">
                        <span><b><c:out value="${pizza.name}"/></b></span></br>
                        <span><c:out value="${pizza.pizzaType}"/></span>
                    </div>
                    <img src="<s:url value="/resources"/>/foto/<c:out value="${pizza.foto}"/>" class="img-rounded"/>
                </a>
                <span><c:out value="${pizza.description}" /></span></br>
                <div style="text-align: center;">
                    <span>Cтоимость: <b><c:out value="${pizza.price}" /></b></span>
                </div>
                <button class="btn btn-success" style="margin-left: 115px;">Заказать</button>
            </div>
            <%-- <img src=
                  "<s:url value="/resources"/>/foto.JPG"/> --%>

        </c:forEach>  
    </div>
    <div class="col-lg-8">
        <div>pizza1</div>
    </div>
    <div class="col-lg-8">
        <div>pizza1</div>
    </div>
</div>
<script>

</script>

