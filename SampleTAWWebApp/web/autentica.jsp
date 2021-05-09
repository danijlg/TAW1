<%-- 
    Document   : autentica
    Created on : 21-abr-2021, 11:44:20
    Author     : guzman
--%>

<%@page import="sampletawwebapp.entity.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido al sistema</title>
    </head>
    <%
        Administrador admin = (Administrador)session.getAttribute("admin");
        if (admin != null) {
    %>        
    <jsp:forward page="ServletCustomerListar" />
    <%
        }        
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>
    <body>
        <h1>Autenticacion en el sistema</h1>
        <form method="POST" action="ServletAutenticar">
            <%= strError %> <br/>
            Usuario: <input type="text" name="usuario" value="" /><br/>
            Clave: <input type="password" name="clave" value="" /> <br/>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
