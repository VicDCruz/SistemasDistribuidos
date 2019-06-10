<%-- 
    Document   : index
    Created on : 18/02/2019, 04:44:00 PM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Por favor de su opinion de los Servlets</h1>
        <form action="ServletInicial">
            Nombre <input type="text" name="nombre" value="Victor" /><br>
            Apellidos <input type="text" name="apellido" value="Cruz" /><br>
            Opinion que le merecen los Servlets<br>
            <input type="radio" name="opinion" value="mala" checked="checked" />Mala<br>
            <input type="radio" name="opinion" value="regular" />Regultar<br>
            <input type="radio" name="opinion" value="buena" />Buena<br>
            Comentarios<br>
            <textarea name="comentarios" rows="4" cols="20">
Desde mi punto de vista, los servlets son una tecnologia sorprendente.
            </textarea><br>
            <input type="submit" value="OK" />
        </form>
    </body>
</html>
