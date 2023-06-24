/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

/**
 *
 * @author ldrak
 */
public class Deparment {

    public int id;
    public String nombre;

    public Deparment(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return  "{ \"id\": " + this.id + ", \"nombre\":\"" + this.nombre + "\"}";
    }
}
