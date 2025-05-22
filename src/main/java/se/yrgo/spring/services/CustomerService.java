package se.yrgo.spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import se.yrgo.spring.domain.Customer;

@Service("customerService")
public interface CustomerService {
    public void newCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void addClassToCustomer(String customerId, GymClass newClass) throws CustomerNotFoundException;

    public Customer findCustomerById(String customerId) throws CustomerNotFoundException;

    public List<Customer> findCustomersByName (String name);
    
    public List<Customer> getAllCustomers();

    public Customer getAllCustomerClasses(String customerId);

    public void deleteCustomer(Customer customer);
}
