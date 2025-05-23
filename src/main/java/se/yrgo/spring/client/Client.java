package se.yrgo.spring.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.services.CustomerService;
import se.yrgo.spring.services.GymClassService;

import java.io.IOException;
import java.io.UncheckedIOException;

public class Client extends Application {

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new StackPane(), 640, 480);
        // add an event handler to the label control
        try {
            // here we load the XML file and makes it into a node
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("/fxml/main.fxml"));
            Parent root = fxmlLoader.load();

            // then we set the root node to this new loaded node
            scene.setRoot(root);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}