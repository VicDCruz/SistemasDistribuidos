<%-- 
    Document   : welcome
    Created on : 1/03/2019, 04:38:09 PM
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
        <h1>Hello World!</h1>
        
        <%
            if (request.getParameter("password") != null) {
                if (request.getParameter("password").equals("123456")) {
                    HttpSession mySession = request.getSession();
                    mySession.setAttribute("name", request.getParameter("name"));
                    mySession.setAttribute("age", request.getParameter("age"));
                    servlets.MyObjectSession myObject = new servlets.MyObjectSession(
                            Integer.valueOf(request.getParameter("age")),
                            request.getParameter("name"));
                    mySession.setAttribute("objeto", myObject);
                    mySession.setMaxInactiveInterval(20);
                    out.println("<p> Bienvenido " + request.getParameter("name") + "</p>");
                    out.println("<a href='profile.jsp'>Perfil</a>");
                } else {
                    response.sendRedirect("index.html");
                }
            } else {
                response.sendRedirect("index.html");
            }
        %>
    </body>
</html>
