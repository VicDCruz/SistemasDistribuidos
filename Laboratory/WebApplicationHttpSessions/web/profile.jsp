<%-- 
    Document   : profile
    Created on : 1/03/2019, 04:38:17 PM
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
            HttpSession mySession = request.getSession();
            if (mySession.getAttribute("name") != null) {
                out.println("<p>" + mySession.getAttribute("name") + "</p>");
                out.println("<p>" + mySession.getAttribute("age") + "</p>");
                out.println("<p>" + mySession.getAttribute("objeto") + "</p>");
                out.println("<a href='SignOut'>Logout</a>");
            } else {
                response.sendRedirect("index.html");
            }
        %>
    </body>
</html>
