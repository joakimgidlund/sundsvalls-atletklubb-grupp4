package se.yrgo.spring.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.spring.domain.Customer;
import se.yrgo.spring.domain.GymClass;

@Repository
public class GymClassDaoJpaImpl implements GymClassDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<GymClass> allGymClasses() {
        return em.createQuery("select gymclass from GymClass as gymclass").getResultList();
    }

    @Override
    public GymClass findById(String classId) throws RecordNotFoundException {
        try {
            return (GymClass) em.createQuery("select gymclass from GymClass as gymclass where classId=:classId")
                    .setParameter("classId", classId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RecordNotFoundException("No class with that ID found.");
        }
    }

    @Override
    public void create(GymClass newClass) {
        em.persist(newClass);
    }

    @Override
    public void update(GymClass classToUpdate) {
        em.merge(classToUpdate);
    }

    @Override
    public void delete(GymClass oldClass) throws RecordNotFoundException {
        try {
            GymClass forRemoval = em.find(GymClass.class, oldClass.getId());
            em.remove(forRemoval);
        } catch (Exception e) {
            throw new RecordNotFoundException("Could not find the specified object to delete in database.");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GymClass> findGymClassesByTrainer(String trainer) throws RecordNotFoundException {
        try {
            return em.createQuery("select gymclass from GymClass as gymclass where trainer=:trainer")
                    .setParameter("trainer", trainer)
                    .getResultList();
        } catch (Exception e) {
            throw new RecordNotFoundException("No class with that trainer found.");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GymClass> findByName(String className) throws RecordNotFoundException {
        try {
            return em.createQuery("select gymclass from GymClass as gymclass where gymclass.className=:className")
                    .setParameter("className", className)
                    .getResultList();
        } catch (Exception e) {
            throw new RecordNotFoundException("No class with name " + className + " found.");
        }
    }
}
