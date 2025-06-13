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
        this.sc = new Scanner(System.in);
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
        boolean more = true;
        do {
            input = getString(msg);
            if(input.isEmpty()){
               break;
            }
            more = !Acceptable.isValid(input, pattern);
            if (more) {
                System.out.println(errorMsg + " Please try again.");
            }
        } while (more);
        return input;
    }

    public Customer inputCustomer(boolean isUpdated) {
        Customer customer = new Customer();

        String msg = "Enter customer code: ";
        String errorMsg = "Customer code is invalid.";
        String pattern = Acceptable.CUSTOMER_ID_VALID;
        if (!isUpdated) {
            customer.setCustomerId(input(msg, errorMsg, pattern));
        }

        msg = "Enter customer name: ";
        errorMsg = "Customer name is invalid.";
        pattern = Acceptable.NAME_VALID;
        customer.setName(input(msg, errorMsg, pattern));

        msg = "Enter customer phone: ";
        errorMsg = "Customer phone is invalid.";
        pattern = Acceptable.PHONE_VALID;
        customer.setPhone(input(msg, errorMsg, pattern));

        msg = "Enter customer email: ";
        errorMsg = "Customer email is invalid.";
        pattern = Acceptable.EMAIL_VALID;
        customer.setEmail(input(msg, errorMsg, pattern));

        return customer;
    }

    public Order inputOrder(Customers customers, SetMenus setmenus, boolean isUpdated, String updatedCustomerCode) {
        //customerCode
        String customerCode = "";
        boolean check = false;
        if (!isUpdated) {
            do {
                String msg = "Enter customer code: ";
                String errorMsg = "Customer code is invalid.";
                String pattern = Acceptable.CUSTOMER_ID_VALID;

                customerCode = input(msg, errorMsg, pattern);
                if (customers.searchById(customerCode) != null) {
                    check = true;
                } else {
                    System.out.println("Customer is not exist!");
                }
            } while (!check);
        } else {
            customerCode = updatedCustomerCode;
        }
        // setMenuCode
        String setMenuCode = "";
        check = false;
        do {
            System.out.print("Enter set menu code: ");
            setMenuCode = sc.nextLine();
            if (setmenus.contains(setMenuCode)) {
                check = true;
            } else {
                System.out.println("Set menu code is not exist!");
            }
        } while (!check);

        // numberOfTables
        int numberOfTable = 0;
        String msg = "Enter number of table: ";
        String errorMsg = "The number of table must be greater than zero.";
        String pattern = Acceptable.POST_INT;
        numberOfTable = Integer.parseInt(input(msg, errorMsg, pattern));

        // eventDate
        Date eventDate = null;
        check = false;
        do {
            try {
                System.out.print("Enter referred date: ");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String inputDate = sc.nextLine().trim();
                eventDate = sdf.parse(inputDate);
                Date today = new Date();
                if (eventDate.after(today)) {
                    check = true;
                } else {
                    System.out.println("The preferred event date must be in the future!");
                }
            } catch (ParseException ex) {
                Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!check);

        // orderCode
        String orderId = null;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        orderId = sdf.format(now);

        Order order = new Order(orderId, customerCode, setMenuCode, numberOfTable, eventDate);
        return order;
    }
}
