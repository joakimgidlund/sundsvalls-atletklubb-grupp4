package se.yrgo.spring.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TrainingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "trainingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GymClass> gymClasses = new ArrayList<>();

    public TrainingRoom() {
    }

    public TrainingRoom(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GymClass> getGymClasses() {
        return gymClasses;
    }

    public void addGymClass(GymClass gymClass) {
        gymClasses.add(gymClass);
        gymClass.setTrainingRoom(this);
    }

    public void removeGymClass(GymClass gymClass) {
        gymClasses.remove(gymClass);
        gymClass.setTrainingRoom(null);
    }
}

// package se.yrgo.spring.domain;

// import java.util.ArrayList;
// import java.util.List;

// import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;

// @Entity
// public class TrainingRoom {

// @Id
// @GeneratedValue(strategy = GenerationType.AUTO)

// private int id;
// private int roomId;
// private String roomType;
// private int floor; // 0 is ground level
// @OneToMany(mappedBy = "trainingRoom", cascade = CascadeType.ALL,
// orphanRemoval = true)
// private List<GymClass> classesInRoom = new ArrayList<>();

// public TrainingRoom() {
// }

// public TrainingRoom(int roomId, String roomType, int floor) {
// this.roomId = roomId;
// this.roomType = roomType;
// this.floor = floor;
// }

// public int getId() {
// return id;
// }

// public int getRoomId() {
// return roomId;
// }

// public String getRoomType() {
// return roomType;
// }

// public int getFloor() {
// return floor;
// }

// public List<GymClass> getClassesInRoom() {
// return classesInRoom;
// }

// public void setId(int id) {
// this.id = id;
// }

// public void setRoomId(int roomId) {
// this.roomId = roomId;
// }

// public void setRoomType(String roomType) {
// this.roomType = roomType;
// }

// public void setFloor(int floor) {
// this.floor = floor;
// }

// public void setClassesInRoom(List<GymClass> classesInRoom) {
// this.classesInRoom = classesInRoom;
// }

// public void addClassToRoom(GymClass newClassInRoom) {
// if (!classesInRoom.contains(newClassInRoom)) {
// classesInRoom.add(newClassInRoom);
// newClassInRoom.setTrainingRoom(this);
// }
// }

// @Override
// public String toString() {
// return "TrainingRoom [id=" + id + ", roomId=" + roomId + ", roomType=" +
// roomType + ", floor=" + floor
// + ", classesInRoom=" + classesInRoom + "]";
// }

// }
