package se.yrgo.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String customerId;
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    // @JoinTable(name = "customer_class")
    // @JoinColumn(name = "customer_id")
    private List<GymClass> classes;

    public Customer() {
    }

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
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

    public List<GymClass> getClasses() {
        return classes;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
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
