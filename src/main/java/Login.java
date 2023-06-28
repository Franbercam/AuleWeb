import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        List<User> users = initializeUsers();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine(); //Sustituir por 
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        
        boolean loggedIn = LoginManager.login(users, username, password);
        
        if (loggedIn) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + username + "!");
        } else {
            System.out.println("Inicio de sesión fallido. Nombre de usuario o contraseña incorrectos.");
        }
    }
    
    private static List<User> initializeUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
        return users;
    }
    
    static class User {
        private String username;
        private String password;
        
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
    }
    
static class LoginManager {
    private static boolean loggedIn = false;
    
    public static boolean login(List<User> users, String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedIn = true;
                return true;
            }
        }
        return false;
    }
    
    public static void logout() {
        loggedIn = false;
    }
    
    public static boolean isLoggedIn() {
        return loggedIn;
    }
}
}