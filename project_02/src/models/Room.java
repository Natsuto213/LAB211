package models;

public class Room {

    private String roomID;
    private String name;
    private String type;
    private double dailyRate;
    private int capacity;
    private String furniture;

    public Room() {
    }

    public Room(String roomID, String name, String type, double dailyRate, int capacity, String furniture) {
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

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
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
        return String.format("%-6s | %-16s | %-8s | %6.2f | %8d | %-22s",
                roomID, name, type, dailyRate, capacity, furniture);
    }

}
