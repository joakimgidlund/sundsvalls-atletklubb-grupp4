package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

public interface CustomerService {
    public void newCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void addClassToCustomer(String customerId, GymClass newClass) throws CustomerNotFoundException;

    public Customer findCustomerById(String customerId) throws CustomerNotFoundException;

    public List<Customer> findCustomersByName (String name) throws CustomerNotFoundException;
    
    public List<Customer> getAllCustomers();

    public List<GymClass> getAllCustomerClasses(String customerId) throws CustomerNotFoundException;

    public void deleteCustomer(Customer customer) throws CustomerNotFoundException;
}
