package se.yrgo.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class GymClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String classId;
    private String className;

    // @ManyToOne
    // private Trainer trainer;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "classes")
    // @JoinTable(name = "customer_class")
    // @JoinColumn(name = "class_id")
    private List<Customer> attendees;

    private int price;

    public GymClass() {
    }

    public GymClass(String classId, String className, int price) {
        this.classId = classId;
        this.className = className;
        this.price = price;
        this.attendees = new ArrayList<>();
    }

    // public void allocateTrainer(Trainer trainer) {
    // this.trainer = trainer;
    // }

    @Override
    public String toString() {
        return "Class: " + this.classId + ", name: " + this.className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void addCustomerToClass(Customer customer) {
        this.attendees.add(customer);
        customer.addClassToCustomer(this);
    }

    public List<Customer> getAttendees() {
        return this.attendees;
    }

    // public void setGymClassAttendees(List<Customer> gymClassAttendees) {
    // this.gymClassAttendees = gymClassAttendees;
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classId == null) ? 0 : classId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GymClass other = (GymClass) obj;
        if (classId == null) {
            if (other.classId != null)
                return false;
        } else if (!classId.equals(other.classId))
            return false;
        return true;
    }
}
