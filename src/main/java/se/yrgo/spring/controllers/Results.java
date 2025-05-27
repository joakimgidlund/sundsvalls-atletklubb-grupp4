package se.yrgo.spring.controllers;

import java.util.List;

import javafx.collections.ObservableList;
import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

/**
 * Results is a helper class with a lot of different constructors
 * to give us options when getting results from dialog windows.
 * A Results object basically just holds whatever we get from
 * a dialog window.
 */
public class Results {
    private String id;
    private String name;
    private String price;

    private GymClass gClass;
    private Customer customer;
    private Trainer trainer;

    private Object o;
    private List<GymClass> classList;
    private List<Customer> customers;

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

    public Results(Object o) {
        this.o = o;

        if (o.getClass() == GymClass.class) {
            this.gClass = (GymClass) o;
        }

        if (o.getClass() == Customer.class) {
            this.customer = (Customer) o;
        }

        if (o.getClass() == Trainer.class) {
            this.trainer = (Trainer) o;
        }
    }

    public Results(GymClass gClass) {
        this.gClass = gClass;
    }

    public Results(Customer customer) {
        this.customer = customer;
    }

    public Results(Trainer trainer) {
        this.trainer = trainer;
    }

    public Results(GymClass gClass, Customer customer) {
        this.gClass = gClass;
        this.customer = customer;
    }

    public Results(Trainer trainer, GymClass gClass) {
        this.trainer = trainer;
        this.gClass = gClass;
    }

    public Results(Trainer trainer, List<GymClass> classList) {
        this.trainer = trainer;
        this.classList = classList;
    }

    public Results(GymClass gClass, ObservableList<Customer> customers) {
        this.gClass = gClass;
        this.customers = customers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public GymClass getgClass() {
        return gClass;
    }

    public void setgClass(GymClass gClass) {
        this.gClass = gClass;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Trainer getTrainer() {
        return this.trainer;
    }

    public Object getO() {
        return o;
    }

    public List<GymClass> getClassList() {
        return classList;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
