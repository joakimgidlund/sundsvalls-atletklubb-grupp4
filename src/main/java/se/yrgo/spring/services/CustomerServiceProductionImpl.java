package se.yrgo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.CustomerDao;
import se.yrgo.spring.domain.Customer;

@Transactional
@Service("customerService")
public class CustomerServiceProductionImpl implements CustomerService {

    CustomerDao customerDao;

    @Override
    public void newCustomer(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void addClassToCustomer(String customerId, GymClass newClass) throws CustomerNotFoundException {
        try {
            Customer customer = customerDao.findById(customerId);
            customer.addClassToCustomer(newClass);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getById(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerDao.getByName(name);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getAllCustomerClasses(String customerId) {
        Customer customer = customerDao.getById(customerId);
        return customer.getClasses();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        try {
            customerDao.delete(customer);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

}
