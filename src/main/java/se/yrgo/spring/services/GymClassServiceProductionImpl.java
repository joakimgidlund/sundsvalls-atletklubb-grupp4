package se.yrgo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.CustomerDao;
import se.yrgo.spring.data.GymClassDao;
import se.yrgo.spring.data.RecordNotFoundException;
import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

/**
 * Implementation of GymClassService.
 * @see se.yrgo.spring.services.GymClassService
 * 
 * @author joakimgidlund
 */
@Transactional
@Service("gymClassService")
public class GymClassServiceProductionImpl implements GymClassService {

    GymClassDao gymClassDao;
    CustomerDao customerDao;

    @Autowired
    public GymClassServiceProductionImpl(GymClassDao gymClassDao, CustomerDao customerDao) {
        this.gymClassDao = gymClassDao;
        this.customerDao = customerDao;
    }

    @Override
    public List<GymClass> getAllGymClasses() {
        return gymClassDao.allGymClasses();
    }

    @Override
    public GymClass getGymClassById(String classId) throws GymClassNotFoundException {
        try {
            return gymClassDao.findById(classId);
        } catch (RecordNotFoundException e) {
            throw new GymClassNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<GymClass> getGymClassByName(String className) throws GymClassNotFoundException {
        try {
            return gymClassDao.findByName(className);
        } catch (RecordNotFoundException e) {
            throw new GymClassNotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteClassFromCatalogue(GymClass oldClass) throws GymClassNotFoundException {
        try {
            gymClassDao.delete(oldClass);
        } catch (RecordNotFoundException e) {
            throw new GymClassNotFoundException(e.getMessage());
        }
    }

    @Override
    public void registerNewClass(GymClass newClass) {
        gymClassDao.create(newClass);
    }

    @Override
    public void registerClassOnCustomer(GymClass gClass, Customer customer) {
        gClass.addCustomerToClass(customer);
        gymClassDao.update(gClass);
        customerDao.update(customer);
    }
}
