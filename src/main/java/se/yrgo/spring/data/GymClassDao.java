package se.yrgo.spring.data;

import se.yrgo.spring.domain.GymClass;
import java.util.List;

public interface GymClassDao {

    public List<GymClass> allGymClasses();

    public GymClass findById(String classId);

    public GymClass findByName(String className) throws RecordNotFoundException;

    public void create(GymClass newClass);

    public void delete(GymClass oldClass);

    public List<GymClass> findGymClassesByTrainer(String trainer) throws RecordNotFoundException;
}
