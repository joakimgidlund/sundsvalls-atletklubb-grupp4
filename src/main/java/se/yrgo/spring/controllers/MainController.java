package se.yrgo.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.swing.Action;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import se.yrgo.spring.domain.*;
import se.yrgo.spring.services.*;

public class MainController {

    @FXML
    TextArea resultArea;

    ClassPathXmlApplicationContext context;
    GymClassService gymClassService;
    CustomerService customerService;

    public MainController() {
        context = new ClassPathXmlApplicationContext("production-application.xml");
        gymClassService = context.getBean("gymClassService", GymClassService.class);
        customerService = context.getBean("customerService", CustomerService.class);
    }

    @FXML
    private void exitAction(ActionEvent actionEvent) {
        context.close();
        javafx.application.Platform.exit();
    }

    @FXML
    private void clearAction(ActionEvent actionEvent) {
        resultArea.clear();
    }

    @FXML
    private void generateData(ActionEvent actionEvent) {
        resultArea.appendText("Generating data...");
    }

    @FXML
    private void listAllClasses(ActionEvent actionEvent) {
        try {
            List<GymClass> list = gymClassService.getAllGymClasses();

            if (list.isEmpty()) {
                resultArea.appendText("No classes found\n");
            } else {
                resultArea.appendText("--Printing all classes--\n");

                for (GymClass gc : list) {
                    resultArea.appendText(gc + "\n");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void listAllCustomers(ActionEvent actionEvent) {
        try {
            List<Customer> list = customerService.getAllCustomers();

            if (list.isEmpty()) {
                resultArea.appendText("No customers found\n");
            } else {
                resultArea.appendText("--Printing all customers--\n");

                for (Customer c : list) {
                    resultArea.appendText(c + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void classCustomerListAction(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        // dialog.setWidth(250);
        dialog.setTitle("Add to list");
        dialog.setHeaderText("Select a class to add a customer to.");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ObservableList<GymClass> classes = FXCollections.observableArrayList(gymClassService.getAllGymClasses());
        ListView<GymClass> classListView = new ListView<>(classes);
        classListView.setPrefHeight(150);
        classListView.setPrefWidth(150);

        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.getAllCustomers());
        ListView<Customer> customerListView = new ListView<>(customers);
        customerListView.setPrefHeight(150);
        customerListView.setPrefWidth(150);

        GridPane grid = new GridPane();

        grid.add(classListView, 0, 0);
        grid.add(customerListView, 1, 0);
        pane.setContent(new VBox(8, grid));

        dialog.showAndWait();
    }

    @FXML
    private void classSearch(ActionEvent actionEvent) {
        MenuItem source = (MenuItem) actionEvent.getSource();
        String id = source.getId();

        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Search");
        dialog.setHeaderText("Please fill all fields");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ToggleGroup group = new ToggleGroup();
        RadioButton idRadio = new RadioButton("ID");
        RadioButton nameRadio = new RadioButton("Name");

        idRadio.setToggleGroup(group);
        idRadio.setSelected(true);
        nameRadio.setToggleGroup(group);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter search");
        pane.setContent(new VBox(8, idRadio, nameRadio, nameInput));

        Platform.runLater(nameInput::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                RadioButton selected = (RadioButton) group.getSelectedToggle();
                String radioValue = selected.getText();
                return new Results(nameInput.getText(), radioValue);
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        if (optionalResults.isPresent()) {
            if (optionalResults.get().name.equals("ID")) {
                if (id.equals("classSearch"))
                    searchClassById(optionalResults.get());
                else if (id.equals("customerSearch"))
                    searchCustomerById(optionalResults.get());
            } else {
                if (id.equals("classSearch"))
                    searchClassByName(optionalResults.get());
                else if(id.equals("customerSearch"))
                    searchCustomerByName(optionalResults.get());
            }
        }
    }

    private void searchCustomerById(Results results) {
        try {
            Customer customer = customerService.findCustomerById(results.id);

            resultArea.appendText("Found customer:\n");
            resultArea.appendText(customer + "\n");

        } catch (Exception e) {
            resultArea.appendText("No customer with ID: " + results.id + " found.\n");
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchCustomerByName(Results results) {
        try {
            List<Customer> foundCustomers = customerService.findCustomersByName(results.id);

            if (foundCustomers.isEmpty()) {
                resultArea.appendText("No customers with that name found.\n");
            } else {
                resultArea.appendText("Found customers:\n");
                for (Customer c : foundCustomers) {
                    resultArea.appendText(c + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchClassById(Results results) {
        try {
            GymClass foundClass = gymClassService.getGymClassById(results.id);

            resultArea.appendText("Found class:\n");
            resultArea.appendText(foundClass + "\n");

        } catch (Exception e) {
            resultArea.appendText("No classes with that ID found.\n");
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchClassByName(Results results) {
        try {
            List<GymClass> foundClasses = gymClassService.getGymClassByName(results.id);

            if (foundClasses.isEmpty()) {
                resultArea.appendText("No classes with that name found.");
            } else {
                resultArea.appendText("\nFound classes:\n");
                for (GymClass gc : foundClasses) {
                    resultArea.appendText(gc + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void addClassAction(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Add new gym class");
        dialog.setHeaderText("Please fill all fields");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label idLabel = new Label("ID");
        Label nameLabel = new Label("Name");
        Label priceLabel = new Label("Price");
        
        TextField idInput = new TextField();
        idInput.setPromptText("Enter ID");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter name");
        TextField priceInput = new TextField();
        priceInput.setPromptText("Enter price");

        pane.setContent(new VBox(8, idLabel, idInput, nameLabel, nameInput, priceLabel, priceInput));

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

    @FXML
    private void addCustomerAction(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Add new customer");
        dialog.setHeaderText("Please fill all fields");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField idInput = new TextField("Enter ID...");
        TextField nameInput = new TextField("Enter name...");
        pane.setContent(new VBox(8, idInput, nameInput));

        Platform.runLater(idInput::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(idInput.getText(), nameInput.getText());
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();
        optionalResults.ifPresent(this::createCustomer);
    }

    private static class Results {
        private String id;
        private String name;
        private String price;

        private GymClass gClass;
        private Customer customer;

        public Results(String id, String name, String price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public Results(String name) {
            this.name = name;
        }

        public Results(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Results(GymClass gClass, Customer customer) {
            this.gClass = gClass;
            this.customer = customer;
        }
    }

    private void createGymClass(Results results) {
        try {

            GymClass testClass = new GymClass(results.id, results.name, Integer.parseInt(results.price));

            gymClassService.registerNewClass(testClass);

            resultArea.appendText("Added class with ID: " + testClass.getClassId() + " to database.\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void createCustomer(Results results) {
        try {
            Customer customer = new Customer(results.id, results.name);

            customerService.newCustomer(customer);

            resultArea.appendText("Added customer with ID: " + customer.getCustomerId() + " to database.\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
