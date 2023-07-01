/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.auleweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import sql.SQLConstructor;

/**
 *
 * @author ldrak
 */
@WebServlet(name = "DeleteEventServlet", urlPatterns = {"/DeleteEventServlet"})
public class DeleteEventServlet extends HttpServlet {

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
            try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/aulaweb", "root", "");
         Statement stmt = conn.createStatement();
    ) {		      
         // Execute a query
         //System.out.println("Inserting records into the table...");          
         //String sql = "INSERT INTO comida3 (id,nombre,calorias,familia) VALUES (2,'test',2,'t')";
         //stmt.executeUpdate(sql);
         //System.out.println("Inserted records into the table...");   	  
    } catch (SQLException e) {
         System.out.println("no va");
         System.out.println(e);
    } 
        response.setContentType("text/html;charset=UTF-8");
        
        int id = Integer.parseInt(request.getParameter("idEvento"));

        new SQLConstructor().deleteEvento(id);
        JOptionPane.showMessageDialog(null, "EVENT DELETED CORRECTLY", "¡PERFECT!", JOptionPane.INFORMATION_MESSAGE);

        response.sendRedirect("index.html"); // Redirigir al dashboard o página principal

        
        
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
