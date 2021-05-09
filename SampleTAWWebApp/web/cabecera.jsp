<%-- 
    Document   : cabecera
    Created on : 21-abr-2021, 12:26:24
    Author     : guzman
--%>

<%@page import="sampletawwebapp.entity.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Administrador admin = (Administrador)session.getAttribute("admin");   
    if (admin == null) {
%>        
    <jsp:forward page="autentica.jsp" />
<%
     }   
%>
<table border="0" width="100%">
    <tr align="center">
        <td align="center">
        ¡Bienvenido al sistema, <%= admin.getEmail() %>!
        </td>
        <td align="right">
            <a href="ServletSalir">Salir...</a>
        </td>
    </tr>
</table>
