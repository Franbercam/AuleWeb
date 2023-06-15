/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.*;
import java.io.*;

/**
 *
 * @author ldrak
 */
public class SQLConstructor {
    
    static final String DB_URL = "jdbc:mysql://localhost/aulaweb";
    static final String USER = "root";
    static final String PASS = "";
    
    
    
       public static void main(String[] args) { 
           
           //readDataBuildings();
           //readDataAules();          
       
       }
       
       public static void readEvents(int idAula, String nombre, String descripcion, String nombreResponsable, String emailResponsable, String fechaInicio, String fechaFin, String tipo, String recurrencia, String fechaFinRecurrencia){
        
            String sql = "INSERT INTO edificios (nombre,descripcion,nombreResponsable,emailResponsable,fechaInicio,fechaFin,tipo,recurrencia,fechaFinRecurrencia) VALUES "
                    + "('"+idAula+"','"+nombre+"','"+descripcion+"','" +nombreResponsable+"','"+emailResponsable+"','"+fechaInicio+"','"+fechaFin+"','"+tipo+"','"+recurrencia+"','"+fechaFinRecurrencia+"')";
            System.out.println(sql);
            SQLConstructor.insertSql(sql);
        
    }
           
    public static void insertSql(String sql) {       

      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
         // Execute a query
         //System.out.println("Inserting records into the table...");          
         //String sql = "INSERT INTO comida3 (id,nombre,calorias,familia) VALUES (2,'test',2,'t')";
         stmt.executeUpdate(sql);
         //System.out.println("Inserted records into the table...");   	  
      } catch (SQLException e) {
         System.out.println("no va");
         System.out.println(e);
      } 
    }
    
    public static void readDataBuildings(){
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
 
        String linea = null;
 
        try {
            
            //Cargamos el archivo de la ruta relativa
            archivo = new File("src\\main\\webapp\\data\\edificios.csv");
            //Cargamos el objeto FileReader
            fr = new FileReader(archivo);
            //Creamos un buffer de lectura
            br = new BufferedReader(fr);
 
            String[] datos = null;
 
            //Leemos hasta que se termine el archivo
            while ((linea = br.readLine()) != null) {
 
                //Utilizamos el separador para los datos
                datos = linea.split(";");
                //Presentamos los datos
               
                String sql = "INSERT INTO edificios (id,nombre) VALUES ("+datos[0]+",'"+datos[1]+"')";
                System.out.println(sql);
                SQLConstructor.insertSql(sql);
                //stmt.executeUpdate(sql);
 
            }
 
            //Capturamos las posibles excepciones
        } catch (Exception e) {
            System.out.println("No funciona");
            e.printStackTrace();
       
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    }
    
    public static void readDataAules(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
 
        String linea = null;
 
        try {
            
            //Cargamos el archivo de la ruta relativa
            archivo = new File("src\\main\\webapp\\data\\aulas.csv");
            //Cargamos el objeto FileReader
            fr = new FileReader(archivo);
            //Creamos un buffer de lectura
            br = new BufferedReader(fr);
 
            String[] datos = null;
 
            //Leemos hasta que se termine el archivo
            while ((linea = br.readLine()) != null) {
 
                //Utilizamos el separador para los datos
                datos = linea.split(";");
                //Presentamos los datos
                String sql = "INSERT INTO aulas (id, idEdificios, nombre, descripcion, ubicacion, aforo, numEnchufes, red, tieneProyector, tienePantallaMotorizada, tienePantallaManual, tieneSisAudio, tienePC, tieneMicIna, tieneMicAla, tieneRetroProy, tieneWifi) VALUES ("+datos[0]+",'"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','" +datos[4]+"','"+datos[5]+"','"+datos[6]+"','"+datos[7]+"','"+datos[8]+"','"+datos[9]+"','"+datos[10]+"','"+datos[11]+"','"+datos[12]+"','"+datos[13]+"','"+datos[14]+"','"+datos[15]+"','"+datos[16]+"')";
                System.out.println(sql);
                SQLConstructor.insertSql(sql);
                //stmt.executeUpdate(sql);
 
            }
 
            //Capturamos las posibles excepciones
        } catch (Exception e) {
            System.out.println("No funciona");
            e.printStackTrace();
       
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    }
    
    
}
