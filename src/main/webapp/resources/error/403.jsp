<%-- 
    Document   : 403
    Created on : 24.11.2015, 16:26:20
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="plugins/bootstrap.css" rel="stylesheet">
        <link href="plugins/bootstrap-theme.css" rel="stylesheet">
        <title>Ошибка 403 - доступ запрещен</title>
    </head>
    <body style="background-color:#fff">
        <div class="container">
            <div align="center">
                <table><tr><td>
                    <center><img id="403_image" src="403.png" alt="Ошибка 403."></center>
                    <div id="message" align=center>
                        <h1></h1>
                        Страница, которую вы хотели открыть...не открылась...<br>
                        Наверное, это связано с отсутствием прав доступа к этим файлам и каталогам сайта...<br><br>
                        Может быть, попробуете перейти на Главную страницу или войдите как Администратор.<br><br>
                        <a href="index">Главная страница</a>&nbsp|&nbsp<a href="admin">Вход Администратора</a><br><br>
                        (Ошибка 403 - доступ запрещен \ 403 Forbidden)
                    </div>
                    </td></tr></table>
            </div>
        </div>
    </body>
</html>
