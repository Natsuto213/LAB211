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

        // -- 0. Neu da luu roi thi khong luu nua
        if (this.isSaved) {
            return;
        }

        FileOutputStream fos = null;
        try {
            //-- 1. Tao File object
            File f = new File(this.pathFile);
            if (!f.exists()) {
                f.createNewFile();
            }

            //-- 2. Tao FileutputStream
            fos = new FileOutputStream(f);

            //-- 3. Tao oos
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //-- 4. Ghi file
            for (Customer c : this) {
                oos.writeObject(c);
            }
            //-- 5. Dong cac objcet
            oos.close();
            //--6. Thay doi trang thai cua saved
            this.isSaved = true;
        } catch (Exception e) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void readFromFile() {
        FileInputStream fis = null;
        try {
            // B1 - Tao doi tuong file de anh xa len tap tin
            File f = new File(this.pathFile);
            // B2 - Kiem tra su ton tai cua file
            if (!f.exists()) {
                System.out.println("Cannot read data from " + this.pathFile + ". Please check it.");
                return;
            } else {
                // B3 - Tao fis
                fis = new FileInputStream(f);
                // B4 - Tap ois
                ObjectInputStream ois = new ObjectInputStream(fis);
                // B5 - Doc tung doi tuong
                try {
                    while (true) {
                        Object o = ois.readObject();
                        Customer customer = (Customer) o;
                        this.addNew(customer);
                    }
                } catch (Exception e) {
                }
            }
        } catch (FileNotFoundException e1) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, e1);
        } catch (IOException e2) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, e2);
        } catch (Exception e4) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, e4);
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
