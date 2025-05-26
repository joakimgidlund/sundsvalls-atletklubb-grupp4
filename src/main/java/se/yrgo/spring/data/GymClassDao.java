package se.yrgo.spring.data;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;
import java.util.List;

public interface GymClassDao {

    public List<GymClass> allGymClasses();

    public GymClass findById(String classId) throws RecordNotFoundException;

    public List<GymClass> findByName(String className) throws RecordNotFoundException;

    public void create(GymClass newClass);

    public void update(GymClass classToUpdate);

    public void delete(GymClass oldClass);

    public List<GymClass> findGymClassesByTrainer(String trainer) throws RecordNotFoundException;

    public List<Customer> getAttendees(String classId) throws RecordNotFoundException;
}
