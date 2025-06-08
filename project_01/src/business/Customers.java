package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import tools.Inputter;

public final class Customers extends HashSet<Customer> implements Workable<Customer, String> {

    private final String pathFile;
    private boolean isSaved;
    int option = 0;

    public Customers(String pathFile) {
        this.pathFile = pathFile;
        this.readFromFile();
    }

    public boolean isDupplicate(Customer t) {
        return this.contains(t);
    }

    @Override
    public void addNew(Customer t) {
        if (!this.isDupplicate(t)) {
            this.add(t);
            this.isSaved = false;
        } else {
            System.out.println("This customer already exist.");
        }
    }

    @Override
    public void update(Customer t) {
        this.remove(t);
        this.add(t);
        this.isSaved = false;
    }

    @Override
    public void showAll() {
        showAll(this);
    }

    public void showAll(HashSet<Customer> h) {
        System.out.println("-------------------------------------------------------------");
        System.out.format("%-6s | %-20s | %-11s | %-20s%n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("-------------------------------------------------------------");
        for (Customer c : h) {
            System.out.print(c);
        }
        System.out.println("-------------------------------------------------------------");
    }

    @Override
    public Customer searchById(String id) {
        for (Customer c : this) {
            if (c.getCustomerId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public HashSet<Customer> filterByName(String name) {
        HashSet<Customer> result = new HashSet();
        for (Customer c : this) {
            String cName = c.getName().toUpperCase();
            String kName = name.toUpperCase();
            if (cName.contains(kName)) {
                result.add(c);
            }
        }
        return result;
    }

    public void saveToFile() {
        // 0. Save r thi thoi
        if (isSaved) {
            return;
        }

        FileOutputStream fos = null;
        try {
            // 1. Tao file object
            File f = new File(pathFile);
            if (!f.exists()) { // Kh co file thi tao file moi
                f.createNewFile();
            }
            //2. Tao File output stream
            fos = new FileOutputStream(f);
            // 3. Tao object output stream
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 4. Ghi file
            for (Customer c : this) {
                oos.writeObject(c);
            }

            // 5. Dong cac object
            oos.close();
            this.isSaved = true;
        } catch (Exception ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (Exception ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void readFromFile() {
        FileInputStream fis = null;
        try {
            // 1. Tao File
            File f = new File(pathFile);
            if (!f.exists()) {
                System.out.println("Cannot read file from " + this.pathFile + " Please check again!");
                return;
            }
            // 2. Tao File Input Stream
            fis = new FileInputStream(f);
            // 3. Tao Object Input Stream
            ObjectInputStream ois = new ObjectInputStream(fis);

            //4. Doc File
            try {
                while (true) {
                    Object o = ois.readObject();
                    Customer c = (Customer) o;
                    this.addNew(c);
                }
            } catch (Exception e) {
            }
            // 5. Dong cac Object
            ois.close();
        } catch (FileNotFoundException e1) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, e1);
        } catch (IOException e2) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, e2);
        } catch (Exception e3) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, e3);
        } finally {
            try {
                fis.close();
            } catch (Exception ex) {
            }
        }
    }

    public void func01(Inputter ip, Scanner sc) {
        option = 0;
        do {
            this.addNew(ip.inputCustomer(false));
            System.out.println("1. Continue entering new customer.");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your option: ");
            option = Integer.parseInt(sc.nextLine());
        } while (option != 2);
    }

    public void func02(Inputter ip, Scanner sc) {
        option = 0;
        do {
            System.out.print("Input customer code: ");
            String customerCode = sc.nextLine();
            Customer c = this.searchById(customerCode);
            if (c == null) {
                System.out.println("This customer does not exist.\n");
            } else {
                Customer customer = ip.inputCustomer(true);
                customer.setCustomerId(customerCode);
                this.update(customer);
            }
            System.out.println("1. Continue updating new customer.");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your option: ");
            option = Integer.parseInt(sc.nextLine());
        } while (option != 2);
    }

    public void func03(Scanner sc) {
        option = 0;
        do {
            System.out.print("Enter customer Name: ");
            String name = sc.nextLine();
            HashSet<Customer> find = filterByName(name);
            if (find.isEmpty()) {
                System.out.println("The customer is not exist!");
            } else {
                showAll(find);
            }
            System.out.println("1. Continue search.");
            System.out.println("2. Return to main menu.");
            System.out.print("Enter your option: ");
            option = Integer.parseInt(sc.nextLine());
        } while (option != 2);

    }

}
