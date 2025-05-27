package se.yrgo.spring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.TrainingRoom;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RoomDaoJpaImp implements RoomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TrainingRoom> findAll() {
        return entityManager.createQuery("from TrainingRoom", TrainingRoom.class).getResultList();
    }

    @Override
    public Optional<TrainingRoom> findById(Long id) {
        TrainingRoom room = entityManager.find(TrainingRoom.class, id);
        return Optional.ofNullable(room);
    }

    @Override
    public TrainingRoom save(TrainingRoom room) {
        if (room.getId() == null) {
            entityManager.persist(room);
            return room;
        } else {
            return entityManager.merge(room);
        }
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(room -> entityManager.remove(room));
    }

    // New method to add GymClass to a room
    public TrainingRoom addGymClassToRoom(Long roomId, GymClass gymClass) {
        TrainingRoom room = entityManager.find(TrainingRoom.class, roomId);
        if (room != null) {
            room.addGymClass(gymClass);
            entityManager.persist(gymClass);
            return room;
        }
        return null;
    }
}

// package se.yrgo.spring.data;

// import java.util.List;

// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;

// import org.springframework.stereotype.Repository;

// import se.yrgo.spring.domain.GymClass;
// import se.yrgo.spring.domain.TrainingRoom;

// @Repository
// public class RoomDaoJpaImp implements RoomDao {

// TrainingRoom room;
// GymClass gymClass;

// @PersistenceContext
// private EntityManager em;

// @Override
// public List<TrainingRoom> allRooms() {
// return em.createQuery("SELECT room FROM TrainingRoom room",
// TrainingRoom.class).getResultList();
// }

// @Override
// public TrainingRoom findRoomById(int roomId) {
// return em.createQuery("SELECT room FROM TrainingRoom room WHERE room.id =
// :roomId", TrainingRoom.class)
// .setParameter("roomId", roomId)
// .getSingleResult();
// }

// @Override
// public List<TrainingRoom> findRoomByGymClass(String name) {
// return em.createQuery(
// "SELECT r FROM TrainingRoom r JOIN r.classesInRoom gc WHERE gc.className =
// :name\r\n",
// TrainingRoom.class)
// .setParameter("name", name)
// .getResultList();
// }

// @Override
// public List<GymClass> getAllRoomClasses(int roomId) {
// TrainingRoom room = em.find(TrainingRoom.class, roomId);
// return room != null ? room.getClassesInRoom() : List.of();
// }

// @Override
// public TrainingRoom findRoomByName(String name) {
// return em.createQuery("SELECT room FROM TrainingRoom room WHERE room.roomType
// = :name", TrainingRoom.class)
// .setParameter("name", name)
// .getSingleResult();
// }

// @Override
// public void create(TrainingRoom newRoom) {
// System.out.println("Using JPA");
// em.persist(newRoom);
// }

// @Override
// public void delete(TrainingRoom redundantRoom) {
// TrainingRoom trainingRoom = em.find(TrainingRoom.class,
// redundantRoom.getId());
// if (trainingRoom != null) {
// em.remove(trainingRoom);
// }
// }

// @Override
// public void updateRoom(TrainingRoom trainingRoom) {
// em.merge(trainingRoom);
// }

// @Override
// @Transactional
// public void addGymClassToRoom(Long roomId, GymClass gymClass) {
// TrainingRoom room = entityManager.find(TrainingRoom.class, roomId);

// if (room != null && gymClass != null) {
// room.addGymClass(gymClass);
// gymClass.setTrainingRoom(room);

// entityManager.merge(gymClass);
// entityManager.merge(room);
// }
// }
// }
