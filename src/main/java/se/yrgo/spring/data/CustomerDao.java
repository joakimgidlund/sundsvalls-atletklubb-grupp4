package se.yrgo.spring.data;

import java.util.List;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

/**
 * Defines a contract for a DAO to communicate with a database.
 * Includes methods for standard CRUD-operations and some custom queries.
 * Methods that access data by identifier may throw a RecordNotFoundException
 * if the record does not exist.
 * 
 * @author anomalin
 * 
 */
public interface CustomerDao {

    /**
     * Persists a new customer entity to the database.
     * 
     * @param customer the customer to be created
     */
    public void create(Customer customer);

    /**
     * Updating an existing customer in the database.
     * 
     * @param customer the customer to be updated
     */
    public void update(Customer customer);

    /**
     * Retrives a customer from the database by ID.
     *
     * @param customerId the ID of the customer
     * @return the customer with the given ID
     * @throws RecordNotFoundException if no customer with the given ID exists
     */
    public Customer getById(String customerId) throws RecordNotFoundException;

    /**
     * Finds all customers matching the given name.
     * 
     * @param name the name to search for
     * @return a list of customers with the specified name
     * @throws RecordNotFoundException if no matching customers are found
     */
    public List<Customer> getByName(String name) throws RecordNotFoundException;

    /**
     * Retrieves all customers from the database.
     * 
     * @return a list of all customers
     */
    public List<Customer> getAllCustomers();

    /**
     * Retrieves all gym classes associated with a specific customer.
     * 
     * @param customerId the ID of the customer
     * @return a list of classes the customer is registered for
     * @throws RecordNotFoundException if the customer does not exist
     */
    public List<GymClass> getCustomerClasses(String customerId) throws RecordNotFoundException;

    /**
     * Deletes the specified customer from the database.
     * 
     * @param customer the customer to delete
     * @throws RecordNotFoundException if the customer does not exist
     */
    public void delete(Customer customer) throws RecordNotFoundException;
}