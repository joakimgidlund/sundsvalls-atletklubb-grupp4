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
import se.yrgo.spring.services.CustomerServiceProductionImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/datasource-test.xml", "/other-tiers.xml"})
@Transactional 
public class ManagingCustomerIntegrationTest {
    @Autowired
    private CustomerServiceProductionImpl customers;

    @Test
    public void testNewCustomer() {
        customers.newCustomer(new Customer("001", "Malin"));
        int actual = customers.getAllCustomers().size();
        assertEquals(1, actual);
    }

    @Test
    public void testFindCustomerById() throws CustomerNotFoundException {
        Customer expected = new Customer("001", "Malin");
        Customer actual = customers.findCustomerById("001");
        assertEquals(expected, actual);
    }

    @Test
    public void testFindWrongCustomer() {
        assertThrows(CustomerNotFoundException.class, () -> {
            List<Customer> list = customers.findCustomersByName("Bosse");
        });
    }
}
