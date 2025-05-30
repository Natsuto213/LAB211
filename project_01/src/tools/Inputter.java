package tools;

import business.Customers;
import business.SetMenus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Order;

public class Inputter {

    private Scanner sc;

    public Inputter() {
        sc = new Scanner(System.in);
    }

    public String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public int getInt(String msg) {
        String temp = getString(msg);
        int result = 0;
        try {
            result = Integer.parseInt(temp);
        } catch (Exception e) {
        }
        return result;
    }

    public double getDouble(String msg) {
        String temp = getString(msg);
        double result = 0;
        try {
            result = Double.parseDouble(temp);
        } catch (Exception e) {
        }
        return result;
    }

    public String input(String msg, String errorMsg, String pattern) {
        String input;
        String errMsg = errorMsg;
        boolean more = true;
        do {
            input = getString(msg);
            more = !Acceptable.isValid(input, pattern);
            if (more) {
                System.out.println(errMsg + ". Please re-enter...");
            }
        } while (more);
        return input;
    }

    public Customer inputCustomer(boolean isUpdated) {
        Customer customer = new Customer();

        String msg = "Enter Customer Code (the first character is [C,G,K], followed by 4 digits): ";
        String errorMsg = "Customer code cannot be empty! Customer code must start with C, G, K, followed by 4 digits!";
        String pattern = Acceptable.CUST_ID_VALID;
        if (!isUpdated) {
            customer.setCustomerId(input(msg, errorMsg, pattern).toUpperCase());

        }

        msg = "Enter name: ";
        errorMsg = "Name cannot be empty. Name must be between 2 and 25 characters.";
        pattern = Acceptable.CUST_NAME_VALID;
        customer.setName(input(msg, errorMsg, pattern));

        msg = "Enter phone: ";
        errorMsg = "Invalid phone format!";
        pattern = Acceptable.PHONE_VALID;
        customer.setPhone(input(msg, errorMsg, pattern));

        msg = "Enter email: ";
        errorMsg = "Invalid email format!";
        pattern = Acceptable.EMAIL_VALID;
        customer.setEmail(input(msg, errorMsg, pattern));

        return customer;
    }

    public Order inputOrder(Customers customers, SetMenus setmenus, boolean updated) {

        //CustomerId
        String customerCode = "";
        boolean checkCustomerCode = false;
        do {
            String msg = "Enter Customer Code: ";
            String errorMsg = "Customer code cannot be empty! Customer code must start with C, G, K, followed by 4 digits!";
            String pattern = Acceptable.CUST_ID_VALID;
            if (!updated) {
                customerCode = input(msg, errorMsg, pattern).toUpperCase();
            }

            if (customers.searchById(customerCode) != null) {
                checkCustomerCode = true;
            } else {
                System.out.println("Customer is not exists!");
            }
        } while (!checkCustomerCode);

        // SetMenuID
        String setMenuCode = "";
        boolean checkSetMenu = false;
        do {
            System.out.print("Enter SetMenu code: ");
            setMenuCode = sc.nextLine();
            if (setmenus.contains(setMenuCode)) {
                checkSetMenu = true;
            } else {
                System.out.println("SetMen Code is not exists!");
            }
        } while (!checkSetMenu);

        // Number of table
        int numberOfTable = 0;
        String msg = "Enter number of tables: ";
        String errorMsg = "Number of tables must be greater than zero!";
        String pattern = Acceptable.POST_INT;
        numberOfTable = Integer.parseInt(input(msg, errorMsg, pattern));

        // Event date
        Date eventDate = null;
        boolean checkEventDate = false;
        do {
            try {
                System.out.print("Enter preferred date: ");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String inputDate = sc.nextLine().trim();
                eventDate = sdf.parse(inputDate);
                Date today = new Date();
                if (eventDate.after(today)) {
                    checkEventDate = true;
                } else {
                    System.out.println("The preferred event date must be in the future!");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!checkEventDate);

        Order order = new Order(customerCode, setMenuCode, numberOfTable, eventDate);
        return order;
    }
}
