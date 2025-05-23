package se.yrgo.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
    
    @OneToMany
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

        public void addGymClassToTrainer(GymClass gymClass) {
        this.gymClassesTrainers.add(gymClass);
    }

    @Override
    public String toString() {
        return "Trainer [trainerId=" + trainerId + ", name=" + name + "GymClasses= " + gymClassesTrainers + "]";
    }

}
