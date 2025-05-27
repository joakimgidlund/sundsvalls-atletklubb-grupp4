package se.yrgo.spring.data;

import se.yrgo.spring.domain.TrainingRoom;

import java.util.List;
import java.util.Optional;

public interface RoomDao {
    List<TrainingRoom> findAll();

    Optional<TrainingRoom> findById(Long id);

    TrainingRoom save(TrainingRoom room);

    void deleteById(Long id);
}

// package se.yrgo.spring.data;

// import java.util.List;

// import se.yrgo.spring.domain.GymClass;
// import se.yrgo.spring.domain.TrainingRoom;

// public interface RoomDao {
// public List<TrainingRoom> allRooms();

// public TrainingRoom findRoomById(int roomId);

// public TrainingRoom findRoomByName(String name);

// public void create(TrainingRoom newRoom);

// public void updateRoom (TrainingRoom trainingRoom);

// public void delete(TrainingRoom redundantRoom);

// public List<TrainingRoom> findRoomByGymClass(String name);

// public void addClassToRoom(int roomId, int gymClassId);

// public List<GymClass> getAllRoomClasses(int roomId);
// }
