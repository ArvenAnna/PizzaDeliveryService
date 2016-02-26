<%-- 
    Document   : registration
    Created on : 26.02.2016, 12:03:12
    Author     : Dimitriy
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sec:authorize access="isAnonymous()">
    <form action="add_customer" method="POST">
        <input id="username_or_email" name="username" type="text" />     <!-- Поле ввода имени пользователя -->
        <input id="password" name="password" type="password" />  <!-- Поле ввода пароля -->
        ...
        <input name="commit" type="submit" value="Next" />
        <sec:csrfInput />
    </form>
    <c:if test='${login_error}'>
        login failed
    </c:if>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <form action="accept_order" method="POST">
        <input type="submit" value="Next"/>
        <sec:csrfInput />
    </form>
</sec:authorize>

