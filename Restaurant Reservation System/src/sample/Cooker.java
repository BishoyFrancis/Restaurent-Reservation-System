package sample;

public class Cooker extends User {
    public Cooker(String name , String username , String password , String role) {
        this.setUsername(username);
        this.setName(name);
        this.setPassword(password);
        this.setRole(role);
    }
}
