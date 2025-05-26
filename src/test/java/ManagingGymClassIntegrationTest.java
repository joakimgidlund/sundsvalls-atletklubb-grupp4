
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.services.CustomerNotFoundException;
import se.yrgo.spring.services.CustomerService;
import se.yrgo.spring.services.GymClassNotFoundException;
import se.yrgo.spring.services.GymClassService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class ManagingGymClassIntegrationTest {

    @Autowired
    private GymClassService classes;

    @Autowired
    private CustomerService customers;

    @Test
    public void testAddingClass() {
        classes.registerNewClass(new GymClass("123", "test1", 123));
        classes.registerNewClass(new GymClass("123", "test2", 123));

        int tableLength = classes.getAllGymClasses().size();
        assertEquals(2, tableLength, "There should be two registered classes in the database.");
    }

    @Test
    public void testFindWrongClass() {
        assertThrows(GymClassNotFoundException.class, () -> {
            GymClass found = classes.getGymClassById("bad id");
        });
    }

    @Test
    public void testClassCustomerJoin() throws GymClassNotFoundException, CustomerNotFoundException {
        Customer customer = new Customer("1", "Joakim", "joakim@mail.com");
        GymClass gClass = new GymClass("123-123", "Power", 100);

        gClass.addCustomerToClass(customer);

        customers.newCustomer(customer);

        classes.registerNewClass(gClass);

        
        int classCustomers = classes.getAllCustomers(gClass.getClassId()).size();
        int customerClasses = customers.getAllCustomerClasses(customer.getCustomerId()).size();
        assertEquals(1, customerClasses, "One class added to customer's classlist");
        assertEquals(1, classCustomers, "One customer added to class attendancelist.");
    }
}
