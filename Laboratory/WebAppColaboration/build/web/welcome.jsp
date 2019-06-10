<%-- 
    Document   : welcome
    Created on : 22/02/2019, 04:56:37 PM
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
        <%
            if (request.getParameter("name") != null) {
                out.print("<p>Bienvenido " + request.getParameter("name") + "</p>");
            }
        %>
    </body>
</html>
