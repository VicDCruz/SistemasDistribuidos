<%-- 
    Document   : index
    Created on : 12/04/2019, 09:25:07 AM
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
        <h1>Suma de WSDL</h1>
        
    <%-- start web service invocation --%><hr/>
    <%
    try {
	webserviceclients.MyWebService_Service service = new webserviceclients.MyWebService_Service();
	webserviceclients.MyWebService port = service.getMyWebServicePort();
	 // TODO initialize WS operation arguments here
	int a = 100;
	int b = 3;
	// TODO process result here
	int result = port.add(a, b);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
