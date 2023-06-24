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
    public String tipo;
    public String fechaInicio;
    public String fechaFin;
    
    public Evento(int id, String nombre, String descripcion, String tipo, String fechaInicio, String fechaFin){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
     @Override
    public String toString() {
        return  "{ \"id\": " + this.id + ", \"nombre\":\"" + this.nombre + "\", \"descripcion\": \"" +this.descripcion +"\", \"tipo\":\"" + this.tipo + "\", \"fechaInicio\":\"" + this.fechaInicio.replace(" ", "T") + "\", \"fechaFin\":\"" + this.fechaFin.replace(" ", "T") + "\" }";
    }
}
