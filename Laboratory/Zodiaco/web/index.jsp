<%-- 
    Document   : index
    Created on : 20/02/2019, 05:16:23 PM
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
        <div>
            <h1>Zodiaco</h1>            
        </div>
        <div>
            <form action="SignZodiac">
                <table border="1">
                    <thead>
                        <tr>
                            <th>¿Cuál es tu fecha de nacimiento?</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Dia</td>
                            <td>
                                <input type="text" name="day" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Mes</td>
                            <td>
                                <input type="text" name="month" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Año</td>
                            <td>
                                <input type="text" name="year" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="reset" value="Limpiar" />
                            </td>
                            <td>
                                <input type="submit" value="Enviar" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <%
                if(request.getParameter("error") != null) {
                    out.println("<br><p>Capturaste una fecha inválida</p>");
                }
            %>
        </div>
    </body>
</html>
