package se.yrgo.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trainer {
    @Id
    @GeneratedValue
    private int trainerId;
    private String name;
    
    @OneToMany
    private List<GymClass> gymClasses = new ArrayList<>();

    public Trainer() {
    }

    public Trainer(int trainerId, String name) {
        this.trainerId = trainerId;
        this.name = name;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public List<GymClass> getGymClasses() {
        return gymClasses;
    }

    @Override
    public String toString() {
        return "Trainer [trainerId=" + trainerId + ", name=" + name + "GymClasses= " + gymClasses + "]";
    }

}
