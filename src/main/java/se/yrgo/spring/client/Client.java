package se.yrgo.spring.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import se.yrgo.spring.domain.*;
import se.yrgo.spring.services.*;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client extends Application {

    @Override
    public void start(Stage stage) {
        // String javaVersion = System.getProperty("java.version");
        // String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Sundsvalls Atletklubb");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("production-application.xml");

        try {
            CustomerService customerService = context.getBean("customerService", CustomerService.class);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            context.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}