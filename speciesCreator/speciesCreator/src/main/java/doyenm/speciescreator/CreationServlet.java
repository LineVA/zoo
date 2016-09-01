/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.speciescreator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author doyenm
 */
public class CreationServlet extends HttpServlet {

    SpecieCreation creator = new SpecieCreation();

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
        String english = request.getParameter("englishName");
        String french = request.getParameter("frenchName");
        String scientific = request.getParameter("scientificName");
        String femaleMat = request.getParameter("femaleMaturity");
        String maleMat = request.getParameter("maleMaturity");
        String gestation = request.getParameter("gestationFrequency");
        String litter = request.getParameter("litterSize");
        String leaving = request.getParameter("leavingAge");
        String femaleLife = request.getParameter("femaleLifespan");
        String maleLife = request.getParameter("maleLifespan");
        String territory = request.getParameter("territorySize");
        String group = request.getParameter("groupSize");
        String foodQuantity = request.getParameter("foodQuantity");
        String uicn = request.getParameter("uicn");
        String family = request.getParameter("family");
        String ecoregion = request.getParameter("ecoregion");
        String diet = request.getParameter("diet");
        String biome = request.getParameter("biome");
        creator.saveSpecie(english, french, scientific, uicn, family, ecoregion, 
                femaleMat, maleMat, gestation, litter, leaving, femaleLife, maleLife,
                biome, territory, diet, foodQuantity, group);
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
