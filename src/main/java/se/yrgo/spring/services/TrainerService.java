package se.yrgo.spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

@Service("trainerService")
public interface TrainerService {

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
