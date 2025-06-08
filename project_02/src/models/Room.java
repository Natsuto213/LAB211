package models;

public class Room {

    private String roomID;
    private String name;
    private String type;
    private int dailyRate;
    private int capacity;
    private String furniture;

    public Room() {
    }

    public Room(String roomID, String name, String type, int dailyRate, int capacity, String furniture) {
        this.roomID = roomID;
        this.name = name;
        this.type = type;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.furniture = furniture;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    @Override
    public String toString() {
        return "Room{" + "roomID=" + roomID + ", name=" + name + ", type=" + type + ", daily rate=" + dailyRate + ", capacity=" + capacity + ", furniture=" + furniture + '}';
    }

}
