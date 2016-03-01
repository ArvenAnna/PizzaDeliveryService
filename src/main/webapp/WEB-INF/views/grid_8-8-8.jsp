<%-- 
    Document   : grid_8-8-8
    Created on : 20.02.2016, 20:43:36
    Author     : Alex
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row" style="margin-top: 30px;">
        <c:forEach var="pizza" items="${somePizzas}"> <!-- Цикл по списку сообщений -->
            <div style="width: 300px; background-color: yellow; margin: 5%; float:left; ">
                <div >
                    <a href="">
                        <div style="text-align: center;">
                            <span style="font-weight: bold; color: black"><b><c:out value="${pizza.name}"/></b></span></br>
                            <span style="color: black"><c:out value="${pizza.pizzaType}"/></span>
                        </div>
                        <img src="<s:url value="/resources"/>/foto/<c:out value="${pizza.foto}"/>" class="img-rounded" width="300" height="280"/>
                    </a>
                    <span style="margin-left: 10px"><c:out value="${pizza.description}" /></span></br>
                    <div style="text-align: center;">
                        <span>Cтоимость: <b><c:out value="${pizza.price}" /></b> грн.</span>
                    </div>

                    <button id="order" name="${pizza.id}" class="addPizza btn btn-success" style="margin-left: 70px" value="${pizza.id}">Добавить в корзину</button>
                    <ul id="${pizza.id}" style="visibility: hidden; display: none">
                        <li class="${pizza.id}">${pizza.name}</li>
                        <li class="${pizza.id}">${pizza.foto}</li>
                        <li class="${pizza.id}">${pizza.price}</li>
                    </ul>
                </div>
            </div>
            <%-- <img src=
                  "<s:url value="/resources"/>/foto.JPG"/> --%>

        </c:forEach>  
    </div>


<script>

</script>

