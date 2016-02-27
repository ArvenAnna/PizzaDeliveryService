<%-- 
    Document   : registration
    Created on : 26.02.2016, 12:03:12
    Author     : Dimitriy
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="springnew"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-18" style="top: 40px;">
        <sec:authorize access="isAnonymous()">
            <spring:form method="POST" action="addcustomer" modelAttribute="customer" commandName="customer">


                <springnew:bind path="customer.name">
                    <input name="${status.expression}" type="text" />
                </springnew:bind>

                <spring:errors path="account.username" cssClass="error"/>
                <springnew:bind path="customer.account.username">
                    <input name="${status.expression}" type="text" />  
                </springnew:bind>


                <springnew:bind path="customer.account.password">
                    <input name="${status.expression}" type="password"/>  
                </springnew:bind>


                <springnew:bind path="customer.address.city">
                    <input name="${status.expression}" type="text"/>
                </springnew:bind>


                <springnew:bind path="customer.address.street">
                    <input name="${status.expression}" type="text"/>
                </springnew:bind>


                <springnew:bind path="customer.address.house">
                    <input name="${status.expression}" type="text"/>
                </springnew:bind>


                <springnew:bind path="customer.address.apartment">
                    <input name="${status.expression}" type="text"/>
                </springnew:bind>


                <springnew:bind path="customer.tel">
                    <input name="${status.expression}" type="text"/>
                </springnew:bind>

                    <spring:button name="submit" value="Next">Next</spring:button>
                <sec:csrfInput />
            </spring:form>

            If I want to login I have to do it now
            
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">

        </sec:authorize>
    </div>
</div>


