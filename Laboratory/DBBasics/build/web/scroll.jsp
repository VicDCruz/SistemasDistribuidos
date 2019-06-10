<%-- 
    Document   : scroll
    Created on : 6/03/2019, 04:33:50 PM
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
        <h1>Scrolling database records</h1><br>
        <%
            int row = 0;
            HttpSession mySession = request.getSession();
            if (request.getAttribute("row") != null) {
                row = Integer.parseInt((String) mySession.getAttribute("row"));
            }
            if (request.getParameter(">") != null) {
                row++;
            } else if (request.getParameter("<") != null) {
                row--;
            }
            mySession.setAttribute("row", row + "");
            if (mySession.getAttribute("resultSet") != null) {
                Object[][] myResultSet = (Object[][]) mySession.getAttribute("resultSet");
                out.println("<p>ID: " + myResultSet[row][0] + "</p>");
                out.println("<p>NAME:" + myResultSet[row][1] + "</p>");
            } else {
                out.println("Sin datos");
            }
        %>
        <hr>
        <form action="scroll.jsp">
            <input type="submit" value="<" name="scroll"
                   <%if (mySession.getAttribute("row") != null && Integer.parseInt((String)mySession.getAttribute("row")) == 0)
                       out.print("disabled");
                   %>/>
            <input type="submit" value=">" name="scroll"
                   <%if (mySession.getAttribute("row") != null){
                       Object[][] myResultSet = (Object[][])(mySession.getAttribute("resultSet"));
                       int lastId = myResultSet[0].length;
                       if (Integer.parseInt((String)mySession.getAttribute("row")) == lastId)
                           out.print("disabled");
                    }
                   %>/>
        </form>
    </body>
</html>
