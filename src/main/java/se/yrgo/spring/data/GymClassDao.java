package se.yrgo.spring.data;

import se.yrgo.spring.domain.GymClass;
import java.util.List;

/**
 * This DAO handles all database operations related to the GymClass object.
 * 
 * @author joakimgidlund
 */
public interface GymClassDao {

    /**
     * Gets all registered gym classes from the database.
     * @return a list of all gym classes.
     */
    public List<GymClass> allGymClasses();

    /**
     * Searches the GymClass table for an object which matches
     * the provided classId.
     * 
     * @param classId a string containing a classId.
     * @return a matching GymClass object.
     * @throws RecordNotFoundException if no matching object can be found.
     */
    public GymClass findById(String classId) throws RecordNotFoundException;

    /**
     * Searches the database for any gym classes matching the provided name.
     * 
     * @param className string to search for.
     * @return a list of matching GymClasses. A name doesn't have to be unique.
     * @throws RecordNotFoundException if no matching gym class can be found.
     * 
     */
    public List<GymClass> findByName(String className) throws RecordNotFoundException;

    /**
     * Adds the provided GymClass object to the database.
     * 
     * @param newClass the object that should be added.
     */
    public void create(GymClass newClass);

    /**
     * Updates the GymClass table row matching the provided object.
     * 
     * @param classToUpdate
     * 
     */
    public void update(GymClass classToUpdate);

    /**
     * Deletes the row matching the provided object from the database.
     * @param oldClass
     * @throws RecordNotFoundException if the object can't be found.
     * 
     */
    public void delete(GymClass oldClass) throws RecordNotFoundException;
}
