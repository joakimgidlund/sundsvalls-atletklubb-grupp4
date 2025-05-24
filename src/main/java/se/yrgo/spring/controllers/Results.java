package se.yrgo.spring.controllers;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

public class Results {
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

    
}
