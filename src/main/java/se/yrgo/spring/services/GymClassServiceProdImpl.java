package se.yrgo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.GymClassDao;
import se.yrgo.spring.data.RecordNotFoundException;
import se.yrgo.spring.domain.GymClass;

@Transactional
@Service("gymClassService")
public class GymClassServiceProdImpl implements GymClassService {

    GymClassDao gymClassDao;

    @Autowired
    public GymClassServiceProdImpl(GymClassDao gymClassDao) {
        this.gymClassDao = gymClassDao;
    }

    @Override
    public List<GymClass> getAllGymClasses() {
        return gymClassDao.allGymClasses();
    }

    @Override
    public GymClass getGymClassById(String classId) {
        return gymClassDao.findById(classId);
    }

    @Override
    public GymClass getGymClassByName(String className) throws GymClassNotFoundException {
        try {
            return gymClassDao.findByName(className);
        } catch (RecordNotFoundException e) {
            throw new GymClassNotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteClassFromCatalogue(GymClass oldClass) {
        gymClassDao.delete(oldClass);
    }

    @Override
    public void registerNewClass(GymClass newClass) {
        gymClassDao.create(newClass);
    }

    @Override
    public List<GymClass> getGymClassesByTrainer(String trainer) throws GymClassNotFoundException {
        try {
            return gymClassDao.findGymClassesByTrainer(trainer);
        } catch (RecordNotFoundException e) {
            throw new GymClassNotFoundException(e.getMessage());
        }
    }
}
