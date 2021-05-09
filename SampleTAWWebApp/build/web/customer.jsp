<%-- 
    Document   : customer
    Created on : 07-abr-2021, 11:12:55
    Author     : guzman
--%>

<%@page import="sampletawwebapp.entity.Customer"%>
<%@page import="sampletawwebapp.entity.DiscountCode"%>
<%@page import="sampletawwebapp.entity.MicroMarket"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos del cliente</title>
    </head>
    <%
        List<MicroMarket> listaSupermercados = (List)request.getAttribute("listaSupermercados");
        List<DiscountCode> listaDescuentos = (List)request.getAttribute("listaDescuentos");
        Customer cliente = (Customer)request.getAttribute("cliente");
        String strNombre = "", strEmail = "", strDomicilio1 = "", strDomicilio2 = "", 
               strSupermercado = "", strDescuento = "", strId = "";
        if (cliente != null) {
            strNombre = cliente.getName();
            strEmail = cliente.getEmail();
            strDomicilio1 = cliente.getAddressline1();
            strDomicilio2 = cliente.getAddressline2();
            strSupermercado = cliente.getZip().getZipCode();
            strDescuento = cliente.getDiscountCode().getDiscountCode();
            strId = cliente.getCustomerId().toString();
        }

    %>    
    <body>
        <jsp:include page="cabecera.jsp" />
        
        <h1>Datos del cliente</h1>
        <form action="ServletCustomerGuardar">
            <table>
                <input type="hidden" name="id" value="<%= strId %>" />
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" maxlength="30" size="30" value="<%= strNombre %>" /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email"  maxlength="40" size="40" value="<%= strEmail %>" /></td>
                </tr>
                <tr>
                    <td>Domicilio:</td>
                    <td><input type="text" name="domicilio1" maxlength="30" size="30"  value="<%= strDomicilio1 %>"/> 
                       <input type="text" name="domicilio2" maxlength="30" size="30"  value="<%= strDomicilio2 %>"/></td>
                </tr>
                <tr>
                    <td>Supermercado:</td>
                    <td>                
             <select name="supermercado">
                <%
                    for (MicroMarket mm: listaSupermercados) {
                        String strSeleccionado = "";
                        if (mm.getZipCode().equals(strSupermercado)) {
                            strSeleccionado = "selected";
                        }                        
                %>                    
                    <option <%= strSeleccionado %> value="<%= mm.getZipCode() %>"><%= mm.getZipCode() %></option>
                <%
                    }
                %>    
            </select>
                    </td>        
                </tr>
                    <td>Descuento:</td>
                    <td>                                    
              <select name="descuento">
                <%
                    for (DiscountCode dc: listaDescuentos) {
                        String strSeleccionado = "";
                        if (dc.getDiscountCode().equals(strDescuento)) {
                            strSeleccionado = "selected";
                        }
                %>    
                    <option <%= strSeleccionado %> value="<%= dc.getDiscountCode() %>"><%= dc.getRate() %></option>
                <%
                    }
                %>    
            </select> 
                    </td>        
                </tr>
                <tr>            
                    <td colspan="2">
                       <input type="submit" value="Guardar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
