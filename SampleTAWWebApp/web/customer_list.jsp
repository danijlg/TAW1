<%-- 
    Document   : customer_list
    Created on : 17-mar-2021, 12:26:04
    Author     : guzman
--%>

<%@page import="sampletawwebapp.entity.MicroMarket"%>
<%@page import="sampletawwebapp.entity.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        List<Customer> lista = (List)request.getAttribute("lista");
        List<MicroMarket> listaSupermercados = (List)request.getAttribute("listaSupermercados");
    %>
    <body>
        
        <jsp:include page="cabecera.jsp" />
        
        
        <h1>Listado de clientes</h1>
        <form>
            Nombre: <input type="text" name="filtroNombre"/>
            Supermercado: <select name="filtroSuper" multiple="multiple">
                <option value="">-----</option>                        
        <%
           for (MicroMarket mm: listaSupermercados) {
        %>
            <option><%= mm.getZipCode() %></option>        
        <%    
           }
        %>
                    
            </select>
                
            <input type="submit" value="Filtrar"/>
        </form>
        
        <table border="1">
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>EMAIL</th>
            <th>CITY</th>            
            <th>MICROMARKET</th>
            <th></th>            
            <th></th>                        
        </tr>        
            
        <%
            for (Customer cliente:lista) {
        %>   
        <tr>
            <td><%= cliente.getCustomerId() %></td>
            <td><%= cliente.getName() %></td>
            <td><%= cliente.getEmail() %></td>            
            <td><%= cliente.getCity() %></td>    
            <td><%= cliente.getZip().getZipCode() %></td>   
            <td><a href="ServletCustomerBorrar?id=<%= cliente.getCustomerId() %>">Borrar</a></td>
            <td><a href="ServletCustomerEditar?id=<%= cliente.getCustomerId() %>">Editar</a></td>            
        </tr>        
        <%
            }
        %>    
        </table>
        <a href="ServletCustomerCrear">Nuevo cliente ...</a>
    </body>
</html>
