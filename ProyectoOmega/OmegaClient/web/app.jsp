<%-- 
    Document   : app
    Created on : 16/05/2019, 05:37:08 PM
    Author     : daniel
--%>

<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.sql.SQLException"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="dataBase.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Wizard</title>
    </head>

    <body onload="getTablesName(null, {})">
        <div class="container">
            <br>
            <div class="row">
                <div class="col-md-6">
                    <h1>Wizard:
                        <span id="session"><%
                                String DATABASE = "myThirdDatabase";
                                HttpSession mySession = request.getSession(false);
                                if (mySession == null || mySession.getAttribute("username") == null) {
                                    response.sendRedirect("index.jsp" + "?err=1"); // No logged-in user found, so redirect to login page.
                                    return;
                                }
                                Conexion c = new Conexion(DATABASE);
                                String nomU = request.getParameter("name").replace(" ", "_");
                                String passU = request.getParameter("password");
                                if (nomU != null) {
                                    try {
                                        JSONParser parser = new JSONParser();
                                        String checkU = c.checkUser("users", new String[]{"name ='" + nomU + "'"});
                                        JSONObject json_user = (JSONObject) parser.parse(checkU);
                                        String sessionStr;
                                        if (json_user.get("name").equals("error")) {
                                            c.createTuple("users", new String[]{"'" + nomU + "'", "'" + passU + "'"});
                                            c.close();
                                            sessionStr = nomU;
                                            mySession = request.getSession();
                                            mySession.setAttribute("username", session);
                                        } else { //Check password
                                            checkU = c.checkPassword("users", new String[]{"name ='" + nomU + "'", "password ='" + passU + "'"});
                                            c.close();
                                            json_user = (JSONObject) parser.parse(checkU);
                                            if (json_user.get("name").equals("error")) {
                                                response.sendRedirect("index.jsp" + "?err=1");
                                                //REDIRECT A PAGINA PRINCIAL
                                            } else {
                                                sessionStr = nomU;
                                                mySession = request.getSession();
                                                mySession.setAttribute("username", session);
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        
                                    }
                                } else {
                                    response.sendRedirect("index.jsp" + "?err=1");
                                }
                            
                            
                                out.println(nomU);
                        %></span>
                    </h1>
                </div>
                <div class="col-md-6">
                    <form action="SignOut">
                        <input type="hidden" name="logout" value="1" />
                        <input type="submit" value="Log-out" class="btn btn-primary" />
                    </form>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <h3>
                        Selecciona tu tabla:
                    </h3>
                    <div class="row">
                        <div class="col-md-3">
                            <select name="myBd" id="myBd" onchange="getTableColumns(this)">
                                <option value="">Selecciona una opción</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <button id="deleteTableBtn" class="btn btn-danger" disabled="disabled"
                                    onclick="deleteTable()">Eliminar tabla</button>
                        </div>
                    </div>
                    <br>
                    <h3>Resultados: </h3>
                    <div class="row">
                        <div class="col-md-8 table-responsive">
                            <table class="table" id="scrollTable">
                                <thead></thead>
                                <tbody></tbody>
                            </table>
                            <ul class="pagination">
                                <li class="page-item" onclick="updateScroll(false, '-')">
                                    <a class="page-link">&laquo;</a>
                                </li>
                                <li class="page-item" onclick="updateScroll(false, '+')">
                                    <a class="page-link">&raquo;</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <h3>Longitud</h3>
                            <select name="lengthDll" id="lengthDll" onchange="updateScroll(true, {})">
                                <option value="2">2</option>
                                <option value="4">4</option>
                                <option value="8">8</option>
                                <option value="16">16</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <button id="deleteBtn" class="btn btn-danger" disabled="disabled"
                            onclick="modifySelection('DELETE')">Eliminar seleccionados</button>
                    <button id="updateBtn" class="btn btn-info" disabled="disabled"
                            onclick="modifySelection('PUT')">Actualizar seleccionados</button>
                    <hr>
                    <h3>Insertar nueva tupla</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table" id="myAttributes">
                                <thead>
                                    <tr>
                                        <th>Atributo</th>
                                        <th>Valor</th>
                                        <th>Tipo</th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                        <div class="col-md-3">
                            <button id="newTupleBtn" class="btn btn-primary" onclick="sendTuple('myAttributes')"
                                    disabled>¡Enviar!</button>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6 table-responsive">
                    <table class="table" id="configTable">
                        <thead>
                            <tr>
                                <th>Primary Key</th>
                                <th>Nombre</th>
                                <th>Tipo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><input type="checkbox" name="isPrimary" id="isPrimary" onclick="setPrimary(this)"></td>
                                <td><input type="text" name="arg" id="arg"></td>
                                <td><select name="type" id="type">
                                        <option value="varchar(50)">Texto</option>
                                        <option value="int">Entero</option>
                                        <option value="decimal">Decimal</option>
                                        <option value="date">Fecha</option>
                                    </select></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-4">
                    <button class="btn btn-success" onclick="createNewRow('configTable')">Crear atributo</button>
                    <button class="btn btn-danger" onclick="deleteLast('configTable')">Eliminar último</button>
                    <button class="btn btn-info" onclick="deleteAll('configTable', 1, true)">Restablecer</button>
                    <br>
                    <br>
                    <input type="text" name="tableName" id="tableName" placeholder="Nombre de la tabla">
                    <br>
                    <br>
                </div>
                <div class="col-md-2">
                    <button class="btn btn-primary" onclick="sendTable('configTable')">¡Enviar!</button>
                </div>
            </div>
            <br><br>
        </div>
        <script src="app.js"></script>
    </body>

</html>