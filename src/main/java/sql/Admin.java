/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

/**
 *
 * @author franb
 */
public class Admin {
    
    public int id;
    public String email;
    public String password;
    
     public Admin(int id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
     
     public String getEmail(){
         return this.email;
     }
     
     public String getPassword() {
         return this.password;
     }
     
    @Override
    public String toString() {
        return  "{   \"id\": " + this.id + ", \"email\":\"" + this.email + ",\"password\":\"" + this.password + "\"}";
    }
    
    
}
