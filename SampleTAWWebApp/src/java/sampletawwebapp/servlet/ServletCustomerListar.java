/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampletawwebapp.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sampletawwebapp.dao.CustomerFacade;
import sampletawwebapp.dao.MicroMarketFacade;
import sampletawwebapp.entity.Administrador;
import sampletawwebapp.entity.Customer;
import sampletawwebapp.entity.MicroMarket;

/**
 *
 * @author guzman
 */
@WebServlet(name = "ServletCustomerListar", urlPatterns = {"/ServletCustomerListar"})
public class ServletCustomerListar extends HttpServlet {

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
        
        String strTo = "customer_list.jsp";
        HttpSession session = request.getSession();
        Administrador admin = (Administrador)session.getAttribute("admin");
        if (admin == null) {
            request.setAttribute("error", "Usuario no autenticado");
            strTo = "autentica.jsp";            
        } else {
                    
            String filtroNombre = request.getParameter("filtroNombre");

            //String filtroSuper = request.getParameter("filtroSuper");
            String [] filtroSuper = request.getParameterValues("filtroSuper");

            List<Customer> lista;

            if ((filtroNombre != null && filtroNombre.length()>0 )  || 
               (filtroSuper != null && filtroSuper.length>0 ) ) {// Estoy aplicando filtro
                lista = this.customerFacade.findBySimilarNameOrMicroMarket(filtroNombre, filtroSuper);

            } else {  // Quiero mostrar todos
                lista = this.customerFacade.findAll();            
            }

            List<MicroMarket> listaSupermercados = this.microMarketFacade.findAll();

            request.setAttribute("lista", lista);
            request.setAttribute("listaSupermercados", listaSupermercados);  
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(strTo);
        rd.forward(request, response);        
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
