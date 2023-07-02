/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.auleweb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sql.SQLConstructor;

/**
 *
 * @author ldrak
 */
@WebServlet(name = "cargarAulas", urlPatterns = {"/cargarAulas"})
public class cargarAulas extends HttpServlet {

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
         //String sql = "INSERT INTO comida3 (id,nombre,calorias,familia) VALUES (2,'test',2,'t')";
         //stmt.executeUpdate(sql);
    } catch (SQLException e) {

    } 
        response.setContentType("text/html;charset=UTF-8");
        
            try {
                // Crear un factory de items de archivo en disco
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // Crear un objeto ServletFileUpload con el factory
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parsear la solicitud para obtener una lista de items de archivo
                List<FileItem> items = upload.parseRequest(request);

                // Iterar sobre los items
                for (FileItem item : items) {
                    // Verificar si el item es un campo de formulario regular o un archivo
                    if (!item.isFormField()) {
                        // Obtener el nombre del archivo
                        String fileName = item.getName();
                        
                        String home = System.getProperty("user.home");
                        // Crear un archivo temporal donde se guardará el archivo subido
                        File uploadedFile = new File(home + "/Downloads/", fileName);

                        // Guardar el archivo subido en el disco
                        item.write(uploadedFile);

                        // Pasar el archivo a la función readDataAules(File file)
                        new SQLConstructor().readDataAules(uploadedFile);

                        // Eliminar el archivo temporal después de su uso, si es necesario
                        uploadedFile.delete();
                    }
                }

                // Redireccionar o mostrar una respuesta exitosa
                JOptionPane.showMessageDialog(null, "The file has been uploaded correctly", "¡PERFECT!", JOptionPane.INFORMATION_MESSAGE);
                response.sendRedirect("/AuleWeb/");
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar cualquier excepción ocurrida durante el procesamiento del archivo
                // Redireccionar o mostrar una respuesta de error
                JOptionPane.showMessageDialog(null, "Error, file could not be uploaded", "Hey!", JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("/AuleWeb/");
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
