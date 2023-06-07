/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.*;
/**
 *
 * @author ldrak
 */
public class SQLConstructor {
    
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "";
    
       public static void main(String[] args) { 
       
        try
	{
            SQLConstructor.insertSql();
	}
	catch(Exception e )
	{
            System.out.println("No funciona");
            e.printStackTrace();
	}
       
       }
           
    public static void insertSql() {       

      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
         // Execute a query
         System.out.println("Inserting records into the table...");          
         String sql = "INSERT INTO comida3 (id,nombre,calorias,familia) VALUES (3,'Feeeee',222222,'httj')";
         stmt.executeUpdate(sql);
         System.out.println("Inserted records into the table...");   	  
      } catch (SQLException e) {
         System.out.println("no va");
         System.out.println(e);
      } 
    }
}
