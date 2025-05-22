package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.domain.GymClass;

public interface GymClassService {

    public List<GymClass> getAllGymClasses();

    public GymClass getGymClassById(String classId);

    public GymClass getGymClassByName(String className) throws GymClassNotFoundException;

    public void registerNewClass(GymClass newClass);

    public void deleteClassFromCatalogue(GymClass oldClass);

    public List<GymClass> getGymClassesByTrainer(String trainer) throws GymClassNotFoundException;
}
