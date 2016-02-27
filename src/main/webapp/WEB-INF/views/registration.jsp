<%-- 
    Document   : registration
    Created on : 26.02.2016, 12:03:12
    Author     : Dimitriy
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-18" style="top: 40px;">
        <sec:authorize access="isAnonymous()">
            <form action="addcustomer" method="POST">
                <input name="name" type="text" />
                <input name="account.username" type="text" />    
                <input name="account.password" type="password" />  
                <input name="address.city" type="text" />
                <input name="address.street" type="text" />
                <input name="address.house" type="text" />
                <input name="address.apartment" type="text" />
                <input name="address.tel" type="text" />
                <input name="commit" type="submit" value="Next" />
                <sec:csrfInput />
            </form>
            <c:if test='${login_error}'>
                login failed
            </c:if>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <form action="acceptorder" method="POST">
                <input type="submit" value="Next"/>
                <sec:csrfInput />
            </form>
        </sec:authorize>
    </div>
</div>


