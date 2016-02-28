<%-- 
    Document   : menu
    Created on : 20.02.2016, 20:12:44
    Author     : Alex
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="row">
            <div class="col-lg-17">
                <ul class="nav navbar-nav">
                    <li><a href="homepage">Главная страница</a></li>
                    <li><a href="our_pizzas">Наши пиццы</a></li>
                    <li><a href="delivery">Доставка</a></li>
                    <li><a href="discount">Акции</a></li>
                    <li><a href="contacts">Контакты</a></li>
                        <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                        <li><a href="account">Мой профиль</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="adminmenu">Меню</a></li>
                        <li><a href="adminorders">Актуальные заказы</a></li>
                        </sec:authorize>
                </ul>
            </div>
            <div class="col-lg-7">
                <sec:authorize access="isAnonymous()">
                    <form action="login" method="POST" class="form-inline" style="margin-top: 10px;">
                        <input id="username_or_email" name="username" type="text" />     <!-- Поле ввода имени пользователя -->
                        <input id="password" name="password" type="password" />  <!-- Поле ввода пароля -->
                        <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>   <!-- Флажок "запомнить меня" -->
                        <input name="commit" type="submit" value="Sign In" />
                        <sec:csrfInput />
                    </form>
                    <c:if test='${login_error}'>
                        <span style="color: red; font-weight: bold;">login failed</span>
                    </c:if>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <form action="logout" method="POST" style="margin-left: 280px; margin-top: 10px;">
                        <input type="submit" value="Log out"/>
                        <sec:csrfInput />
                    </form>
                </sec:authorize>
            </div>
        </div>
    </nav>	
</div> 

