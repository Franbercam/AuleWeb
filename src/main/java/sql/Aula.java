/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

/**
 *
 * @author ldrak
 */
public class Aula {
    public int id;
    public String nombre;
    public String descripcion;
    public String ubicacion;
    public int aforo;
    public int numEnchufes;
    public int red;
    public Boolean tieneProyector;
    public Boolean tienePantallaMotorizada;
    public Boolean tienePantallaManual;
    public Boolean tieneSisAudio;
    public Boolean tienePC;
    public Boolean tieneMicIna;
    public Boolean tieneMicAla;
    public Boolean tieneRetroProy;
    public Boolean tieneWifi;
    
    public Aula(int id, String nombre, int aforo){
        this.id = id;
        this.nombre = nombre;
        this.aforo = aforo;
    }
    
    public Aula(int id, String nombre, String descripcion, String ubicacion, int aforo,int numEnchufes,int red, Boolean tieneProyector, Boolean tienePantallaMotorizada, Boolean tienePantallaManual, Boolean tieneSisAudio, 
            Boolean tienePC, Boolean tieneMicIna, Boolean tieneMicAla, Boolean tieneRetroProy, Boolean tieneWifi){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.aforo = aforo;
        this.numEnchufes = numEnchufes;
        this.red = red;
        this.tieneProyector = tieneProyector;
        this.tienePantallaMotorizada = tienePantallaMotorizada;
        this.tienePantallaManual = tienePantallaManual;
        this.tieneSisAudio = tieneSisAudio;
        this.tienePC = tienePC;
        this.tieneMicIna = tieneMicIna;
        this.tieneMicAla = tieneMicAla;
        this.tieneRetroProy = tieneRetroProy;
        this.tieneWifi = tieneWifi;
        
    }
    
     @Override
    public String toString() {
        return  "{ \"id\": " + this.id + ", \"nombre\":\"" + this.nombre + "\", \"aforo\": " +this.aforo +"}";
    }
    
    public String toStringComplete() {
        return  "{ \"id\": " + this.id + ", \"nombre\":\"" + this.nombre + "\", \"descripcion\":\"" + this.descripcion + "\",  \"ubicacion\":\"" + this.ubicacion + "\", \"aforo\": " + this.aforo + ","
                + " \"numEnchufes\": " + this.numEnchufes + ", \"red\": " + this.red + ", \"tieneProyector\":\"" + this.tieneProyector + "\","
            + " \"tienePantallaMotorizada\":\"" + this.tienePantallaMotorizada + "\", \"tienePantallaManual\":\"" + this.tienePantallaManual + "\", \"tieneSisAudio\":\"" + this.tieneSisAudio + "\","
            + " \"tienePC\":\"" + this.tienePC + "\", \"tieneMicIna\":\"" + this.tieneMicIna + "\", \"tieneMicAla\":\"" + this.tieneMicAla + "\", \"tieneRetroProy\":\"" + this.tieneRetroProy + "\",\"tieneWifi\":\"" + this.tieneWifi + "\"}";
    }
}
