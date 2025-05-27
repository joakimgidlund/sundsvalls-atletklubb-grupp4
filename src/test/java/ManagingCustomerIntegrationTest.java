import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.services.CustomerNotFoundException;
import se.yrgo.spring.services.CustomerService;
/**
 * Integration tests for the customer management layer.
 * Verifies the persistence and retrieval of customers using the {@link CustomerService}
 * 
 * @author anomalin
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/other-tiers.xml", "/datasource-test.xml"})
@Transactional 
public class ManagingCustomerIntegrationTest {
    @Autowired
    private CustomerService customers;

    /**
     * Tests if a new customer is successfully persisted to the database.
     */
    @Test
    public void testNewCustomer() {
        customers.newCustomer(new Customer("001", "Malin", "malin@mail.com"));
        int actual = customers.getAllCustomers().size();
        assertEquals(1, actual);
    }

    /**
     * Tests if you can search for a customer with a given ID.
     * 
     * @throws CustomerNotFoundException if no customer exists with the given ID
     */
    @Test
    public void testFindCustomerById() throws CustomerNotFoundException {
        Customer expected = new Customer("001", "Malin", "malin@mail.com");
        customers.newCustomer(expected);
        Customer actual = customers.findCustomerById("001");
        assertEquals(expected, actual);
    }

    /**
     * Tests if a {@link CustomerNotFoundException} is thrown if you get no matching customers when searching for a given name.
     */
    @Test
    public void testFindWrongCustomer() {
        assertThrows(CustomerNotFoundException.class, () -> {
            List<Customer> list = customers.findCustomersByName("Bosse");
        });
    }
}
