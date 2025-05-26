package se.yrgo.spring.controllers;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

public class Results {
    private String id;
    private String name;
    private String price;

    private GymClass gClass;
    private Customer customer;
    private Trainer trainer;

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
        if(o.getClass() == GymClass.class) {
            this.gClass = (GymClass)o;
        }

        if(o.getClass() == Customer.class) {
            this.customer = (Customer)o;
        }

        if(o.getClass() == Trainer.class) {
            this.trainer = (Trainer)o;
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

}
