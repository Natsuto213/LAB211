package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import models.Customer;
import models.Room;
import tools.Inputter;

public class Customers extends HashSet<Customer> {

    private String filePath;
    private boolean isSaved;

    public Customers() {
    }

    public boolean isDupplicate(Customer c) {
        return this.contains(c);
    }

    public void addNew(Customer c) {
        if (!this.isDupplicate(c)) {
            this.add(c);
        } else {
            System.out.println("This customer has already exist!");
        }
    }

    public void update(Customer c) {
        this.remove(c);
        this.add(c);
        this.isSaved = false;
    }

    public Customer searchByNationalId(String id) {
        for (Customer c : this) {
            if (c.getNationalID().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public void display(Customer c, Rooms rooms) {
        Room r = rooms.searchByID(c.getRoomID());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateFormat = sdf.format(c.getBirthdate());
        String checkInFormat = sdf.format(c.getStartDate());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Guest information [National ID: " + c.getNationalID() + "]");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.format("%-14s: %s\n", "Full name", c.getName());
        System.out.format("%-14s: %s\n", "Phone number", c.getPhone());
        System.out.format("%-14s: %s\n", "Birth day", birthDateFormat);
        System.out.format("%-14s: %s\n", "Gender", c.getGender());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.format("%-14s: %s\n", "Rental room", c.getRoomID());
        System.out.format("%-14s: %s\n", "Check in", checkInFormat);
        System.out.format("%-14s: %s\n", "Rental days", c.getRentalDays());
        System.out.format("%-14s: %s\n", "Check out", c.getCheckOutDate());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Room information");
        System.out.format("+ %-10s: %s\n", "ID", r.getRoomID());
        System.out.format("+ %-10s: %s\n", "Room", r.getName());
        System.out.format("+ %-10s: %s\n", "Type", r.getType());
        System.out.format("+ %-10s: %,d$\n", "Daily rate", (int) r.getDailyRate());
        System.out.format("+ %-10s: %d\n", "Capacity", r.getCapacity());
        System.out.format("+ %-10s: %s\n", "Funiture", r.getFurniture());
        System.out.println("-----------------------------------------------------------------------------");
    }

    public void func03(Inputter ip,Rooms rooms) {
        Customer c = ip.inputCustomer(false, rooms);

        this.addNew(c);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatStartDate = sdf.format(c.getStartDate());

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Guest registered successfully for room " + c.getRoomID());
        System.out.println("Rental from " + formatStartDate + " for " + c.getRentalDays() + " days");
        System.out.println("-----------------------------------------------------------------------------");
    }

    public void func04(Scanner sc, Inputter ip, Rooms rooms) {
        System.out.print("Enter national ID: ");
        String nationalID = sc.nextLine();
        Customer c = this.searchByNationalId(nationalID);
        if (c == null) {
            System.out.println("--------------------------------------");
            System.out.println("No guest found with the requested ID !");
            System.out.println("--------------------------------------");

        } else {
            Customer customer = ip.inputCustomer(true, rooms);
            customer.setNationalID(nationalID);
            System.out.println("----------------------------------------------------");
            System.out.println("Guest information updated for ID: " + customer.getNationalID());
            System.out.println("----------------------------------------------------");
        }
    }

    public void func05(Scanner sc, Rooms rooms) {
        System.out.print("Enter national ID: ");
        String nationalID = sc.nextLine();
        Customer c = this.searchByNationalId(nationalID);
        if (c == null) {
            System.out.println("--------------------------------------");
            System.out.println("No guest found with the request ID '" + nationalID + "'");
            System.out.println("--------------------------------------");
        } else {
            this.display(c, rooms);
        }
    }

    public void func06(Scanner sc, Rooms rooms) {
        Date today = new Date();
        System.out.print("Enter national ID: ");
        String nationalID = sc.nextLine();
        Customer c = this.searchByNationalId(nationalID);
        if (c == null) {
            System.out.println("Booking detail for ID '" + nationalID + "' could not be found");
        } else {
            if (c.getStartDate().after(today)) {
                this.display(c, rooms);
                System.out.print("Do you really want to cancel this guest's room booking ? [Y/N]: ");
                String choose = sc.nextLine();
                if (choose.equalsIgnoreCase("Y")) {
                    System.out.println("...System message...");
                    this.remove(c);
                    System.out.println("The booking associated with ID " + nationalID + " has been successfully cancelled");
                    this.isSaved = false;
                } else {
                    System.out.println("Cancellation  aborted");
                }
            } else {
                System.out.println("The room booking for this guest cannot be cancelled!");
            }
        }
    }
}
