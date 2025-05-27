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

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import se.yrgo.spring.data.RoomDao;
// import se.yrgo.spring.domain.TrainingRoom;
// import se.yrgo.spring.domain.GymClass;
// import se.yrgo.spring.data.RecordNotFoundException;

// @Service("roomService")
// @Transactional
// public class TrainingRoomServiceProductionImpl implements TrainingRoomService {

//     private final RoomDao roomDao;

//     @Autowired
//     public TrainingRoomServiceProductionImpl(RoomDao roomDao) {
//         this.roomDao = roomDao;
//     }

//     @Override
//     public List<TrainingRoom> getAllTrainingRooms() {
//         return roomDao.allRooms();
//     }

//     @Override
//     public TrainingRoom getTrainingRoomById(int roomId) {
//         return roomDao.findRoomById(roomId);
//     }

//     @Override
//     public void addGymClassToRoom(Long roomId, GymClass gymClass) {
//         roomDao.addGymClassToRoom(roomId, gymClass);
//     }

//     @Override
//     public void registerNewRoom(TrainingRoom newRoom) {
//         roomDao.create(newRoom);
//     }

//     @Override
//     public void deleteRoomFromCatalogue(TrainingRoom oldRoom) {
//         roomDao.delete(oldRoom);
//     }

//     @Override
//     public List<TrainingRoom> getTrainingRoomsByClass(String gymClassName) {
//         return roomDao.findRoomByGymClass(gymClassName);

//     }

//     @Override
//     public List<GymClass> getAllClassesByRoom(int roomId) {
//         return roomDao.getAllRoomClasses(roomId);
//     }

//     @Override
//     public void registerRoomOnClass(TrainingRoom room, GymClass gymClass) throws RecordNotFoundException {
//         if (!room.getClassesInRoom().contains(gymClass)) {
//             room.getClassesInRoom().add(gymClass);
//             gymClass.setTrainingRoom(room);
//             roomDao.updateRoom(room);
//         }
//     }
// }
