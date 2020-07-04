package sample;

public class Manager extends User {
    public Manager(String name , String username , String password , String role) {
        this.setUsername(username);
        this.setName(name);
        this.setPassword(password);
        this.setRole(role);
    }
}
