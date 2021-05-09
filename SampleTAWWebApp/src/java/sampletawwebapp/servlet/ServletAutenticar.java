/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampletawwebapp.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sampletawwebapp.dao.AdministradorFacade;
import sampletawwebapp.entity.Administrador;

/**
 *
 * @author guzman
 */
@WebServlet(name = "ServletAutenticar", urlPatterns = {"/ServletAutenticar"})
public class ServletAutenticar extends HttpServlet {

    @EJB
    private AdministradorFacade administradorFacade;
    
    

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
        String strUsuario = request.getParameter("usuario");
        String strClave = request.getParameter("clave");    
        Administrador admin;
        String strError = "", strTo = "ServletCustomerListar";
        HttpSession session = request.getSession();
        
        if (strUsuario == null || strUsuario.isEmpty() || 
            strClave == null || strClave.isEmpty()) {  // Error de autenticación por email o clave
                                                       // vacíos o nulos.
            strError = "Error de autenticación: alguno de los valores está vacío";
            request.setAttribute("error", strError);
            strTo = "autentica.jsp";
                
        } else {
            admin = this.administradorFacade.findByEmailAndPassword(strUsuario, strClave);
            if (admin == null) { // No hay usuario en la BD
                strError = "Error de autenticación: credenciales incorrectas";
                request.setAttribute("error", strError);
                strTo = "autentica.jsp";                
            } else { // El usuario está en la BD
                session.setAttribute("admin", admin);
            }
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
