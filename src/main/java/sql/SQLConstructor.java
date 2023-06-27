/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

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
        //exeQuery();
        //exeQueryDepartamentos();
        //readEvents(2, "a", "a", "a", "a@a.com", "2023-06-16T14:30:00", "2023-06-16T15:30:00", "examen", "nula", "1753-01-01T00:00:00" );
        //String valor = new SQLConstructor().exeQueryEventos(2);
        //System.out.println(valor);
        
        //getEventsAula(1);
    }

    public static void readEvents(int idAula, String nombre, String descripcion, String nombreResponsable, String emailResponsable, String fechaInicio, String fechaFin, String tipo, String recurrencia, String fechaFinRecurrencia) {

        String sql = "INSERT INTO eventos (idAula,nombre,descripcion,nombreResponsable,emailResponsable,fechaInicio,fechaFin,tipo,recurrencia,fechaFinRecurrencia) VALUES "
                + "(" + idAula + ",'" + nombre + "','" + descripcion + "','" + nombreResponsable + "','" + emailResponsable + "','" + fechaInicio + "','" + fechaFin + "','" + tipo + "','" + recurrencia + "','" + fechaFinRecurrencia + "')";
        System.out.println(sql);
        SQLConstructor.insertSql(sql);

    }

    public static void insertSql(String sql) {

        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();) {
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

    public static void exeQuery() {
        String QUERY = "SELECT * FROM eventos";
        
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            while (rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", NOMBRE: " + rs.getString("nombre"));
                System.out.print(", DESCRIPCION: " + rs.getString("descripcion"));
                System.out.println(", TIPO: " + rs.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
        /*public static void getEventsAula(int idAula){
        String QUERY = "SELECT fechaInicio,fechaFin FROM eventos WHERE idAula = " +idAula;
        
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            HashMap<Date, Date> dates = new HashMap<>();
            
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
           

            while (rs.next()) {
                //Display values
                String fechaInicio = rs.getString("fechaInicio");
                String fechaFin = rs.getString("fechaFin");

                java.sql.Date sqlDate1 = new java.sql.Date(sdf.parse(fechaInicio));
                java.sql.Date sqlDate2 = new java.sql.Date(sdf.parse(fechaFin));

                
                //Date fInicioDate = sdf.parse(fechaInicio);
                //Date fFinDate = sdf.parse(fechaFin);

                
                dates.put(fInicioDate, fFinDate);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } */


    public String exeQueryDepartamentos() {

        String result = "";
        String QUERY = "SELECT * FROM edificios";
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            ArrayList<String> departments = new ArrayList<String>();
            while (rs.next()) {
                Deparment department = new Deparment(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );

                departments.add(department.toString());
            }

            result = "[" + String.join(",", departments) + " ]";

        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    
    
    
    public String exeQueryAulas(int id) {

        String result = "";
        String QUERY = "SELECT Id, nombre, aforo FROM aulas WHERE IdEdificios = " + id;
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            ArrayList<String> aulas = new ArrayList<String>();
            while (rs.next()) {
                Aula aula = new Aula(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("aforo")
                );

                aulas.add(aula.toString());
            }

            result = "[" + String.join(",", aulas) + " ]";

        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }
    
     public String exeQueryEventos(int id) {

        String result = "";
        String QUERY = "SELECT * FROM eventos WHERE idAula = " + id;
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            ArrayList<String> eventos = new ArrayList<String>();
            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("tipo"),
                        rs.getString("fechaInicio"),
                        rs.getString("fechaFin")
                );

                eventos.add(evento.toString());
            }

            result = "[" + String.join(",", eventos) + " ]";

        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    public static void readDataBuildings() {

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

                String sql = "INSERT INTO edificios (id,nombre) VALUES (" + datos[0] + ",'" + datos[1] + "')";
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

    public static void readDataAules() {
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
                String sql = "INSERT INTO aulas (id, idEdificios, nombre, descripcion, ubicacion, aforo, numEnchufes, red, tieneProyector, tienePantallaMotorizada, tienePantallaManual, tieneSisAudio, tienePC, tieneMicIna, tieneMicAla, tieneRetroProy, tieneWifi) VALUES (" + datos[0] + ",'" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "','" + datos[5] + "','" + datos[6] + "','" + datos[7] + "','" + datos[8] + "','" + datos[9] + "','" + datos[10] + "','" + datos[11] + "','" + datos[12] + "','" + datos[13] + "','" + datos[14] + "','" + datos[15] + "','" + datos[16] + "')";
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
