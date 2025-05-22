package se.yrgo.spring.data;

import java.util.List;

import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

public interface TrainerDao {
    public List<Trainer> allTrainers();

    public Trainer findTrainerById(int trainerId);

    public Trainer findTrainerByName(String name);

    public void create(Trainer newTrainer);

    public void updateTrainer(Trainer trainer);

    public void delete(Trainer redundantTrainer);

    public List<Trainer> findTrainersByGymClass(String name);

    public void addClassToTrainer(int trainerId, int gymClassId);
    
    public List<GymClass> getAllTrainerClasses(int trainerId);
}
