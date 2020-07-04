package sample;

public class Client extends User {
    public Client(String name , String username , String password , String role) {
        this.setUsername(username);
        this.setName(name);
        this.setPassword(password);
        this.setRole(role);
    }
}
