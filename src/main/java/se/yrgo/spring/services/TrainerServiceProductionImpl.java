package se.yrgo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.TrainerDao;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

/*This class is responsible for the method calls and transactions that is later forwarded to 
TrainerDaoJpaImp*/

@Transactional
@Service("trainerService")
public class TrainerServiceProductionImpl implements TrainerService {

    TrainerDao trainerDao;

    @Autowired
    public TrainerServiceProductionImpl(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public List<Trainer> allTrainers() {
        return trainerDao.allTrainers();
    }

    @Override
    public Trainer findTrainerById(String trainerId) {
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
    public void addClassToTrainer(Trainer trainer, GymClass gymClass) {
        trainerDao.addClassToTrainer(trainer, gymClass);
    }

    @Override
    public List<GymClass> getAllTrainerClasses(int trainerId) {
        return trainerDao.getAllTrainerClasses(trainerId);
    }

}
