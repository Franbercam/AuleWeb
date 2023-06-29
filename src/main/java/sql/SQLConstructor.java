/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import com.opencsv.CSVWriter;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        //exeQueryAulasId();
        //exeQuery();
        //exeQueryDepartamentos();
        //readEvents(2, "a", "a", "a", "a@a.com", "2023-06-16T14:30:00", "2023-06-16T15:30:00", "examen", "nula", "1753-01-01T00:00:00" );
        //String valor = new SQLConstructor().exeQueryEventos(2);
        //System.out.println(valor);
        //getEventsAula(1);
        //String home = System.getProperty("user.home");
        //generateEventsCSV2(new java.sql.Date(2023, 6, 26), new java.sql.Date(2023, 7, 1), home + "/Downloads/" + "evento" + ".csv");
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

    public String exeQueryAulasId(int id) {

        String result = "";
        String QUERY = "SELECT * FROM aulas WHERE IdEdificios = " + id;
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            ArrayList<String> aulas = new ArrayList<String>();
            while (rs.next()) {
                Aula aula = new Aula(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("ubicacion"),
                        rs.getInt("aforo"),
                        rs.getBoolean("tieneProyector"),
                        rs.getBoolean("tienePantallaMotorizada"),
                        rs.getBoolean("tienePantallaManual"),
                        rs.getBoolean("tieneSisAudio"),
                        rs.getBoolean("tienePC"),
                        rs.getBoolean("tieneMicIna"),
                        rs.getBoolean("tieneMicAla"),
                        rs.getBoolean("tieneRetroProy"),
                        rs.getBoolean("tieneWifi")
                );

                aulas.add(aula.toStringComplete());
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

    public String getEventId(int id) {

        String result = "";
        String QUERY = "SELECT * FROM eventos WHERE id = " + id;
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            ArrayList<String> eventos = new ArrayList<String>();
            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("nombreResponsable"),
                        rs.getString("emailResponsable"),
                        rs.getString("tipo"),
                        rs.getString("fechaInicio"),
                        rs.getString("fechaFin"),
                        rs.getString("recurrencia"),
                        rs.getString("fechaFinRecurrencia")
                );

                eventos.add(evento.toStringComplete());
            }

            result = "[" + String.join(",", eventos) + " ]";

        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    private static String FormatFecha(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String strFecha = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);

        return strFecha;
    }

    public static void generateEventsCSV(Date fechaInicio, Date fechaFin, String filePath) {
        System.out.println(filePath);

        String QUERY = "SELECT * FROM eventos WHERE fechaInicio >=  '"
                + FormatFecha(fechaInicio)
                + "' AND fechaFin <= '"
                + FormatFecha(fechaFin) + "'";
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            System.out.println(QUERY);
            try ( ResultSet rs = stmt.executeQuery()) {

                // Crear el escritor CSV
                try ( CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
                    // Escribir el encabezado del archivo CSV
                    writer.writeNext(new String[]{"id", "nombre", "descripcion", "nombreResponsable", "emailResponsable", "tipo", "fechaInicio", "fechaFin", "recurrencia", "fechaFinRecurrencia"});

                    // Escribir los eventos en el archivo CSV
                    while (rs.next()) {
                        System.out.println("x");
                        String[] eventoData = {
                            String.valueOf(rs.getInt("id")),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("nombreResponsable"),
                            rs.getString("emailResponsable"),
                            rs.getString("tipo"),
                            rs.getString("fechaInicio"),
                            rs.getString("fechaFin"),
                            rs.getString("recurrencia"),
                            rs.getString("fechaFinRecurrencia")
                        };
                        writer.writeNext(eventoData);
                    }
                }
            }

            System.out.println("Archivo CSV generado exitosamente: " + filePath);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateEventsCSV2(Date fechaInicio, Date fechaFin, String filePath) {
        System.out.println(filePath);

        String QUERY = "SELECT * FROM eventos WHERE fechaInicio >=  '"
                + FormatFecha(fechaInicio)
                + "' AND fechaFin <= '"
                + FormatFecha(fechaFin) + "'";

        System.out.println(QUERY);
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            try ( ResultSet rs = stmt.executeQuery();  FileWriter writer = new FileWriter(filePath)) {

                // Escribir el encabezado del archivo CSV
                writer.write("id,nombre,descripcion,nombreResponsable,emailResponsable,tipo,fechaInicio,fechaFin,recurrencia,fechaFinRecurrencia\n");
    

                // Escribir los eventos en el archivo CSV
                while (rs.next()) {
                    StringBuilder eventoData = new StringBuilder();
                    eventoData.append(rs.getInt("id")).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("nombre"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("descripcion"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("nombreResponsable"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("emailResponsable"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("tipo"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("fechaInicio"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("fechaFin"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("recurrencia"))).append(",");
                    eventoData.append(escapeCSVValue(rs.getString("fechaFinRecurrencia"))).append("\n");
                    writer.write(eventoData.toString());
                }

                System.out.println("Archivo CSV generado exitosamente: " + filePath);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String escapeCSVValue(String value) {
        if (value == null) {
            return "";
        }
        if (value.contains(",")) {
            value = "\"" + value + "\"";
        }
        return value;
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

public void readDataAules(File file) {
    FileReader fr = null;
    BufferedReader br = null;

    try {
        // Cargamos el objeto FileReader utilizando el archivo pasado como parámetro
        fr = new FileReader(file);
        // Creamos un buffer de lectura
        br = new BufferedReader(fr);

        String linea = null;
        List<String> registrosActuales = obtenerRegistrosActuales();

        // Leemos hasta que se termine el archivo
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");
            System.out.println(datos[0]);

            // Verificar si el registro ya existe en la base de datos
            String idAula = datos[0];
            if (registroExiste(idAula)) {
                System.out.println("El registro con ID " + idAula + " ya existe. Se realizará una actualización en lugar de la inserción.");
                actualizarRegistro(datos);
            } else {
                // Insertar el nuevo registro en la base de datos
                insertarRegistro(datos);
            }

            // Remover el registro actual de la lista para los registros que existen en el CSV
            registrosActuales.remove(idAula);
        }

        // Eliminar los registros restantes en la base de datos que no existen en el CSV
        eliminarRegistrosNoExistentes(registrosActuales);

        System.out.println("Finalizada la actualización del CSV.");

    } catch (Exception e) {
        System.out.println("Ocurrió un error al actualizar el CSV.");
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

private static List<String> obtenerRegistrosActuales() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> registrosActuales = new ArrayList<>();

        try {
            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Preparar la consulta SQL para obtener todos los registros actuales
            String sql = "SELECT id FROM aulas";
            statement = connection.prepareStatement(sql);

            // Ejecutar la consulta y obtener el resultado
            resultSet = statement.executeQuery();

            // Agregar los ID de los registros actuales a la lista
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                registrosActuales.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return registrosActuales;
    }

private static void eliminarRegistrosNoExistentes(List<String> registrosNoExistentes) {
        Connection connection = null;
        PreparedStatement statementEventos = null;
        PreparedStatement statementAulas = null;

        try {
            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Eliminar los registros de la tabla eventos que corresponden a los registros no existentes
            String sqlEventos = "DELETE FROM eventos WHERE idAula = ?";
            statementEventos = connection.prepareStatement(sqlEventos);
            for (String id : registrosNoExistentes) {
                statementEventos.setString(1, id);
                statementEventos.executeUpdate();
                System.out.println("Eliminando eventos asociados al registro con ID " + id);
            }

            // Eliminar los registros de la tabla aulas que no existen en el CSV
            String sqlAulas = "DELETE FROM aulas WHERE id = ?";
            statementAulas = connection.prepareStatement(sqlAulas);
            for (String id : registrosNoExistentes) {
                statementAulas.setString(1, id);
                statementAulas.executeUpdate();
                System.out.println("Eliminando registro con ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (statementEventos != null) {
                    statementEventos.close();
                }
                if (statementAulas != null) {
                    statementAulas.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    private static boolean registroExiste(String idAula) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Preparar la consulta SQL para verificar el registro existente
            String sql = "SELECT COUNT(*) FROM aulas WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, idAula);

            // Ejecutar la consulta y obtener el resultado
            resultSet = statement.executeQuery();

            // Verificar si el resultado tiene al menos una fila
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Devuelve true si el registro existe, false de lo contrario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Si ocurre algún error, se considera que el registro no existe
    }

    private static void insertarRegistro(String[] datos) {
        // Resto del código para insertar el registro en la base de datos
        String sql = "INSERT INTO aulas (id, idEdificios, nombre, descripcion, ubicacion, aforo, numEnchufes, red, tieneProyector, tienePantallaMotorizada, tienePantallaManual, tieneSisAudio, tienePC, tieneMicIna, tieneMicAla, tieneRetroProy, tieneWifi) VALUES (" + datos[0] + ",'" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "','" + datos[5] + "','" + datos[6] + "','" + datos[7] + "','" + datos[8] + "','" + datos[9] + "','" + datos[10] + "','" + datos[11] + "','" + datos[12] + "','" + datos[13] + "','" + datos[14] + "','" + datos[15] + "','" + datos[16] + "')";
        System.out.println("Insertando nuevo registro: " + sql);
        SQLConstructor.insertSql(sql);
    }

    private static void actualizarRegistro(String[] datos) {
        // Resto del código para actualizar el registro existente en la base de datos
        String sql = "UPDATE aulas SET idEdificios = '" + datos[1] + "', nombre = '" + datos[2] + "', descripcion = '" + datos[3] + "', ubicacion = '" + datos[4] + "', aforo = '" + datos[5] + "', numEnchufes = '" + datos[6] + "', red = '" + datos[7] + "', tieneProyector = '" + datos[8] + "', tienePantallaMotorizada = '" + datos[9] + "', tienePantallaManual = '" + datos[10] + "', tieneSisAudio = '" + datos[11] + "', tienePC = '" + datos[12] + "', tieneMicIna = '" + datos[13] + "', tieneMicAla = '" + datos[14] + "', tieneRetroProy = '" + datos[15] + "', tieneWifi = '" + datos[16] + "' WHERE id = " + datos[0];
        System.out.println("Actualizando registro existente: " + sql);
        SQLConstructor.insertSql(sql);
    }


}
