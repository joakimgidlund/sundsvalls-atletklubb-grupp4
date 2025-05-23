package se.yrgo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.CustomerDao;
import se.yrgo.spring.data.RecordNotFoundException;
import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

@Transactional
@Service("customerService")
public class CustomerServiceProductionImpl implements CustomerService {

    CustomerDao customerDao;

    @Autowired
    public CustomerServiceProductionImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void newCustomer(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    public void addClassToCustomer(String customerId, GymClass newClass) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerDao.getById(customerId);
            customer.addClassToCustomer(newClass);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException(e.getMessage());
        }

    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getById(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Customer> findCustomersByName(String name) throws CustomerNotFoundException {
        try {
            return customerDao.getByName(name);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public List<GymClass> getAllCustomerClasses(String customerId) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerDao.getById(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException(e.getMessage());
        }
        return customer.getClasses();
    }

    @Override
    public void deleteCustomer(Customer customer) throws CustomerNotFoundException {
        try {
            customerDao.delete(customer);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException(e.getMessage());
        }

    }

}
