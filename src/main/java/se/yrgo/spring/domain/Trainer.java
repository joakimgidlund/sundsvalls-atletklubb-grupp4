package se.yrgo.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trainer {
    @Id
    @GeneratedValue
    private int id;
    private String trainerId;
    private String name;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
    private List<GymClass> gymClassesTrainers;

    public Trainer() {
    }

    public Trainer(String trainerId, String name) {
        this.trainerId = trainerId;
        this.name = name;
        this.gymClassesTrainers = new ArrayList<>();
    }

    public String getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public List<GymClass> getGymClassesTrainers() {
        return gymClassesTrainers;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGymClassToTrainer(GymClass gymClass) {
        this.gymClassesTrainers.add(gymClass);
    }

    @Override
    public String toString() {
        return "Trainer: " + this.trainerId + ", name: " + this.name;
    }

}
