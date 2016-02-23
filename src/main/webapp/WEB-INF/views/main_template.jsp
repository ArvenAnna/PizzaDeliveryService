<%-- 
    Document   : main_template
    Created on : 20.02.2016, 15:53:17
    Author     : Dimitriy
--%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/js/jquery.js"></script>
        <script src="resources/js/ajaxAddToCart.js"></script> 
        <title>gggggggggggggggg</title>
    </head>
    <body>
        <tiles:insertAttribute name="menu"/>
        <tiles:insertAttribute name="basket"/>
        <tiles:insertAttribute name="grid_6-18"/>
        <tiles:insertAttribute name="footer"/>
    
    </body>
</html>
