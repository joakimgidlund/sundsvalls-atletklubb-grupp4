package se.yrgo.spring.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * The client mostly just starts our JavaFX application. 
 * Application logic can be found in MainController.java.
 * 
 */
public class Client extends Application {

    /**
     * JavaFX method which builds our stage (what we see).
     */
    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new StackPane(), 520, 480);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("/fxml/main.fxml"));
            Parent root = fxmlLoader.load();

            scene.setRoot(root);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }

        stage.setTitle("Sundsvalls Atletklubb - SAK");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}