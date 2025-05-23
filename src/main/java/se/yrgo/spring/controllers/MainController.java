package se.yrgo.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.services.GymClassService;

public class MainController {

    @FXML
    private void handleButtonClick(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Add new gym class");
        dialog.setHeaderText("Please fill all fields");
        
        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        TextField idInput = new TextField("Enter ID...");
        TextField nameInput = new TextField("Enter name...");
        TextField priceInput = new TextField("Enter price...");
        pane.setContent(new VBox(8, idInput, nameInput, priceInput));
        
        Platform.runLater(idInput::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(idInput.getText(), nameInput.getText(), priceInput.getText());
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();
        optionalResults.ifPresent(this::createGymClass);
    }

    private static class Results {
        private String id;
        private String name;
        private String price;

        public Results(String id, String name, String price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    private void createGymClass(Results results) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("production-application.xml");
        try {
            GymClassService gymClassService = context.getBean("gymClassService", GymClassService.class);

            GymClass testClass = new GymClass(results.id, results.name, Integer.parseInt(results.price));

            gymClassService.registerNewClass(testClass);

            List<GymClass> list = gymClassService.getAllGymClasses();

            for (GymClass gc : list) {
                System.out.println(gc);
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            context.close();
        }
    }
}
