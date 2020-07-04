package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Login Form");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("Login.css")));
        primaryStage.show();
    }

    public static Restaurant restaurant;

    public static void marshallData() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Marshaller   marshaller=jaxbContext.createMarshaller();
        marshaller.marshal(restaurant, new File("Restaurant.xml"));
    }
    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //Marshaller   marshaller=jaxbContext.createMarshaller();
        restaurant = (Restaurant) unmarshaller.unmarshal(new File("Restaurant.xml"));

        List tempList = new ArrayList();
        for (User user : restaurant.getUsers().get(0).getUsers()) {
            User newUser = null;
            if (user.getRole().equals("Manager")) {
                newUser = new Manager(user.getName(),user.getUsername(),user.getPassword(),user.getRole());
            }
            else if(user.getRole().equals("Cooker")){
                newUser = new Cooker(user.getName(),user.getUsername(),user.getPassword(),user.getRole());
            }
            else if(user.getRole().equals("Waiter")){
                newUser = new Waiter(user.getName(),user.getUsername(),user.getPassword(),user.getRole());
            }
            else if(user.getRole().equals("Client")){
                newUser = new Client(user.getName(),user.getUsername(),user.getPassword(),user.getRole());
            }
            tempList.add(newUser);
        }
        restaurant.getUsers().get(0).setUsers(tempList);
        /*for (Table table : restaurant.getTables().get(0).getTables()) {
            System.out.println(table.getNumber() + " " + table.getNumber_of_seats() + " " + table.isSmoking());
        }
        for (User user : restaurant.getUsers().get(0).getUsers()) {
            System.out.println(user.getName() + " " + user.getPassword() + " " + user.getRole() + " " + user.getUsername());
        }
        for (Dish dish : restaurant.getDishes().get(0).getDishes()) {
            System.out.println(dish.getName() + " " + dish.getPrice() + " " + dish.getType());
        }*/

        launch(args);
    }
}
