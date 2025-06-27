package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Room;
import tools.Acceptable;

public class Rooms extends TreeMap<String, Room> {

    private String pathFile;

    public Rooms(String pathFile) {
        this.pathFile = pathFile;
    }

    public void showAll() {
        if (!this.values().isEmpty()) {
            System.out.format("%-6s | %-16s | %-8s | %-6s | %-8s | %-22s\n",
                    "RoomID", "RoomName", "Type", "Rate", "Capacity", "Furniture");
            System.out.println("-------+------------------+----------+--------+----------+---------------------------------------");
            for (Room rm : this.values()) {
                System.out.println(rm);
            }
        } else {
            System.out.println("--------------------------------------");
            System.out.println("Room list is currently empty, not loaded yet.");
            System.out.println("--------------------------------------");
        }
    }

    public Room searchByID(String id) {
        return this.get(id);
    }

    public Room searchByRoomType(String roomType) {
        return this.get(roomType);
    }

    public void readFromFile() {
        int successful = 0, fail = 0;

        FileInputStream fis = null;
        try {
            // 1. Tao file
            File f = new File(pathFile);
            if (!f.exists()) {
                System.out.println("The file is not exist. Please check again!");
            }
            fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            try ( BufferedReader br = new BufferedReader(isr)) {
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    Room room = dataToObject(temp);
                    if (room != null) { // Room is not empty
                        if (this.containsKey(room.getRoomID())) { // Same RoomID
                            fail++;
                        } else {
                            successful++;
                            this.put(room.getRoomID(), room);
                        }
                    } else {
                        fail++;
                    }
                }
            }
            System.out.println("--------------------------------------");
            System.out.println(successful + " rooms succesfully loaded.");
            System.out.println(fail + " entries failed.");
            System.out.println("--------------------------------------");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Room dataToObject(String temp) {
        Room room = null;
        Acceptable acpt = new Acceptable();
        String[] part = temp.split(";");
        if (part.length != 6) { // If don't have enough feilds
            return null;
        }
        try {
            String ID = part[0].trim();
            String name = part[1].trim();
            String type = part[2].trim();
            String rateStr = part[3].trim();
            String capacityStr = part[4].trim();
            String funiture = part[5].trim();

            if (!acpt.isRoomIdValid(ID)) {
                return null;
            }
            if (!acpt.isRateValid(rateStr)) {
                return null;
            }
            if (!acpt.isCapacityValid(capacityStr)) {
                return null;
            }

            double rate = Double.parseDouble(rateStr);
            int capacity = Integer.parseInt(capacityStr);

            room = new Room(ID, name, type, rate, capacity, funiture);
        } catch (Exception e) {
        }

        return room;
    }

    public void func01() {
        this.readFromFile();
    }

    public void func02() {
        this.showAll();
    }

    public void func07(Customers customers) {
        boolean found = false;
        System.out.format("%-6s | %-16s | %-8s | %-6s | %-8s | %-22s\n",
                "RoomID", "RoomName", "Type", "Rate", "Capacity", "Furniture");
        System.out.println("-------+------------------+----------+--------+----------+---------------------------------------");
        for (Room r : this.values()) {
            boolean isRented = false;
            for (Customer c : customers) {
                if (r.getRoomID().equalsIgnoreCase(c.getRoomID())) {
                    isRented = true;
                    break;
                }
            }
            if (!isRented) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("All rooms are currently rented out — no availability at the moment!.");
        }
    }
}
