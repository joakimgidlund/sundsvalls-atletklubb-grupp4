
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

/**
 * Test class for GymClassDao implementation.
 * 
 * @author joakimgidlund
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
class ManagingGymClassIntegrationTest {

    @Autowired
    private GymClassService classes;

    @Autowired
    private CustomerService customers;

    /**
     * Tests adding new classes to the database by registering them
     * and then getting all classes from the database and matching the count.
     */
    @Test
    void testAddingClass() {
        classes.registerNewClass(new GymClass("123", "test1", 123));
        classes.registerNewClass(new GymClass("123", "test2", 123));

        int tableLength = classes.getAllGymClasses().size();
        assertEquals(2, tableLength, "There should be two registered classes in the database.");
    }

    /**
     * Tests finding a class with a bad id. Makes sure we throw
     * GymClassNotFoundException on errors.
     */
    @Test
    void testFindWrongClassById() {
        assertThrows(GymClassNotFoundException.class, () -> {
            classes.getGymClassById("bad id");
        });
    }

    /**
     * Tests searching for a name that doesn't exist in the database.
     * Should throw GymClassNotFoundException.
     */
    @Test
    void testFindWrongByName() {
        assertThrows(GymClassNotFoundException.class, () -> {
            classes.getGymClassByName("bad name");
        });
    }

    /**
     * Tests deletion of gym classes from the database.
     * 
     * @throws GymClassNotFoundException
     */
    @Test
    void testDelete() throws GymClassNotFoundException {
        GymClass gClass = new GymClass("001", "Test class", 130);
        classes.registerNewClass(gClass);

        assertEquals(1, classes.getAllGymClasses().size(), "Checks that the class was added.");

        classes.deleteClassFromCatalogue(gClass);
        assertEquals(0, classes.getAllGymClasses().size(), "The table should be empty after deletion.");
    }

    /**
     * Tests adding customers to a gym class. Registers a customer to a gymclass and
     * then a gymclass to that customer, then we save them to the database. Finally,
     * we find the added gymclass and checks how many customers are attending.
     * Similarily we get all classes of the added customer in the database.
     * 
     * @throws GymClassNotFoundException
     * @throws CustomerNotFoundException
     */
    @Test
    void testClassCustomerJoin() throws GymClassNotFoundException, CustomerNotFoundException {
        Customer customer = new Customer("1", "Joakim", "joakim@mail.com");
        GymClass gClass = new GymClass("123-123", "Power", 100);

        gClass.addCustomerToClass(customer);

        customers.newCustomer(customer);

        classes.registerNewClass(gClass);

        GymClass foundClass = classes.getGymClassById(gClass.getClassId());
        int classCustomers = foundClass.getAttendees().size();
        int customerClasses = customers.getAllCustomerClasses(customer.getCustomerId()).size();
        assertEquals(1, customerClasses, "One class added to customer's classlist");
        assertEquals(1, classCustomers, "One customer added to class attendancelist.");
    }
}
