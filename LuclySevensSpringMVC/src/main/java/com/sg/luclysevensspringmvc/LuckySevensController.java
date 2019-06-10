/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.luclysevensspringmvc;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author wgv85
 */
@Controller
public class LuckySevensController extends HttpServlet {

    @RequestMapping(value="/userInput", method=RequestMethod.POST)
    
    public String userInput(HttpServletRequest request, Map<String, Object> model) {
                Random randomizer = new Random();
        
        int rollsToHighestAmt = 0;
        int numberOfRolls = 0;
        String originalBetAmtString = request.getParameter("userWager");
        int originalBetAmt = Integer.parseInt(originalBetAmtString);
        int highestAmt = originalBetAmt;
        int betAmt = originalBetAmt;
        
        while (betAmt > 0){
            ++numberOfRolls;
            int rollOne = randomizer.nextInt(7) + 1;
            int rollTwo = randomizer.nextInt(7) + 1;
            
            if (rollOne + rollTwo == 7){
                betAmt = betAmt + 4;
                if(betAmt > originalBetAmt){
                    highestAmt = betAmt;
                    rollsToHighestAmt = numberOfRolls;
                }
            }
            if (rollOne + rollTwo != 7){
                --betAmt;
            }
        }
        
        String numberOfRollsString = Integer.toString(numberOfRolls);
        String rollsToHighestAmtString = Integer.toString(rollsToHighestAmt);
        String highestAmtString = Integer.toString(highestAmt);
        
        model.put("originalBetAmt", originalBetAmtString);
        model.put("numberOfRolls", numberOfRollsString);
        model.put("rollsToHighestAmt", rollsToHighestAmtString);
        model.put("highestAmt", highestAmtString);
        
        return "result";
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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
