package se.yrgo.spring.data;

import java.util.List;

import se.yrgo.spring.domain.Customer;

public interface CustomerDao {

    public void create(Customer customer);
    
    public void update(Customer customer);

    public Customer getById(String customerId) throws RecordNotFoundException;

    public List<Customer> getByName(String name) throws RecordNotFoundException;

    public List<Customer> getAllCustomers();

    public void delete(Customer customer) throws RecordNotFoundException;
}