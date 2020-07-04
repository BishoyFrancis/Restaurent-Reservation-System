package sample;

public class Waiter extends User {
    public Waiter(String name , String username , String password , String role) {
        this.setUsername(username);
        this.setName(name);
        this.setPassword(password);
        this.setRole(role);
    }
}
