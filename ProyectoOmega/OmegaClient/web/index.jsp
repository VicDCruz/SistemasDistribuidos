<%-- 
    Document   : index
    Created on : May 12, 2019, 5:11:14 PM
    Author     : pmeji
--%>

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


<body>
    <div class="container">
        <br>
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="row">
                    <div class="col-md-12">
                        <h1>Welcome to Data Web Wizard</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <%
                            HttpSession mySession = request.getSession();
                            if (request.getParameter("err") != null) {
                                out.write("<p> Error: Session not initializated </p>");
                            }
                            if (request.getParameter("logout") != null) {
                                out.write("<p> You've logged out successfully </p>");
                            }
                        %>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <form action="app.jsp" method="GET">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="text" class="form-control" name="name" value=""
                                    placeholder="User">
                                <small id="emailHelp" class="form-text text-muted">Nunca compartiremos 
                                    tus datos con otros</small>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control" name="password"
                                    placeholder="Password">
                            </div>
                            <input type="submit" value="Log in" class="btn btn-info" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

</html>