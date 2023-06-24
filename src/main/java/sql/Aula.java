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
    public int aforo;
    
    public Aula(int id, String nombre, int aforo){
        this.id = id;
        this.nombre = nombre;
        this.aforo = aforo;
    }
    
     @Override
    public String toString() {
        return  "{ \"id\": " + this.id + ", \"nombre\":\"" + this.nombre + "\", \"aforo\": " +this.aforo +"}";
    }
}
