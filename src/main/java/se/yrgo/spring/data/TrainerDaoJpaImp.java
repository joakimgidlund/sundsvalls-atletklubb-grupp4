package se.yrgo.spring.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.Trainer;

@Repository
public class TrainerDaoJpaImp implements TrainerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Trainer> allTrainers() {
        return em.createQuery("select trainer from Trainer as trainer").getResultList();
    }

    @Override
    public Trainer findTrainerById(String trainerId) {
       return(Trainer)em.createQuery("select trainer from Trainer as trainer where trainerId=:trainerId")
       .setParameter("trainerId", trainerId)
       .getSingleResult();
    }

    @Override
    public Trainer findTrainerByName(String name) {
        return em.createQuery("select trainer from Trainer as trainer where trainer.name = :name", Trainer.class)
        .setParameter("name", name)
        .getSingleResult();
    }

    @Override
    public void create(Trainer newTrainer) {
        System.out.println("Using JPA");
        em.persist(newTrainer);
    }

    @Override
    public void delete(Trainer redundantTrainer) {
        Trainer trainer = em.find(Trainer.class, redundantTrainer.getId());
        if (trainer != null) {
            em.remove(trainer);
        }
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        em.merge(trainer);
    }

    @Override
    public List<Trainer> findTrainersByGymClass(String name) {
       return em.createQuery("select trainer from Trainer as t where t.gymClass.name =:name" , Trainer.class)
       .setParameter("name", name)
       .getResultList();
    }
    
    @Override
    public void addClassToTrainer(int trainerId, int gymClassId) {
        Trainer trainer = em.find(Trainer.class, trainerId);
        GymClass gymClass = em.find(GymClass.class, gymClassId);
        if (trainer != null && gymClass != null) {
            trainer.getGymClassesTrainers().add(gymClass);
            em.merge(trainer);
        }
    }

    /*returns all the gym classes for that specific trainerId but if the id doesn't exist it 
    returns an empty list.*/
    @Override
    public List <GymClass> getAllTrainerClasses(int trainerId) {
        Trainer trainer = em.find(Trainer.class, trainerId);
        return trainer != null ? trainer.getGymClassesTrainers() : List.of();
    }
}
