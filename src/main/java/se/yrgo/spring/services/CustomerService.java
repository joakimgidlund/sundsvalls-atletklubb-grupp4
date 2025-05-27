package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

/**
 * Defines a contract for the business logic layer related to customer management
 * within the application. 
 * Methods that access data through an identifier may throw a CustomerNotFoundException
 * if the customer does not exist.
 * 
 * @author anomalin
 */
public interface CustomerService {

    /**
     * Registers a new customer in the system.
     * 
     * @param customer to be created
     */
    public void newCustomer(Customer customer);

    /**
     * Updates an existing customer's details.
     * 
     * @param customer the customer with updated information
     */
    public void updateCustomer(Customer customer);

    /**
     * Adds a gym class to the specified customer.
     * 
     * @param customerId the ID of the customer
     * @param newClass the class to be added
     * @throws CustomerNotFoundException if no customer exists with the given ID
     */
    public void addClassToCustomer(String customerId, GymClass newClass) throws CustomerNotFoundException;

    /**
     * Searches for a customer with the given ID.
     * 
     * @param customerId the ID of the customer
     * @return the customer with the given ID
     * @throws CustomerNotFoundException if no customer exists with the given ID
     */
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException;

    /**
     * Searches for customers with the given name.
     * 
     * @param name the name of the customer(s)
     * @return a list of customers with the given name
     * @throws CustomerNotFoundException if no matching customers are found
     */
    public List<Customer> findCustomersByName (String name) throws CustomerNotFoundException;
    
    /**
     * Retrieves a list of all customers.
     * 
     * @return list of all customers
     */
    public List<Customer> getAllCustomers();

    /**
     * Retrieves all gym classes associated with a specific customer.
     * 
     * @param customerId the ID of the customer
     * @return a list of classes the customer is registered for
     * @throws CustomerNotFoundException if no customer exists with the given ID
     */
    public List<GymClass> getAllCustomerClasses(String customerId) throws CustomerNotFoundException;

    /**
     * Deletes a customer.
     * 
     * @param customer customer to be deleted.
     * @throws CustomerNotFoundException if the customer does not exist
     */
    public void deleteCustomer(Customer customer) throws CustomerNotFoundException;
}
