/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.auleweb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "downloadAule", urlPatterns = {"/downloadAule"})
public class downloadAule extends HttpServlet {

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
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/aulaweb", "root", "");  Statement stmt = conn.createStatement();) {

        } catch (SQLException e) {
        }
        try {
                String home = System.getProperty("user.home");

                String filePath = home + "\\Downloads\\" + "aulas" + ".csv";
                SQLConstructor sqlConstructor = new SQLConstructor();
                
                
                sqlConstructor.generateAulas(filePath);

                // Get PrintWriter object
                PrintWriter out = response.getWriter();

                // Set the content type and header of the response.
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\""
                        + "aulas.csv\"");

                // Get FileInputStream object to identify the path
                FileInputStream inputStream
                        = new FileInputStream(filePath);

                // Loop through the document and write into the
                // output.
                int in;
                while ((in = inputStream.read()) != -1) {
                    out.write(in);
                }

                // Close FileInputStream and PrintWriter object
                inputStream.close();
                out.close();
            
            JOptionPane.showMessageDialog(null, "Aules donwloaded correctly. Check your download folder", "¡PERFECT!", JOptionPane.INFORMATION_MESSAGE);

                
        } catch (Exception  e) {
            e.printStackTrace();
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
