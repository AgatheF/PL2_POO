/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.tp10;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author agathe
 */
@WebServlet(name = "ControlerJeuServlet", urlPatterns = {"/jouer"})
public class ControlerJeuServlet extends HttpServlet {

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
        // recuperation de la session (ps de creation si n'existe pas car pas de parametre true)
        HttpSession session = request.getSession(); 
        NombreInconnu ni = (NombreInconnu) session.getAttribute("nombreCache");
        int proposition = Integer.parseInt(request.getParameter("valSaisie"));
        try {
            String message;
            int res = ni.testerValeur(proposition);
            if (res<0){
                message="Essayez plus petit !" ;                
            }
            else if (res>0){
                message="Essayez plus grand!";
            }
            else{
                message="Bravo vous avez trouvé le nombre cache =)!";
            }
            
            if(ni.getNbEssaisRestant()==0){
                if(res==0){
                    message="Il etait temps mais vous avez trouve !";
                }
                else{
                    message="desole vous n'avez plus d'essais"
                            + "le nombre etait "+ni.getValeur();
                }
            }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/PageJeu.jsp").forward(request, response);
            
        } catch (ValeurIncorrecteException ex) {
            //Logger.getLogger(ControlerJeuServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Valeur saisie hors des bornes");
        } catch (NbreEssaisDepasseException ex) {
            //Logger.getLogger(ControlerJeuServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Plus d essai");
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
 