package business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Room;
import tools.Inputter;

public class Customers extends HashSet<Customer> implements Serializable {

    private String filePath;
    private boolean isSaved;

    public Customers() {
    }

    public Customers(String filePath) {
        this.filePath = filePath;
        this.isSaved = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            this.add(new Customer("012345678901", "Alice Tran", sdf.parse("12/03/1990"), "Female", "0901234567", "R101", 3, sdf.parse("25/06/2025"), "Bob Nguyen"));
            this.add(new Customer("012345678902", "Minh Nguyen", sdf.parse("05/07/1985"), "Male", "0912345678", "R102", 5, sdf.parse("26/06/2025"), ""));
            this.add(new Customer("012345678903", "Thao Le", sdf.parse("09/11/1992"), "Female", "0923456789", "R103", 2, sdf.parse("22/07/2025"), "Lan Pham"));
            this.add(new Customer("012345678904", "Khoa Pham", sdf.parse("15/01/1988"), "Male", "0934567890", "R104", 4, sdf.parse("01/07/2025"), ""));
            this.add(new Customer("012345678905", "Linh Dang", sdf.parse("18/06/1995"), "Female", "0945678901", "R105", 3, sdf.parse("30/06/2025"), "Hung Do"));
            this.add(new Customer("012345678906", "Huy Tran", sdf.parse("22/02/1990"), "Male", "0956789012", "R106", 6, sdf.parse("05/07/2025"), ""));
            this.add(new Customer("012345678907", "Trang Vo", sdf.parse("30/09/1989"), "Female", "0967890123", "R107", 1, sdf.parse("24/06/2025"), ""));
            this.add(new Customer("012345678908", "Son Ho", sdf.parse("01/04/1993"), "Male", "0978901234", "R108", 7, sdf.parse("10/07/2025"), ""));
            this.add(new Customer("012345678909", "Mai Ly", sdf.parse("11/12/1991"), "Female", "0989012345", "R109", 2, sdf.parse("28/06/2025"), "Tuan Le"));
            this.add(new Customer("012345678910", "Dat Nguyen", sdf.parse("03/08/1987"), "Male", "0990123456", "R110", 5, sdf.parse("15/07/2025"), ""));
        } catch (ParseException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        this.isSaved = false;
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
        String birthDateFormat;
        if (c.getBirthdate() != null) {
            birthDateFormat = sdf.format(c.getBirthdate());
        } else {
            birthDateFormat = "";
        }
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

    public void saveToFile() {
        if (isSaved) {
            return;

        }
        FileOutputStream fos = null;
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }

            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Customer c : this) {
                oos.writeObject(c);
            }
            oos.close();
        } catch (Exception ex) {
        } finally {
            try {
                fos.close();
            } catch (Exception ex) {
            }
        }
    }

    public void func03(Inputter ip, Rooms rooms) {
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
            if (customer.getBirthdate() == null) {
                customer.setBirthdate(c.getBirthdate());
            }
            if (customer.getPhone().isEmpty()) {
                customer.setPhone(c.getPhone());
            }
            if (customer.getGender().isEmpty()) {
                customer.setGender(c.getGender());

            }
            this.update(customer);
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

    public void func10() {
        this.saveToFile();
        System.out.println("----------------------");
        System.out.println("Update succesfull!");
    }
}
