package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static sample.Main.restaurant;

public class Controller {
    @FXML
    public PasswordField password;

    @FXML
    public TextField username;
    @FXML
    private Label usernamewarning;
    @FXML
    private Label passwordwarning;
    @FXML
    private Label signinlabel;

    public String USERNAME;
    public String PASSWORD;

    public void password() {
        System.out.println(password.getText());
    }

    public void username() {
        System.out.println(username.getText());
    }


    public void Validation() {
        if (username.getText() == null || username.getText().trim().isEmpty()) {
            usernamewarning.setText("Username Field Empty!");
        }
        if (password.getText() == null || password.getText().trim().isEmpty()) {
            passwordwarning.setText("Password Field Empty!");
        }
    }


    public void Login() {
        int found = 0;
        Validation();
        USERNAME = username.getText();
        PASSWORD = password.getText();
        for (User user : restaurant.getUsers().get(0).getUsers()) {
            if (USERNAME.equals(user.getUsername()) && PASSWORD.equals(user.getPassword())) {
                System.out.println("Successfully logged in Mr." + user.getName());
                //System.out.println(user.getRole());
                found = 1;
                if (user.getRole().equals("Client")) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Client");
                        Scene scene2 = new Scene(root1);
                        stage.setScene(scene2);
                        stage.setResizable(false); //Resize window = false;
                        //scene2.getStylesheets().add(String.valueOf(getClass().getResource("sample.ClientController.css")));
                        stage.show();
                        ClientController clientController = fxmlLoader.getController();
                        clientController.Clientinfo(user.getName(),user.getRole(),user.getUsername(),user.getPassword());
                    } catch (Exception e) {
                        System.out.println("Error Loading Window");
                    }
                }
                if (user.getRole().equals("Manager")) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Manager.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Manager");
                        Scene scene2 = new Scene(root1);
                        stage.setScene(scene2);
                        stage.setResizable(false); //Resize window = false;
                        //scene2.getStylesheets().add(String.valueOf(getClass().getResource("sample.ClientController.css")));
                        stage.show();
                        ManagerController managerController = fxmlLoader.getController();
                        managerController.Managerinfo(user.getName(),user.getRole(),user.getUsername(),user.getPassword());
                    } catch (Exception e) {
                        System.out.println("Error Loading Window");
                    }
                }
                if (user.getRole().equals("Cooker")) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cooker.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Cooker");
                        Scene scene2 = new Scene(root1);
                        stage.setScene(scene2);
                        stage.setResizable(false); //Resize window = false;
                        //scene2.getStylesheets().add(String.valueOf(getClass().getResource("client.css")));
                        stage.show();
                        CookerController cookerController = fxmlLoader.getController();
                        cookerController.Cookerinfo(user.getName(),user.getRole(),user.getUsername(),user.getPassword());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                if (user.getRole().equals("Waiter")) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Waiter.fxml"));
                        Parent root2 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Waiter");
                        Scene scene3 = new Scene(root2);
                        stage.setScene(scene3);
                        stage.setResizable(false); //Resize window = false;
                        //scene2.getStylesheets().add(String.valueOf(getClass().getResource("sample.ClientController.css")));
                        stage.show();
                        WaiterController waiterController = fxmlLoader.getController();
                        waiterController.Waiterinfo(user.getName(),user.getRole(),user.getUsername(),user.getPassword());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                break;
            } else if (USERNAME.compareTo(user.getUsername()) == 0 && !PASSWORD.equals(user.getPassword())) {
                System.out.println("Wrong password");
                signinlabel.setText("WRONG PASSWORD!");
                password.setText("");
                signinlabel.setText("WRONG PASSWORD");
                found =1;
                break;
            }
        }
        if (found == 0) {
            signinlabel.setText("USER NOT FOUND!");
        }

        /*System.out.println("Trying to login ... please wait");

        System.out.println("Logged in Successfully");
        for (User user : restaurant.getUsers().get(0).getUsers()) {
            System.out.println(user.getName() + " " + user.getPassword() + " " + user.getRole() + " " + user.getUsername());
        }*/
    }

}

