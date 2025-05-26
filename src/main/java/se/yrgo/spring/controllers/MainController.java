package se.yrgo.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import se.yrgo.spring.data.RecordNotFoundException;
import se.yrgo.spring.domain.*;
import se.yrgo.spring.services.*;

public class MainController {

    @FXML
    TextArea resultArea;

    ClassPathXmlApplicationContext context;
    GymClassService gymClassService;
    CustomerService customerService;
    TrainerService trainerService;

    public MainController() {
        context = new ClassPathXmlApplicationContext("production-application.xml");
        gymClassService = context.getBean("gymClassService", GymClassService.class);
        customerService = context.getBean("customerService", CustomerService.class);
        trainerService = context.getBean("trainerService", TrainerService.class);
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
        resultArea.appendText("Generating data...\n");
        gymClassService.registerNewClass(new GymClass("1", "Bootcamp", 250));
        gymClassService.registerNewClass(new GymClass("2", "Kickboxing", 250));
        gymClassService.registerNewClass(new GymClass("3", "HIIT Circuit", 250));
        gymClassService.registerNewClass(new GymClass("4", "Iron Body", 400));
        gymClassService.registerNewClass(new GymClass("5", "Boxing Bootcamp", 700));
        gymClassService.registerNewClass(new GymClass("6", "Sweat & Shred", 400));

        customerService.newCustomer(new Customer("001", "Joakim Gidlund"));
        customerService.newCustomer(new Customer("002", "Peter Hjelm"));
        customerService.newCustomer(new Customer("003", "Dennis Duong"));
        customerService.newCustomer(new Customer("004", "Malin Sundberg"));
        customerService.newCustomer(new Customer("005", "Bosse Stenberg"));
        customerService.newCustomer(new Customer("006", "Petra Persson"));
        customerService.newCustomer(new Customer("007", "James Bond"));
        customerService.newCustomer(new Customer("008", "Leif GW"));
        customerService.newCustomer(new Customer("009", "Gunilla Fjellgren"));

        trainerService.create(new Trainer("001", "Jonas Johansson"));
        trainerService.create(new Trainer("002", "Alex Svensson"));
        trainerService.create(new Trainer("003", "Roger Andersson"));
        trainerService.create(new Trainer("004", "Peter Forsberg"));
        trainerService.create(new Trainer("005", "Felicia Liljestrand"));
        trainerService.create(new Trainer("006", "Amanda Besson"));
        trainerService.create(new Trainer("007", "Erik Samuelsson"));

        resultArea.appendText("Done.\n");
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
    private void listAllTrainers(ActionEvent actionEvent) {
        try {
            List<Trainer> list = trainerService.allTrainers();

            if (list.isEmpty()) {
                resultArea.appendText("No trainers found.\n");
            } else {
                resultArea.appendText("--Printing all trainers---\n");

                for (Trainer t : list) {
                    resultArea.appendText(t + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void classCustomerListAction(ActionEvent actionEvent) throws RecordNotFoundException {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Add to list");
        dialog.setHeaderText("Select a class to add a customer to.");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ObservableList<GymClass> classes = FXCollections.observableArrayList(gymClassService.getAllGymClasses());
        ListView<GymClass> classListView = new ListView<>(classes);
        classListView.setPrefHeight(300);
        classListView.setPrefWidth(200);

        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.getAllCustomers());
        ListView<Customer> customerListView = new ListView<>(customers);
        customerListView.setPrefHeight(300);
        customerListView.setPrefWidth(200);

        GridPane grid = new GridPane();

        grid.add(classListView, 0, 0);
        grid.add(customerListView, 1, 0);
        pane.setContent(new VBox(8, grid));

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(classListView.selectionModelProperty().get().getSelectedItem(),
                        customerListView.selectionModelProperty().get().getSelectedItem());
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        if (optionalResults.isPresent()) {
            gymClassService.registerClassOnCustomer(optionalResults.get().getgClass(),
                    optionalResults.get().getCustomer());

            resultArea.appendText(optionalResults.get().getgClass() + ": "
                    + optionalResults.get().getgClass().getAttendees().toString() + "\n");
        }
    }

    @FXML
    private void trainerClassListAction(ActionEvent actionEvent) throws RecordNotFoundException {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Add to list");
        dialog.setHeaderText("Select a trainer to add a class to.");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ObservableList<Trainer> trainers = FXCollections.observableArrayList(trainerService.allTrainers());
        ListView<Trainer> trainerListView = new ListView<>(trainers);
        trainerListView.setPrefHeight(300);
        trainerListView.setPrefWidth(200);

        ObservableList<GymClass> classes = FXCollections.observableArrayList(gymClassService.getAllGymClasses());
        ListView<GymClass> classListView = new ListView<>(classes);
        classListView.setPrefHeight(300);
        classListView.setPrefWidth(200);

        GridPane grid = new GridPane();

        grid.add(trainerListView, 0, 0);
        grid.add(classListView, 1, 0);
        pane.setContent(new VBox(8, grid));

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(trainerListView.selectionModelProperty().get().getSelectedItem(),
                        classListView.selectionModelProperty().get().getSelectedItem());
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        if (optionalResults.isPresent()) {
            trainerService.addClassToTrainer(optionalResults.get().getTrainer(), optionalResults.get().getgClass());

            resultArea.appendText(optionalResults.get().getTrainer().getName() + ": "
                    + optionalResults.get().getgClass() + "\n");
        }
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
            if (optionalResults.get().getName().equals("ID")) {
                if (id.equals("classSearch"))
                    searchClassById(optionalResults.get());
                else if (id.equals("customerSearch"))
                    searchCustomerById(optionalResults.get());
                else if (id.equals("trainerSearch"))
                    searchTrainerById(optionalResults.get());
            } else {
                if (id.equals("classSearch"))
                    searchClassByName(optionalResults.get());
                else if (id.equals("customerSearch"))
                    searchCustomerByName(optionalResults.get());
                else if (id.equals("trainerSearch")) {
                    searchTrainerByName(optionalResults.get());
                }
            }
        }
    }

    private void searchTrainerByName(Results results) {
        try {
            Trainer trainer = trainerService.findTrainerByName(results.getId());

            resultArea.appendText("Found trainer:\n" + trainer + "\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchTrainerById(Results results) {
        try {
            Trainer trainer = trainerService.findTrainerById(results.getId());

            resultArea.appendText("Found trainer:\n" + trainer + "\n");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchCustomerById(Results results) {
        try {
            Customer customer = customerService.findCustomerById(results.getId());

            resultArea.appendText("Found customer:\n" + customer + "\n");

        } catch (Exception e) {
            resultArea.appendText("No customer with ID: " + results.getId() + " found.\n");
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchCustomerByName(Results results) {
        try {
            List<Customer> foundCustomers = customerService.findCustomersByName(results.getId());

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
            GymClass foundClass = gymClassService.getGymClassById(results.getId());

            resultArea.appendText("Found class:\n");
            resultArea.appendText(foundClass + "\n");

        } catch (Exception e) {
            resultArea.appendText("No classes with that ID found.\n");
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchClassByName(Results results) {
        try {
            List<GymClass> foundClasses = gymClassService.getGymClassByName(results.getId());

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
    private void addAction(ActionEvent actionEvent) {
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

        MenuItem source = (MenuItem) actionEvent.getSource();
        if (source.getId().equals("trainerAdd")) {
            optionalResults.ifPresent(this::createTrainer);
        } else {
            optionalResults.ifPresent(this::createCustomer);
        }
    }

    private void createGymClass(Results results) {
        try {

            GymClass testClass = new GymClass(results.getId(), results.getName(), Integer.parseInt(results.getPrice()));

            gymClassService.registerNewClass(testClass);

            resultArea.appendText("Added class with ID: " + testClass.getClassId() + " to database.\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void createCustomer(Results results) {
        try {
            Customer customer = new Customer(results.getId(), results.getName());

            customerService.newCustomer(customer);

            resultArea.appendText("Added customer with ID: " + customer.getCustomerId() + " to database.\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void createTrainer(Results results) {
        try {
            Trainer trainer = new Trainer(results.getId(), results.getName());

            trainerService.create(trainer);

            resultArea.appendText("Added trainer with ID: " + trainer.getTrainerId() + " to database.\n");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void deleteAction(ActionEvent actionEvent) throws CustomerNotFoundException {
        MenuItem mItem = (MenuItem) actionEvent.getSource();
        String type = mItem.getId();

        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Delete");
        dialog.setHeaderText("Select an item to delete.");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        @SuppressWarnings("rawtypes")
        ObservableList<?> items;

        if (type.equals("classDelete")) {
            items = FXCollections.observableArrayList(gymClassService.getAllGymClasses());
        } else if (type.equals("customerDelete")) {
            items = FXCollections.observableArrayList(customerService.getAllCustomers());
        } else {
            items = FXCollections.observableArrayList(trainerService.allTrainers());
        }

        ListView<?> deleteList = new ListView<>(items);

        deleteList.setPrefHeight(300);
        deleteList.setPrefWidth(200);

        pane.setContent(new VBox(8, deleteList));

        dialog.setResultConverter((ButtonType button) -> {
            if(button == ButtonType.OK) {
                Object o = deleteList.selectionModelProperty().get().getSelectedItem();
                return new Results(o);
            }

            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        if(optionalResults.isPresent()) {
            if(optionalResults.get().getgClass() != null) {
                gymClassService.deleteClassFromCatalogue(optionalResults.get().getgClass());
            } else if(optionalResults.get().getCustomer() != null) {
                customerService.deleteCustomer(optionalResults.get().getCustomer());
            } else {
                trainerService.delete(optionalResults.get().getTrainer());
            }
        }
    }

    @FXML
    private void getCustomerClasses(ActionEvent actionEvent) throws CustomerNotFoundException {
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Customer class viewer");
        dialog.setHeaderText("Select a customer");
        
        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.getAllCustomers());
        ListView<Customer> customerListView = new ListView<>(customers);
        customerListView.setPrefHeight(300);
        customerListView.setPrefWidth(350);
        pane.setContent(customerListView);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return customerListView.getSelectionModel().getSelectedItem();
            }
            return null;
        });

        Optional<Customer> result = dialog.showAndWait();

        result.ifPresent(customer -> {
            try {
                List<GymClass> classes = customerService.getAllCustomerClasses(customer.getCustomerId());
                resultArea.appendText("Classes for " + customer.getName() + ":\n");
                StringBuilder sb = new StringBuilder();
                for (GymClass gClass : classes) {
                    sb.append(gClass.toString()).append("\n");
                }
                if (classes.isEmpty()) {
                    sb.append("No classes registered.");
                }

                resultArea.appendText(sb.toString());

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        });

    }

    @FXML
    private void getGymClassAttendees (ActionEvent actionEvent) throws GymClassNotFoundException {
        Dialog<GymClass> dialog = new Dialog<>();
        dialog.setTitle("Gymclass attendees viewer");
        dialog.setHeaderText("Select a gymclass");
        
        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ObservableList<GymClass> gClasses = FXCollections.observableArrayList(gymClassService.getAllGymClasses());
        ListView<GymClass> gClassListView = new ListView<>(gClasses);
        gClassListView.setPrefHeight(300);
        gClassListView.setPrefWidth(200);
        pane.setContent(gClassListView);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return gClassListView.getSelectionModel().getSelectedItem();
            }
            return null;
        });

        Optional<GymClass> result = dialog.showAndWait();

        result.ifPresent(gymClass -> {
            try {
                List<Customer> attendees = gymClassService.getAllCustomers(gymClass.getClassId());
                resultArea.appendText("Attendees for " + gymClass.getClassName() + ":\n");
                StringBuilder sb = new StringBuilder();
                for (Customer attendee : attendees) {
                    sb.append(attendee.toString()).append("\n");
                }
                if (attendees.isEmpty()) {
                    sb.append("No customers registered.");
                }

                resultArea.appendText(sb.toString());

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        });

    }
}
