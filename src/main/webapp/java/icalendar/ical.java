/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;



import java.io.FileInputStream;
import java.io.IOException;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author franb
 */
public class ical {
    public static void main(String[] args) {
        String filePath = "ruta/al/archivo.ics"; // Reemplaza con la ruta de tu archivo iCalendar

        try (FileInputStream fis = new FileInputStream(filePath)) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(fis);

            Document document = Jsoup.parse("<html><body></body></html>");
            Element body = document.selectFirst("body");

            for (Component component : calendar.getComponents()) {
                // Genera el contenido HTML para cada componente del iCalendar
                String componentHtml = generateComponentHtml(component);
                body.append(componentHtml);
            }

            System.out.println(document.html());

        } catch (IOException | CalendarException e) {
            e.printStackTrace();
        }
    }

    private static String generateComponentHtml(Component component) {
        // Genera el contenido HTML para el componente específico (puedes personalizar esto según tus necesidades)
        String html = "<div>";
        html += "<h2>" + component.getName() + "</h2>";
        html += "<p>" + component.getProperty("SUMMARY").getValue() + "</p>";
        html += "</div>";

        return html;
    }
    
    public class GenerarEventoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String titulo = request.getParameter("titulo");
        String fecha = request.getParameter("fecha");

        // Crear un nuevo objeto Calendar
        Calendar calendar = new Calendar();

        // Crear un nuevo evento
        try {
            DateTime dateTime = new DateTime(fecha);
            VEvent event = new VEvent(dateTime, titulo);

            // Agregar propiedades al evento
            event.getProperties().add(new Description("Este es un evento generado por iCal4j."));

            // Agregar el evento al Calendar
            calendar.getComponents().add(event);

            // Configurar el encabezado de la respuesta HTTP
            response.setContentType("text/calendar");
            response.setHeader("Content-Disposition", "attachment; filename=evento.ics");

            // Obtener el flujo de salida de la respuesta
            OutputStream outputStream = response.getOutputStream();

            // Escribir el Calendar en el flujo de salida
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, outputStream);

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el evento iCalendar.");
        }
    }
}
    
}
