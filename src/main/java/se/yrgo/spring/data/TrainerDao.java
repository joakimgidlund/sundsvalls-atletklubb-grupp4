package se.yrgo.spring.data;

import java.util.List;

import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

//This class defines how Trainer communicates with the database.
public interface TrainerDao {

//Here are all abstract methods to handle the database and to be implemented in the JPA impl. class.

    //List all the trainers that exists in the database.
    public List<Trainer> allTrainers();

    //Find a trainer by it's id.
    public Trainer findTrainerById(String trainerId);

    //Find a trainer by its name.
    public Trainer findTrainerByName(String name);

    //Create a completely new trainer.
    public void create(Trainer newTrainer);

    //Update an existing trainer.
    public void updateTrainer(Trainer trainer);

    //Delete an existing trainer.
    public void delete(Trainer redundantTrainer);

    //List all the trainers by their name.
    public List<Trainer> findTrainersByGymClass(String name);

    //Add a gym class to a certain trainer.
    public void addClassToTrainer(Trainer trainer, GymClass gymClass);
    
    //Get all gym classes that a trainer is linked to.
    public List<GymClass> getAllTrainerClasses(int trainerId);
}
