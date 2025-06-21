package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import models.Customer;
import models.Room;

public class Reports {

    public Reports() {
    }

    public void func08(Scanner sc, Customers customers, Rooms rooms) {
        boolean found = false;

        System.out.print("Enter target month: ");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        sdf.setLenient(false);
        String targetMonth = sc.nextLine().trim();
        try {
            sdf.parse(targetMonth);
        } catch (ParseException ex) {
            System.out.println("---------------------------------------");
            System.out.println("Target month invalid. Please try again.");
            System.out.println("---------------------------------------");
            return;
        }

        for (Customer c : customers) {
            if (c.getStartDate() != null && sdf.format(c.getStartDate()).equals(targetMonth)) {
                Room r = rooms.searchByID(c.getRoomID());
                if (r != null) {
                    if (!found) {
                        System.out.println("Monthly Revenue Report - '" + targetMonth + "'");
                        System.out.format("%-6s | %-16s | %-9s | %-9s | %-7s \n",
                                "RoomID", "Room Name", "Room type", "DailyRate", "Amount");
                        System.out.println("-------+------------------+-----------+-----------+---------");
                    }
                    double amount = r.getDailyRate() * c.getRentalDays();
                    found = true;
                    System.out.format("%-6s | %-16s | %-9s | %-9.2f | %-7.2f \n",
                            r.getRoomID(), r.getName(), r.getType(), r.getDailyRate(), amount);
                }
            }
        }
        System.out.println("------------------------------------------------------------");

        if (!found) {
            System.out.println("There is no data on guests who have rented rooms.");
            System.out.println("------------------------------------------------------------");
        }
    }

    public void func09(Scanner sc, Customers customers, Rooms rooms) {
        System.out.print("Enter room type: ");
        String targetRoomType = sc.nextLine();

        boolean found = false;
        int total = 0;

        System.out.println("----------------------------------");
        System.out.format("%-8s | %-9s\n", "Room type", "Amount");
        System.out.println("----------------------------------");

        for (Customer c : customers) {
            Room r = rooms.searchByID(c.getRoomID());
            if (r != null && r.getType().equalsIgnoreCase(targetRoomType)) {
                int amount = (int) r.getDailyRate() * c.getRentalDays();
                total += amount;
                found = true;
            }
        }
        if (found) {
            System.out.format("%-8s | $%,d\n", targetRoomType, total);
        }
        if (!found) {
            System.out.println("Invalid room type!");
        }
    }

}
