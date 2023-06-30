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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.SQLConstructor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import sql.Admin;

/**
 *
 * @author ldrak
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
         
       String datab = new SQLConstructor().exeQueryAdmins();
       PrintWriter out = response.getWriter();
       out.print(datab);
      
       String email = request.getParameter("email");
       String pass = request.getParameter("password");
       
             
       boolean validCredentials = validateCredentials(email, pass);

        if (validCredentials) {
            // Iniciar sesión exitosamente  
            establishUserSession(request, email);
            response.sendRedirect("index.html"); // Redirigir al dashboard o página principal
        } else {
            // Credenciales inválidas, redirigir al formulario de inicio de sesión con un mensaje de error
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("adminlogin.html").forward(request, response);
        }
        
    }       
    
    private boolean validateCredentials(String email, String password) {
        SQLConstructor sqlConstructor = new SQLConstructor();
        String jsonAdmins = sqlConstructor.exeQueryAdmins();

        Gson gson = new Gson();
        List<Admin> adminsList = gson.fromJson(jsonAdmins, new TypeToken<List<Admin>>(){}.getType());

        for (Admin adminData : adminsList) {
            if (adminData.getEmail().equals(email) && adminData.getPassword().equals(password)) {
                return true; // Credenciales válidas
            }
        }

        return false; // Credenciales inválidas
    }
    
    private void establishUserSession(HttpServletRequest request, String email) {
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);

        // Otros datos adicionales que desees almacenar en la sesión
        // session.setAttribute("userId", userId);
        // session.setAttribute("name", name);
        // ...

        // Establecer la duración máxima de la sesión (en segundos)
        int sessionTimeout = 3600; // 1 hora (ajústalo según tus necesidades)
        session.setMaxInactiveInterval(sessionTimeout);
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
