package se.yrgo.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TrainingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    // Variables
    private int id;
    private int roomId;
    private String roomType;
    private int floor; // 0 is ground level
    @OneToMany(mappedBy = "trainingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GymClass> classesInRoom = new ArrayList<>();

    // Constructor
    public TrainingRoom() {
    }

    public TrainingRoom(int roomId, String roomType, int floor) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.floor = floor;
    }

    // Getters

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getFloor() {
        return floor;
    }

    public List<GymClass> getClassesInRoom() {
        return classesInRoom;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setClassesInRoom(List<GymClass> classesInRoom) {
        this.classesInRoom = classesInRoom;
    }

    // Function
    public void addClassToRoom(GymClass newClassInRoom) {
        classesInRoom.add(newClassInRoom);
    }

    // ToString
    @Override
    public String toString() {
        return "TrainingRoom [id=" + id + ", roomId=" + roomId + ", roomType=" + roomType + ", floor=" + floor
                + ", classesInRoom=" + classesInRoom + "]";
    }
    
}
