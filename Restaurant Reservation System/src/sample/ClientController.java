package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample.Main.restaurant;

public class ClientController implements Initializable {
    public Label grilledchickenlabel;
    public Label greeksaladelabel;
    public Label applepielabel;
    public Label friedpotatoslabel;
    public Label moltencakelabel;
    public Label mushroomsouplabel;
    public Label beefsteaklabel;
    public Label grilledprice;
    public Label greekprice;
    public Label appleprice;
    public Label friedprice;
    public Label moltenprice;
    public Label mushroomprice;
    public Label beefprice;

    public Label info;
    FXMLLoader fxmlLoader;
    ObservableList<String> TableAreaList = FXCollections.observableArrayList("smoking", "non smoking");
    @FXML
    private Label Clientname;
    @FXML
    private ChoiceBox<String> TableArea;
    @FXML
    public TextField NumberOfSeats;

    public int MaximumNumberOfSeats = 0;
    private String dishes = "";
    private double totalPrice = 0.0;
    private double totalTax = 0.0;
    String ClientNameLogged;
    private int isfound = 0;


    @FXML
    private void initializebox() {
        TableArea.setItems(TableAreaList);
        TableArea.setValue("Smoking");
    }

    public void Clientinfo(String name, String role, String username, String password) {
        System.out.println(name + role + username + password);
        ClientNameLogged = name;
        System.out.println("Hello "+ClientNameLogged);
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


    public void Reserve() {
        for (Table table : restaurant.getTables().get(0).getTables()) {
            if (TableArea.getValue().equals("smoking") && !table.isSmoking()) {
                continue;
            }else if (TableArea.getValue().equals("non smoking") && table.isSmoking())
                continue;
            if (table.isState() && table.getNumber_of_seats() >= Integer.parseInt(NumberOfSeats.getText())) {
                table.setState(false);
                System.out.println(">>>>"+ClientNameLogged);
                table.setClientName(ClientNameLogged);
                table.setDishes(dishes);
                info.setText("Successfully Reserved");
                System.out.println(table.getDishes());
                System.out.println(table.getNumber());
                System.out.println(totalPrice);
                System.out.println(totalTax);
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                table.setTime(formattedDate);
                table.setTotalPrice(totalPrice + totalTax);
                System.out.println(totalPrice + totalTax);
                isfound =1;
                try {
                    Main.marshallData();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if (isfound == 0){
            info.setText("No Table Found");
        }
        if (MaximumNumberOfSeats < Integer.parseInt(NumberOfSeats.getText())){
            info.setText("Maximum Number Of Seats Exceeded");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializebox();
        for (Table table : restaurant.getTables().get(0).getTables()) {
            if (table.getNumber_of_seats() > MaximumNumberOfSeats) {
                MaximumNumberOfSeats = table.getNumber_of_seats();
            }
        }
        System.out.println(MaximumNumberOfSeats);
        grilledprice.setText("75");
        greekprice.setText("35");
        friedprice.setText("30");
        appleprice.setText("50");
        moltenprice.setText("60");
        mushroomprice.setText("60");
        beefprice.setText("80");


    }


    public void addDish(String dishname) {
        for (Dish dish : restaurant.getDishes().get(0).getDishes()) {
            if (dish.getName().equals(dishname)) {
                totalPrice = totalPrice + dish.getPrice();
                if (dish.getType().equals("main_course"))
                    totalTax = totalTax + dish.getPrice() * 0.15;
                else if (dish.getType().equals("desert"))
                    totalTax = totalTax + dish.getPrice() * 0.20;
                else if (dish.getType().equals("appetizer"))
                    totalTax = totalTax + dish.getPrice() * 0.10;
            }
        }
    }



    public void grilledchickenclick() {
        dishes = dishes + "Grilled Chicken\n";
        addDish("Grilled Chicken");
        grilledchickenlabel.setText(String.valueOf(Integer.parseInt(grilledchickenlabel.getText()) + 1));
    }

    public void applepieclick() {
        dishes = dishes + "Apple Pie\n";
        addDish("Apple Pie");
        applepielabel.setText(String.valueOf(Integer.parseInt(applepielabel.getText()) + 1));
    }

    public void Moltencakeclick() {
        dishes = dishes + "Molten Cake\n";
        addDish("Molten Cake");
        moltencakelabel.setText(String.valueOf(Integer.parseInt(moltencakelabel.getText()) + 1));
    }

    public void mushroomClick() {
        dishes = dishes + "Mushroom soup\n";
        addDish("Mushroom soup");
        mushroomsouplabel.setText(String.valueOf(Integer.parseInt(mushroomsouplabel.getText()) + 1));
    }

    public void potatoClick() {
        dishes = dishes + "Fried Potatos\n";
        addDish("Fried Potatos");
        friedpotatoslabel.setText(String.valueOf(Integer.parseInt(friedpotatoslabel.getText()) + 1));
    }

    public void beefClick() {
        dishes = dishes + "Beef Steak\n";
        addDish("Beef Steak");
        beefsteaklabel.setText(String.valueOf(Integer.parseInt(beefsteaklabel.getText()) + 1));
    }

    public void saladClick() {
        dishes = dishes + "Greek Salade\n";
        addDish("Greek Salade");
        greeksaladelabel.setText(String.valueOf(Integer.parseInt(greeksaladelabel.getText()) + 1));
    }

    public void reset() {
        totalTax = 0.0;
        totalPrice = 0.0;
        grilledchickenlabel.setText("0");
        applepielabel.setText("0");
        moltencakelabel.setText("0");
        mushroomsouplabel.setText("0");
        greeksaladelabel.setText("0");
        beefsteaklabel.setText("0");
        friedpotatoslabel.setText("0");
        dishes = "";
    }
}
