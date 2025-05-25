package se.yrgo.spring.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.spring.domain.*;

@Repository
public class CustomerDaoJpaImp implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    
    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        try {
        return (Customer)em.createQuery("select customer from Customer as customer where customer.customerId=:customerId").setParameter("customerId", customerId).getSingleResult();
        } catch (Exception e) {
            throw new RecordNotFoundException("Customer with ID " + customerId + " not found.");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getByName(String name) throws RecordNotFoundException {
        try {
        return em.createQuery("select customer from Customer as customer where customer.name=:name").setParameter("name", name).getResultList();
        } catch (Exception e) {
            throw new RecordNotFoundException("Customer with name " + name + " not found.");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("select customer from Customer as customer").getResultList();
    }

    @Override
    public void delete(Customer customer) {
        Customer customerToDelete = em.find(Customer.class, customer.getId());
        em.remove(customerToDelete);
    }

    @Override
    public Customer getCustomerClasses(String customerId) throws RecordNotFoundException {
        try { 
            
        return em.createQuery("select customer from Customer as customer join fetch customer.classes where customer.customerId = :id", Customer.class).setParameter("id", customerId).getSingleResult();

        } catch (Exception e) {
            throw new RecordNotFoundException("Customer with id " + customerId + " not found.");
        }
    }
}
