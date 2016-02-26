<%-- 
    Document   : login
    Created on : 24.02.2016, 21:08:22
    Author     : Dimitriy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head> 
        <title>login</title>
    </head>
    <body>
        <form action="login" method="POST">
            <input id="username_or_email" name="username" type="text" />     <!-- Поле ввода имени пользователя -->
            <input id="password" name="password" type="password" />  <!-- Поле ввода пароля -->
            <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>   <!-- Флажок "запомнить меня" -->
            <input name="commit" type="submit" value="Sign In" />
            <%--<input type="hidden" name="spring-security-redirect" value="<c:out value="${param.r}" />">--%>
            <sec:csrfInput />
        </form>
        <c:if test='${login_error}'>
            login failed
        </c:if>
    </body>
</html>

