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
            <spring:form method="POST" action="${path}/app/order/addcustomer" modelAttribute="customer" commandName="customer">

                <div class="row" style="margin-top: 50px">
                    <div class="col-lg-10">
                        <table class="table table-striped">
                            <tbody>
                                <tr>
                                    <td>Введите Ваше имя:</td>
                                    <td> 
                                        <springnew:bind path="customer.name">
                                            <input name="${status.expression}" type="text" onblur='checkFunction(this)'/>
                                        </springnew:bind>
                                    <td>
                                        <spring:errors path="account.username" cssClass="error"/>    
                                    </td>
                                    </td>
                                </tr>
                                <tr>   
                                    <td>Введите Ваш e-mail:</td>
                                    <td>
                                        <springnew:bind path="customer.account.username">
                                            <input name="${status.expression}" type="text"/> 
                                        </springnew:bind>
                                    </td>
                                </tr>
                                <tr>   
                                    <td>Введите Ваш пароль:</td>
                                    <td>
                                        <springnew:bind path="customer.account.password">
                                            <input name="${status.expression}" type="password"/> 
                                        </springnew:bind>
                                    </td>
                                </tr>
                                <tr>   
                                    <td>Введите Ваш город:</td>
                                    <td>
                                        <springnew:bind path="customer.address.city">
                                            <input name="${status.expression}" type="text"/>
                                        </springnew:bind>
                                    </td>
                                </tr>
                                <tr>   
                                    <td>Введите Вашу улицу:</td>   
                                    <td>
                                        <springnew:bind path="customer.address.street">
                                            <input name="${status.expression}" type="text"/>
                                        </springnew:bind>
                                    </td>
                                </tr>
                                <tr>   
                                    <td>Введите номер дома:</td>  
                                    <td>
                                        <springnew:bind path="customer.address.house">
                                            <input name="${status.expression}" type="text"/>
                                        </springnew:bind>
                                    </td>
                                </tr>
                                <tr>   
                                    <td>Введите номер квартиры:</td>  
                                    <td>
                                        <springnew:bind path="customer.address.apartment">
                                            <input name="${status.expression}" type="text"/>
                                        </springnew:bind>
                                    </td>
                                </tr>
                                <tr>   
                                    <td>Ваш номер телефона:</td>  
                                    <td>
                                        <springnew:bind path="customer.tel">
                                            <input name="${status.expression}" type="text"/>
                                        </springnew:bind>  
                                    </td>
                                </tr>
                                <tr>   
                                    <td>
                                        <spring:button name="submit" value="Next" class="glyphicon glyphicon-menu-right btn btn-primary">Next</spring:button>
                                        <sec:csrfInput />
                                    </td>  
                                </tr>
                            </tbody>
                        </table>
                    </div>  
                    <div class="col-lg-14">
                    </div>
                </spring:form>
            </sec:authorize>
        </div>
    </div>
    <script>
        
        function checkFunction(input){
            if ($(input).val()=='jopa'){
                $(input).css({"border-color":"green"});
            }
        };
    </script>


