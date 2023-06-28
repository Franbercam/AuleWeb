/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

/**
 *
 * @author ldrak
 */
public class Evento {
    public int id;
    public String nombre;
    public String descripcion;
    public String nombreResponsable;
    public String emailResponsable;
    public String tipo;
    public String fechaInicio;
    public String fechaFin;
    public String recurrencia;
    public String fechaFinRecurrencia;
    
    public Evento(int id, String nombre, String descripcion, String tipo, String fechaInicio, String fechaFin){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    public Evento(int id, String nombre, String descripcion, String nombreResponsable, String emailResponsable, String tipo, String fechaInicio, String fechaFin, String recurrencia, String fechaFinRecurrencia){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreResponsable = nombreResponsable;
        this.emailResponsable = emailResponsable;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.recurrencia = recurrencia;
        this.fechaFinRecurrencia = fechaFinRecurrencia;
    }
    
     @Override
    public String toString() {
        return  "{ \"id\": " + this.id + ", \"nombre\":\"" + this.nombre + "\", \"descripcion\": \"" +this.descripcion +"\", \"tipo\":\"" + this.tipo + "\", \"fechaInicio\":\"" + this.fechaInicio.replace(" ", "T") + "\", \"fechaFin\":\"" + this.fechaFin.replace(" ", "T") + "\" }";
    }
    
    public String toStringComplete() {
        return  "{ \"id\": " + this.id + ", \"nombre\":\"" + this.nombre + "\", \"descripcion\":\"" +this.descripcion +"\", \"nombreResponsable\":\"" +this.nombreResponsable +"\", \"emailResponsable\":\"" +this.emailResponsable +"\", \"tipo\":\"" + this.tipo + "\", \"fechaInicio\":\"" + this.fechaInicio.replace(" ", "T") + "\", \"recurrencia\":\"" +this.recurrencia +"\", \"fechaFinRecurrencia\":\"" + this.fechaFinRecurrencia.replace(" ", "T") + "\", \"fechaFin\":\"" + this.fechaFin.replace(" ", "T") + "\" }";
    }
}
