package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static sample.Main.restaurant;

public class CookerController {
    @FXML
    public TableView list;
    @FXML
    private Label Cookername;

    @FXML
    void initialize(){
        for (Table table : restaurant.getTables().get(0).getTables()){
            list.getItems().add(table);
        }
    }


    public void Cookerinfo(String name, String role, String username, String password) {
        System.out.println(name + role + username + password);
        Cookername.setText("Welcome Mr. " + name);
    }


    public void LogoutButton() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            Scene scene2 = new Scene(root1);
            stage.setScene(scene2);
            stage.setResizable(false); //Resize window = false;
            scene2.getStylesheets().add(String.valueOf(getClass().getResource("Login.css")));
            stage.show();
            /*ClientController clientController = fxmlLoader.getController();
            clientController.Clientinfo(user.getName(),user.getRole(),user.getUsername(),user.getPassword());*/
        } catch (Exception e) {
            System.out.println("Error Loading Window");
        }
    }
}
