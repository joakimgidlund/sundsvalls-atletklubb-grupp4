
package se.yrgo.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.spring.data.RoomDao;
import se.yrgo.spring.domain.GymClass;
import se.yrgo.spring.domain.TrainingRoom;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingRoomServiceProductionImpl implements TrainingRoomService {

    private final RoomDao roomDao;

    @Autowired
    public TrainingRoomServiceProductionImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<TrainingRoom> findAllRooms() {
        return roomDao.findAll();
    }

    @Override
    public Optional<TrainingRoom> findById(Long id) {
        return roomDao.findById(id);
    }

    @Override
    public TrainingRoom saveRoom(TrainingRoom room) {
        return roomDao.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomDao.deleteById(id);
    }

    @Override
    public TrainingRoom addGymClassToRoom(Long roomId, GymClass gymClass) {
        if (gymClass == null || roomId == null) {
            throw new IllegalArgumentException("RoomId and GymClass must not be null");
        }
        return roomDao.addGymClassToRoom(roomId, gymClass);
    }
}

// package se.yrgo.spring.services;

// import java.util.List;
// import se.yrgo.spring.data.RecordNotFoundException;
// import se.yrgo.spring.domain.GymClass;
// import se.yrgo.spring.domain.TrainingRoom;

// public interface TrainingRoomService {
// List<TrainingRoom> getAllTrainingRooms();

// TrainingRoom getTrainingRoomById(int roomId);

// List<TrainingRoom> getTrainingRoomsByName(String roomName);

// void registerNewRoom(TrainingRoom newRoom);

// void deleteRoomFromCatalogue(TrainingRoom oldRoom);

// List<TrainingRoom> getTrainingRoomsByClass(String gymClassName);

// List<GymClass> getAllClassesByRoom(int roomId);

// void registerRoomOnClass(TrainingRoom room, GymClass gymClass) throws
// RecordNotFoundException;
// }
