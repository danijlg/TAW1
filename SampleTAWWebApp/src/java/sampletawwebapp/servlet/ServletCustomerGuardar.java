/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampletawwebapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sampletawwebapp.dao.CustomerFacade;
import sampletawwebapp.dao.DiscountCodeFacade;
import sampletawwebapp.dao.MicroMarketFacade;
import sampletawwebapp.entity.Administrador;
import sampletawwebapp.entity.Customer;
import sampletawwebapp.entity.DiscountCode;
import sampletawwebapp.entity.MicroMarket;

/**
 *
 * @author guzman
 */
@WebServlet(name = "ServletCustomerGuardar", urlPatterns = {"/ServletCustomerGuardar"})
public class ServletCustomerGuardar extends HttpServlet {

    @EJB
    private DiscountCodeFacade discountCodeFacade;

    @EJB
    private MicroMarketFacade microMarketFacade;
    
    @EJB
    private CustomerFacade customerFacade;
    
            
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Administrador admin = (Administrador)session.getAttribute("admin");
        if (admin == null) {
            request.setAttribute("error", "Usuario no autenticado");
            RequestDispatcher rd = request.getRequestDispatcher("autentica.jsp");
            rd.forward(request, response);
        } else {                
        
            String id, nombre, email, domicilio1, domicilio2, cp, descuento;
            Customer cliente; 

            id = request.getParameter("id");
            nombre = request.getParameter("nombre");
            email = request.getParameter("email");      
            domicilio1 = request.getParameter("domicilio1");
            domicilio2 = request.getParameter("domicilio2");
            cp = request.getParameter("supermercado");
            descuento = request.getParameter("descuento");

            if (id == null || id.isEmpty()) { // Crear nuevo cliente
                cliente = new Customer();            
            } else { // Editar cliente existente
                cliente = this.customerFacade.find(new Integer(id));
            }
            cliente.setName(nombre);
            cliente.setEmail(email);
            cliente.setAddressline1(domicilio1);
            cliente.setAddressline2(domicilio2);

            MicroMarket mm = this.microMarketFacade.find(cp);
            cliente.setZip(mm);                

            DiscountCode dc = this.discountCodeFacade.find(descuento);
            cliente.setDiscountCode(dc);

            if (id == null || id.isEmpty()) { // Crear nuevo cliente        
                this.customerFacade.create(cliente);
            } else { // Editar cliente existente
                this.customerFacade.edit(cliente);
            }

            response.sendRedirect("ServletCustomerListar");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
