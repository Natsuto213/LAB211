package tools;

import business.Rooms;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;

public class Inputter {

    Scanner sc;

    public Inputter() {
        sc = new Scanner(System.in);
    }

    public String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public String input(String msg, String errorMsg, String pattern) {
        String input;
        boolean more = true;
        do {
            input = getString(msg);
            if (input.isEmpty()) {
                break;
            }
            more = !Acceptable.isValid(input, pattern);
            if (more) {
                System.out.println(errorMsg + " Please try again.");
            }
        } while (more);
        return input;
    }

    public Customer inputCustomer(boolean isUpdated, Rooms rooms) {
        boolean check = false;

        // National ID
        String nationalID = "";
        String msg = "Enter national ID: ";
        String errorMsg = "National ID invalid.";
        String pattern = Acceptable.NATION_ID_VALID;
        if (!isUpdated) {
            nationalID = input(msg, errorMsg, pattern);
        }

        // Name
        String name = "";
        msg = "Enter your name: ";
        errorMsg = "Name invalid.";
        pattern = Acceptable.NAME_VALID;
        name = input(msg, errorMsg, pattern);

        // BirthDate
        Date birthDate = null;
        while (birthDate == null) {
            System.out.print("Enter your birthdate: ");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String inputDate = sc.nextLine().trim();
            if (inputDate == null) {
                break;
            }
            try {
                birthDate = sdf.parse(inputDate);
            } catch (ParseException ex) {
                System.out.println("Birthdate invalid. Please try again.");
            }
        }

        // Gender
        String gender = "";
        msg = "Enter your gender: ";
        errorMsg = "Gender invalid.";
        pattern = Acceptable.GENDER_VALID;
        gender = input(msg, errorMsg, pattern);
        if (gender.isEmpty()) {
            gender = "";
        }

        // Phone
        int phone = 0;
        msg = "Enter your phone: ";
        errorMsg = "Phone invalid.";
        pattern = Acceptable.PHONE_VALID;
        phone = Integer.parseInt(input(msg, errorMsg, pattern));
      

        // RoomID
        String roomID = "";
        msg = "Enter desired room ID: ";
        errorMsg = "Room ID invalid.";
        pattern = Acceptable.ROOM_ID_VALID;
        roomID = input(msg, errorMsg, pattern);
        

        // Number of rental day
        int rentalDays = 0;
        msg = "Enter number of rental day: ";
        errorMsg = "Rental day invalid.";
        pattern = Acceptable.RENTAL_DAY_VALID;
        rentalDays = Integer.parseInt(input(msg, errorMsg, pattern));

        // Start date
        Date startDate = null;
        check = false;
        do {
            try {
                System.out.print("Enter start date: ");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String inputDate = sc.nextLine().trim();
                startDate = sdf.parse(inputDate);
                Date today = new Date();
                if (startDate.after(today)) {
                    check = true;
                } else {
                    System.out.println("Start date must be in future.");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!check);

        // Co-tenant's name
        String coTenant = "";
        msg = "Enter your co-tenant name (if any): ";
        errorMsg = "Co-tenant nameinvalid.";
        pattern = Acceptable.NAME_VALID;
        coTenant = input(msg, errorMsg, pattern);

        Customer customer = new Customer(nationalID, name, birthDate, gender, phone, roomID, rentalDays, startDate, coTenant);
        return customer;
    }

}
