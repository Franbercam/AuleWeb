/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icalendar;

import java.io.FileInputStream;
import java.io.IOException;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.CalendarException;

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
}
