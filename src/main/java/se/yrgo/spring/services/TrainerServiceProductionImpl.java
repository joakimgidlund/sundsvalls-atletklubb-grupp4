package se.yrgo.spring.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.TrainerDao;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

@Transactional
@Service("trainerService")
public class TrainerServiceProductionImpl implements TrainerService {

    TrainerDao trainerDao;

    public TrainerServiceProductionImpl(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public List<Trainer> allTrainers() {
        return trainerDao.allTrainers();
    }

    @Override
    public Trainer findTrainerById(int trainerId) {
        return trainerDao.findTrainerById(trainerId);
    }

    @Override
    public Trainer findTrainerByName(String name) {
        return trainerDao.findTrainerByName(name);
    }

    @Override
    public void create(Trainer newTrainer) {
        trainerDao.create(newTrainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerDao.updateTrainer(trainer);
    }

    @Override
    public void delete(Trainer redundantTrainer) {
        trainerDao.delete(redundantTrainer);
    }

    @Override
    public List<Trainer> findTrainersByGymClass(String name) {
        return trainerDao.findTrainersByGymClass(name);
    }

    @Override
    public void addClassToTrainer(int trainerId, int gymClassId) {
        trainerDao.addClassToTrainer(trainerId, gymClassId);
    }

    @Override
    public List<GymClass> getAllTrainerClasses(int trainerId) {
        return trainerDao.getAllTrainerClasses(trainerId);
    }

}
