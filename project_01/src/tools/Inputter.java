package tools;

import java.util.Scanner;
import models.Customer;
import models.Order;

public class Inputter {

    private Scanner sc;

    public Inputter() {
        sc = new Scanner(System.in);
    }

    public String getString(String msg) {
        System.out.println(msg);
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

        String msg = "Input Customer Code (the first character is [C,G,K], followed by 4 digits): ";
        String errorMsg = "Customer code cannot be empty! Customer code must start with C, G, K, followed by 4 digits!";
        String pattern = Acceptable.CUST_ID_VALID;
        if (!isUpdated) {
            customer.setCustomerId(input(msg, errorMsg, pattern).toUpperCase());

        }

        msg = "Input name: ";
        errorMsg = "Name cannot be empty. Name must be between 2 and 25 characters.";
        pattern = Acceptable.CUST_NAME_VALID;
        customer.setName(input(msg, errorMsg, pattern));

        msg = "Input phone: ";
        errorMsg = "Invalid phone format!";
        pattern = Acceptable.PHONE_VALID;
        customer.setPhone(input(msg, errorMsg, pattern));

        msg = "Input email: ";
        errorMsg = "Invalid email format!";
        pattern = Acceptable.EMAIL_VALID;
        customer.setEmail(input(msg, errorMsg, pattern));

        return customer;
    }

    public Order inputOrder() {
        Order order = new Order();

        String msg = "Input Customer code.";
        String errorMsg = "Customer has not registered";
        String pattern = Acceptable.CUST_ID_VALID;

        msg = "Input number of tables.";
        errorMsg = "Must be greater than zero";
        pattern = Acceptable.POST_INT;
        order.setNumOfTables(getInt(msg));

        return order;
    }
}
