/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.auleweb;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.swing.JOptionPane;

import sql.SQLConstructor;

/**
 *
 * @author ldrak
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

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
             
             
       SQLConstructor test = new SQLConstructor();
       String nom = request.getParameter("nombre");
       Integer idAula = Integer.parseInt(request.getParameter("idaula"));
       String des = request.getParameter("description");
       String snom = request.getParameter("staffname");
       String email = request.getParameter("email");
       String date = request.getParameter("Date");
       String hourStart = request.getParameter("hourStart");
       String minuteStart = request.getParameter("minuteStart");
       String hourEnd = request.getParameter("hourEnd");
       String minuteEnd = request.getParameter("minuteEnd");
       String tipo = request.getParameter("tipo");
       String recu = request.getParameter("recurrencia");
       String ffrecu = request.getParameter("fechafinrecurrencia");
      
       String T = "T";
       String dosPuntos = ":";
       String dosPuntos00 = ":00";
              
       String initialDate = date+T+hourStart+dosPuntos+minuteStart+dosPuntos00;
       String finalDate = date+T+hourEnd+dosPuntos+minuteEnd+dosPuntos00;       
       
       System.out.println(initialDate);

       //Hay q hacer if para comprar fechas y que no se solapen

       
       if (Integer.parseInt(hourStart) > Integer.parseInt(hourEnd)){
        JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser mayor que la fecha fin", "Hey!", JOptionPane.ERROR_MESSAGE);
        response.sendRedirect("/AuleWeb/additional_pages/testcalendar.html?id="+request.getParameter("iddepartamento")+"&idAula="+idAula);
           return;
       } else if (Integer.parseInt(hourStart) == 8 && Integer.parseInt(minuteStart) < 30) {
           JOptionPane.showMessageDialog(null, "El primer evento que se puede añadir es a partir de las 08:30h", "Hey!", JOptionPane.ERROR_MESSAGE);
           response.sendRedirect("/AuleWeb/additional_pages/testcalendar.html?id="+request.getParameter("iddepartamento")+"&idAula="+idAula);
           return;
           
       } else {
           test.readEvents(idAula,nom,des,snom,email,initialDate,finalDate, tipo,recu,ffrecu);

       }
       
       
       response.sendRedirect("/AuleWeb/additional_pages/testcalendar.html?id="+request.getParameter("iddepartamento")+"&idAula="+idAula);
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




