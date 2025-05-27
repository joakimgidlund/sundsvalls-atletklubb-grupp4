package se.yrgo.spring.controllers;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import se.yrgo.spring.data.RecordNotFoundException;
import se.yrgo.spring.domain.*;
import se.yrgo.spring.services.*;

/**
 * Handles all GUI input. Currently very unorganized but works.
 * Linked together with main.fxml in resources.fxml with the @FXML-tag.
 * The app is designed with a MenuBar that contains all controls that manage
 * the database and a text area that outputs results. GUI designed with
 * the tool SceneBuilder.
 * 
 * @author: joakimgidlund, anomalin
 * 
 */
public class MainController {

    @FXML
    TextArea resultArea;

    ClassPathXmlApplicationContext context;
    GymClassService gymClassService;
    CustomerService customerService;
    TrainerService trainerService;

    /**
     * Instantiates a context and all services to make sure we only do it once for
     * performance.
     * This is called as soon as the app starts which means we are ready to
     * manipulate the database
     * instantly.
     * 
     */
    public MainController() {
        context = new ClassPathXmlApplicationContext("production-application.xml");
        gymClassService = context.getBean("gymClassService", GymClassService.class);
        customerService = context.getBean("customerService", CustomerService.class);
        trainerService = context.getBean("trainerService", TrainerService.class);
    }

    /**
     * This happens when the app closes. Makes sure we close the database context.
     * 
     * @param actionEvent contains all information about the item that was pressed.
     * 
     */
    @FXML
    private void exitAction(ActionEvent actionEvent) {
        context.close();
        javafx.application.Platform.exit();
    }

    /**
     * Clears the text area which displays all the results of all database
     * operations.
     * 
     * @param actionEvent
     * 
     */
    @FXML
    private void clearAction(ActionEvent actionEvent) {
        resultArea.clear();
    }

    /**
     * Generates mock data so we have something to work with.
     * 
     * @param actionEvent
     * 
     */
    @FXML
    private void generateData(ActionEvent actionEvent) {
        resultArea.appendText("Generating data...\n");
        gymClassService.registerNewClass(new GymClass("1", "Bootcamp", 250));
        gymClassService.registerNewClass(new GymClass("2", "Kickboxing", 250));
        gymClassService.registerNewClass(new GymClass("3", "HIIT Circuit", 250));
        gymClassService.registerNewClass(new GymClass("4", "Iron Body", 400));
        gymClassService.registerNewClass(new GymClass("5", "Boxing Bootcamp", 700));
        gymClassService.registerNewClass(new GymClass("6", "Sweat & Shred", 400));

        customerService.newCustomer(new Customer("001", "Joakim Gidlund", "joakim@mail.com"));
        customerService.newCustomer(new Customer("002", "Peter Hjelm", "peter@mail.com"));
        customerService.newCustomer(new Customer("003", "Dennis Duong", "dennis@mail.com"));
        customerService.newCustomer(new Customer("004", "Malin Sundberg", "malin@mail.com"));
        customerService.newCustomer(new Customer("005", "Bosse Stenberg", "bosse@mail.com"));
        customerService.newCustomer(new Customer("006", "Petra Persson", "petra@mail.com"));
        customerService.newCustomer(new Customer("007", "James Bond", "james@mail.com"));
        customerService.newCustomer(new Customer("008", "Leif GW", "leif@mail.com"));
        customerService.newCustomer(new Customer("009", "Gunilla Fjellgren", "gunilla@mail.com"));

        trainerService.create(new Trainer("001", "Jonas Johansson"));
        trainerService.create(new Trainer("002", "Alex Svensson"));
        trainerService.create(new Trainer("003", "Roger Andersson"));
        trainerService.create(new Trainer("004", "Peter Forsberg"));
        trainerService.create(new Trainer("005", "Felicia Liljestrand"));
        trainerService.create(new Trainer("006", "Amanda Besson"));
        trainerService.create(new Trainer("007", "Erik Samuelsson"));

        resultArea.appendText("Done.\n");
    }

    /**
     * Lists all gym classes in the database. Prints a message if none were found.
     * 
     * @param actionEvent
     * 
     */
    @FXML
    private void listAllClasses(ActionEvent actionEvent) {
        try {
            List<GymClass> list = gymClassService.getAllGymClasses();

            if (list.isEmpty()) {
                resultArea.appendText("No classes found.\n");
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

    /**
     * Lists all customers in the database. Prints a message if none were found.
     * 
     * @param actionEvent
     * 
     */
    @FXML
    private void listAllCustomers(ActionEvent actionEvent) {
        try {
            List<Customer> list = customerService.getAllCustomers();

            if (list.isEmpty()) {
                resultArea.appendText("No customers found.\n");
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

    /**
     * Lists all trainers in the database. Prints a message if none were found.
     * 
     * @param actionEvent
     * 
     */
    @FXML
    private void listAllTrainers(ActionEvent actionEvent) {
        try {
            List<Trainer> list = trainerService.allTrainers();

            if (list.isEmpty()) {
                resultArea.appendText("No trainers found.\n");
            } else {
                resultArea.appendText("--Printing all trainers--\n");

                for (Trainer t : list) {
                    resultArea.appendText(t + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Function to handle adding customers to a gym class. 
     * Creates a new dialog window with two lists, one contains all gym classes
     * and the other all customers.
     * 
     * @param actionEvent
     * @throws RecordNotFoundException
     */
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
        classListView.setPrefWidth(300);

        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.getAllCustomers());
        ListView<Customer> customerListView = new ListView<>(customers);
        customerListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        customerListView.setPrefHeight(300);
        customerListView.setPrefWidth(300);

        GridPane grid = new GridPane();

        grid.add(classListView, 0, 0);
        grid.add(customerListView, 1, 0);
        pane.setContent(new VBox(8, grid));

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(classListView.getSelectionModel().getSelectedItem(),
                        customerListView.getSelectionModel().getSelectedItems());
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        optionalResults.ifPresent(results -> {
            for (Customer c : results.getCustomers()) {
                try {
                    gymClassService.registerClassOnCustomer(results.getgClass(), c);
                } catch (RecordNotFoundException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            resultArea.appendText("--Customer(s) registered to class--\n");
            resultArea.appendText(results.getgClass() + ":\n");
            for (Customer c : results.getCustomers()) {
                resultArea.appendText(c + "\n");
            }
        });
    }

    @FXML
    private void trainerClassListAction(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Add to list");
        dialog.setHeaderText("Select a trainer to add a class to.");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ObservableList<Trainer> trainers = FXCollections.observableArrayList(trainerService.allTrainers());
        ListView<Trainer> trainerListView = new ListView<>(trainers);
        trainerListView.setPrefHeight(300);
        trainerListView.setPrefWidth(300);

        ObservableList<GymClass> classes = FXCollections.observableArrayList(gymClassService.getAllGymClasses());
        ListView<GymClass> classListView = new ListView<>(classes);
        classListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        classListView.setPrefHeight(300);
        classListView.setPrefWidth(300);

        GridPane grid = new GridPane();

        grid.add(trainerListView, 0, 0);
        grid.add(classListView, 1, 0);
        pane.setContent(new VBox(8, grid));

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(trainerListView.getSelectionModel().getSelectedItem(),
                        classListView.getSelectionModel().getSelectedItems());
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        optionalResults.ifPresent(results -> {
            for (GymClass gc : results.getClassList()) {
                trainerService.addClassToTrainer(results.getTrainer(), gc);
            }

            resultArea.appendText("--Class(es) registered to trainer--\n");
            resultArea.appendText(results.getTrainer() + ":\n");
            for (GymClass gc : results.getClassList()) {
                resultArea.appendText(gc + "\n");
            }
        });
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

        optionalResults.ifPresent(results -> {
            if (results.getName().equals("ID")) {
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
        });
    }

    private void searchTrainerByName(Results results) {
        try {
            Trainer trainer = trainerService.findTrainerByName(results.getId());

            resultArea.appendText("--Found trainer--\n");
            resultArea.appendText(trainer + "\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchTrainerById(Results results) {
        try {
            Trainer trainer = trainerService.findTrainerById(results.getId());

            resultArea.appendText("--Found trainer--\n");
            resultArea.appendText(trainer + "\n");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchCustomerById(Results results) {
        try {
            Customer customer = customerService.findCustomerById(results.getId());

            resultArea.appendText("--Found customer--\n");
            resultArea.appendText(customer + ", email: " + customer.getEmail() + "\n");

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
                resultArea.appendText("--Found customers--\n");
                for (Customer c : foundCustomers) {
                    resultArea.appendText(c + ", email: " + c.getEmail() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void searchClassById(Results results) {
        try {
            GymClass foundClass = gymClassService.getGymClassById(results.getId());

            resultArea.appendText("--Found class--\n");
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
                resultArea.appendText("--Found classes--\n");
                for (GymClass gc : foundClasses) {
                    resultArea.appendText(gc + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void addAction(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Add entry to database");
        dialog.setHeaderText("Please fill all fields");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        MenuItem source = (MenuItem) actionEvent.getSource();
        String type = source.getId();

        UnaryOperator<Change> intFilter = change -> {
            String input = change.getText();
            if (input.matches("\\d") || input.matches("-") || input.isEmpty()) {
                return change;
            }
            return null;
        };

        UnaryOperator<Change> priceFilter = change -> {
            String input = change.getText();
            if (input.matches("\\d") || input.isEmpty()) {
                return change;
            }
            return null;
        };

        TextField idInput = new TextField();
        idInput.setPromptText("Enter ID...");
        idInput.setTextFormatter(new TextFormatter<>(intFilter));

        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter name...");

        TextField emailInput = new TextField();
        emailInput.setPromptText("Enter e-mail...");

        TextField priceInput = new TextField();
        priceInput.setPromptText("Enter price...");
        priceInput.setTextFormatter(new TextFormatter<>(priceFilter));

        pane.setContent(new VBox(8, idInput, nameInput, emailInput, priceInput));
        if (!type.equals("customerAdd")) {
            emailInput.setVisible(false);
            emailInput.setManaged(false);
        }

        if (!type.equals("classAdd")) {
            priceInput.setVisible(false);
            priceInput.setManaged(false);
        }

        Platform.runLater(idInput::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                switch (type) {
                    case "trainerAdd":
                        return new Results(new Trainer(idInput.getText(), nameInput.getText()));
                    case "customerAdd":
                        return new Results(new Customer(idInput.getText(), nameInput.getText(), emailInput.getText()));
                    default:
                        return new Results(new GymClass(idInput.getText(), nameInput.getText(),
                                Integer.parseInt(priceInput.getText())));
                }
            }
            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        optionalResults.ifPresent(results -> {
            switch (type) {
                case "trainerAdd" ->
                    createTrainer(results);
                case "customerAdd" ->
                    createCustomer(results);
                default ->
                    createGymClass(results);
            }
        });
    }

    private void createGymClass(Results results) {
        try {
            GymClass gClass = results.getgClass();

            gymClassService.registerNewClass(gClass);

            resultArea.appendText("--Added class to database--\n");
            resultArea
                    .appendText(gClass.getClassId() + " : " + gClass.getClassName() + " : " + gClass.getPrice() + "\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void createCustomer(Results results) {
        try {
            Customer customer = results.getCustomer();

            customerService.newCustomer(customer);

            resultArea.appendText("--Added customer to database--\n");
            resultArea.appendText(
                    customer.getCustomerId() + " : " + customer.getName() + " : " + customer.getEmail() + "\n");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void createTrainer(Results results) {
        try {
            Trainer trainer = results.getTrainer();

            trainerService.create(trainer);

            resultArea.appendText("--Added trainer to database--\n");
            resultArea.appendText(trainer.getTrainerId() + " : " + trainer.getName());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void deleteAction(ActionEvent actionEvent) throws CustomerNotFoundException, GymClassNotFoundException {
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
            if (button == ButtonType.OK) {
                Object o = deleteList.getSelectionModel().getSelectedItem();
                return new Results(o);
            }

            return null;
        });

        Optional<Results> optionalResults = dialog.showAndWait();

        optionalResults.ifPresent(results -> {
            if (results.getgClass() != null) {
                try {
                    gymClassService.deleteClassFromCatalogue(results.getgClass());
                } catch (GymClassNotFoundException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            } else if (results.getCustomer() != null) {
                try {
                    customerService.deleteCustomer(results.getCustomer());
                } catch (CustomerNotFoundException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            } else {
                trainerService.delete(results.getTrainer());
            }
            resultArea.appendText("--Deleted from database--\n");
            resultArea.appendText(results.getO().toString());
        });
    }

    @FXML
    private void editCustomerAction(ActionEvent actionEvent) {
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Customer edit");
        dialog.setHeaderText("Select a customer");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.getAllCustomers());
        ListView<Customer> customerListView = new ListView<>(customers);
        customerListView.setPrefHeight(300);
        customerListView.setPrefWidth(350);
        pane.setContent(customerListView);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return customerListView.getSelectionModel().getSelectedItem();
            }

            return null;
        });

        Optional<Customer> result = dialog.showAndWait();

        result.ifPresent(this::editDialog);

    }

    private void editDialog(Customer customer) {

        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Add new gym class");
        dialog.setHeaderText("Please fill all fields");

        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label nameLabel = new Label("Name");

        TextField nameInput = new TextField();
        nameInput.setText(customer.getName());
        TextField emailInput = new TextField();
        // emailInput.setText(customer.getEmail());

        pane.setContent(new VBox(8, nameLabel, nameInput));

        Platform.runLater(nameInput::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                customer.setName(nameInput.getText());
                // customer.setEmail(emailInput.getText());
                return customer;
            }
            return null;
        });

        Optional<Customer> optionalResults = dialog.showAndWait();
        if (optionalResults.isPresent()) {
            customerService.updateCustomer(optionalResults.get());
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
                resultArea.appendText("--Classes for " + customer.getName() + "--\n");
                StringBuilder sb = new StringBuilder();
                if (classes.isEmpty()) {
                    sb.append("No classes registered.");
                } else {
                    for (GymClass gClass : classes) {
                        sb.append(gClass.toString()).append("\n");
                    }
                }

                resultArea.appendText(sb.toString());

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        });

    }

    @FXML
    private void getGymClassAttendees(ActionEvent actionEvent) throws GymClassNotFoundException {
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
                resultArea.appendText("--Printing attendees for " + gymClass.getClassName() + "--\n");
                StringBuilder sb = new StringBuilder();
                if (attendees.isEmpty()) {
                    sb.append("No customers registered.");
                } else {
                    for (Customer attendee : attendees) {
                        sb.append(attendee.toString()).append("\n");
                    }
                }

                resultArea.appendText(sb.toString());

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        });

    }
}
