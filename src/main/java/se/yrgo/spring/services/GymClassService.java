package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

/**
 * Interface detailing a service making calls to the GymClassDao.
 * 
 * @author joakimgidlund
 */
public interface GymClassService {

    /**
     * Calls the dao to fetch all gym classes.
     * 
     * @return as list of all gym classes in the database.
     */
    public List<GymClass> getAllGymClasses();

    /**
     * Calls the dao to fetch a GymClass by searching for an ID.
     * 
     * @param classId the string to search the classId column for.
     * @return a matching GymClass object.
     * @throws GymClassNotFoundException if no match was found.
     */
    public GymClass getGymClassById(String classId) throws GymClassNotFoundException;

    /**
     * Calls the dao to fetch a GymClass by searching for a name.
     * 
     * @param className the string to search the name column for.
     * @return a matching GymClass object.
     * @throws GymClassNotFoundException if no match was found.
     */
    public List<GymClass> getGymClassByName(String className) throws GymClassNotFoundException;

    /**
     * Calls the dao to insert a new object into the database.
     * @param newClass the object to be added.
     */
    public void registerNewClass(GymClass newClass);

    /**
     * Calls the dao to delete a matching object from the database.
     * 
     * @param oldClass the object to delete from the database.
     * @throws GymClassNotFoundException if no matching object exists in the database.
     */
    public void deleteClassFromCatalogue(GymClass oldClass) throws GymClassNotFoundException;

    /**
     * Registers a new class on a customer and saves it in the database.
     * 
     * @param gClass the class to add a customer to
     * @param customer the customer to add a class to
     */
    public void registerClassOnCustomer(GymClass gClass, Customer customer);
}
