<%-- 
    Document   : error
    Created on : 26.02.2016, 12:02:41
    Author     : Dimitriy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:out value="${empty_order_saving}"/>
<div class="row" style="margin-top: 50px;">
    <div class="col-lg-6">

    </div>
    <div class="col-lg-18" style="top: 40px;">
        <c:if test='${empty_order_saving}'>
            empty order saving error!!!!!!!!!!!!!
        </c:if>
    </div>
</div>