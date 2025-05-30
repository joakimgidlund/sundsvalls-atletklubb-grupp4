package se.yrgo.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

/**
 * The domain class for the customer entity.
 * 
 * @author anomalin
 * 
 */

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String customerId;
    private String name;
    private String email;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<GymClass> classes;

    public Customer() {
    }

    /**
     * Constructor for creating a customer.
     * Initializes an empty list of classes when created. 
     * 
     * @param customerId the customer ID
     * @param name the customer name
     * @param email the customer email
     */
    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.classes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<GymClass> getClasses() {
        return classes;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClasses(List<GymClass> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Customer: " + customerId + ", name: " + name;
    }

    public void addClassToCustomer(GymClass newClass) {
        classes.add(newClass);
    }

}
