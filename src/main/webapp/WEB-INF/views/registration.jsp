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
        <h4>Если Вы уже в теме - залогиньтесь, если нет - заполните форму ниже.</h4>
        <sec:authorize access="isAnonymous()">
            <spring:form method="POST" action="addcustomer" modelAttribute="customer" commandName="customer">

                <div class="row" style="margin-top: 50px">
                    <div class="col-lg-5">
                        <div>Введите Ваше имя:</div><br>
                        <div>Введите Ваш e-mail:</div><br>
                        <div>Введите Ваш пароль:</div><br> 
                        <div>Введите Ваш город:</div><br>
                        <div>Введите Вашу улицу:</div><br>
                        <div>Введите номер дома:</div><br>
                        <div>Введите номер квартиры:</div><br>
                        <div>Ваш номер телефона:</div><br>
                        
                    </div>  
                    <div class="col-lg-19">
                    <springnew:bind path="customer.name">
                        <input name="${status.expression}" type="text" /><br>
                    </springnew:bind>

                    <spring:errors path="account.username" cssClass="error"/>
                    <springnew:bind path="customer.account.username">
                        <input name="${status.expression}" type="text" style="margin-top: 10px;"/><br>  
                    </springnew:bind>


                    <springnew:bind path="customer.account.password">
                        <input name="${status.expression}" type="password" style="margin-top: 15px;"/><br>  
                    </springnew:bind>


                    <springnew:bind path="customer.address.city">
                        <input name="${status.expression}" type="text" style="margin-top: 15px;"/><br>
                    </springnew:bind>


                    <springnew:bind path="customer.address.street">
                        <input name="${status.expression}" type="text" style="margin-top: 15px;"/><br>
                    </springnew:bind>


                    <springnew:bind path="customer.address.house">
                         <input name="${status.expression}" type="text" style="margin-top: 15px;"/><br>
                    </springnew:bind>


                    <springnew:bind path="customer.address.apartment">
                         <input name="${status.expression}" type="text" style="margin-top: 15px;"/><br>
                    </springnew:bind>


                    <springnew:bind path="customer.tel">
                         <input name="${status.expression}" type="text" style="margin-top: 15px;"/><br><br>
                    </springnew:bind>

                    <spring:button name="submit" value="Next" class="glyphicon glyphicon-menu-right btn btn-primary">Next</spring:button>
                    <sec:csrfInput />
                    </div>
                </spring:form>


            </sec:authorize>
            <sec:authorize access="isAuthenticated()">

            </sec:authorize>
        </div>
    </div>


