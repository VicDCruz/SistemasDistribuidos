<%-- 
    Document   : index
    Created on : 4/03/2019, 04:36:11 PM
    Author     : daniel
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/Staff",
                    "root",
                    "root");
            Statement query = con.createStatement();
            if (request.getParameter("add") != null) {
                String name = request.getParameter("name");
                double balance = Double.valueOf(request.getParameter("balance"));
                ResultSet rs = query.executeQuery("SELECT COUNT(*) FROM PEOPLE");
                rs.next();
                int lastIndex = rs.getInt(1) + 1;
                query.executeUpdate("INSERT INTO PEOPLE VALUES(" + lastIndex + 
                        ",'" + name + "'," + balance + ")");
            } else if (request.getParameter("delete") != null) {
                String id = request.getParameter("id");
                int result = query.executeUpdate("DELETE FROM PEOPLE WHERE "
                        + "id=" + id);
                if (result != 0) {
                    System.out.println("Eliminado... " + id);
                } else {
                    System.out.println("No se elimino... " + id);
                }
            } else if (request.getParameter("update") != null){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                double balance = Double.valueOf(request.getParameter("balance"));
                int result = query.executeUpdate("UPDATE PEOPLE SET"
                        + " name='" + name + "', balance=" + balance + " WHERE id=" + id);
                if (result != 0) {
                    System.out.println("Actualizado... " + id);
                } else {
                    System.out.println("No se actualizo... " + id);
                }
            }
        %>
        <div>
            <h1>Staff</h1>
            <%
                ResultSet rs = query.executeQuery("SELECT * FROM PEOPLE");
                while(rs.next()) {
                    out.println("Id: "+ rs.getInt("ID"));
                    out.println(", Name: "+ rs.getString("NAME"));
                    out.println(", Balance: "+ rs.getDouble("BALANCE"));
                    out.println("<br>");
                }
            %>
        </div>
        <div>
            <h2>Add a record</h2>
            <form action="index.jsp">
                <span>Id:</span><input type="text" name="id" value="" /><br>
                <span>Name:</span><input type="text" name="name" value="" /><br>
                <span>Balance:</span><input type="text" name="balance" value="" /><br>
                <input type="submit" value="Ok" name="add" />
            </form>
        </div>
        <div>
            <h2>Delete a record</h2>
            <form action="index.jsp">
                <span>Id:</span><input type="text" name="id" value="" /><br>
                <input type="submit" value="Ok" name="delete" />
            </form>
        </div>
        <div>
            <h2>Update a record</h2>
            <form action="index.jsp">
                <span>Id:</span><input type="text" name="id" value="" /><br>
                <span>Name:</span><input type="text" name="name" value="" /><br>
                <span>Balance:</span><input type="text" name="balance" value="" /><br>
                <input type="submit" value="Ok" name="update" />
            </form>
        </div>
    </body>
</html>
