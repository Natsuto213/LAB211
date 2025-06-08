package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Order;
import tools.Inputter;

public class Orders extends ArrayList<Order> implements Workable<Order, String> {

    String pathFile;
    boolean isSaved;
    int option = 0;

    public Orders(String pathFile) {
        this.pathFile = pathFile;
        this.readFromFile();
    }

    public boolean isDupplicate(Order t) {
        return this.contains(t);
    }

    @Override
    public void addNew(Order t) {
        if (!this.isDupplicate(t)) {
            this.add(t);
            this.isSaved = false;
        }
    }

    @Override
    public void update(Order t) {
        this.remove(t);
        this.add(t);
        this.isSaved = false;
    }

    @Override
    public Order searchById(String id) {
        for (Order o : this) {
            if (o.getOrderCode().equals(id)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public void showAll() {
        showAll(this);
    }

    public void showAll(ArrayList<Order> o) {
        Customers customers = new Customers("./src/data/customers.dat");
        SetMenus setmenus = new SetMenus("./src/data/FeastMenu.csv");

        System.out.println("-----------------------------------------------------------------------------");
        System.out.format("%-5s | %-10s | %-11s | %-8s | %-9s | %-6s | %-10s\n",
                "ID", "Event Date", "Customer ID", "Set Menu", "Price", "Tables", "Cost");
        System.out.println("-----------------------------------------------------------------------------");
        for (Order ord : o) {
            ord.display02(customers, setmenus);
        }
    }

    public void saveToFile() {
        FileOutputStream fos = null;
        try {
            // 0. Save r thi thoi
            if (isSaved) {
                return;
            }   // 1. Tao file object
            File f = new File(pathFile);
            if (!f.exists()) { // Kh co file thi tao file moi
                f.createNewFile();
            }
            //2. Tao File output stream
            fos = new FileOutputStream(f);
            // 3. Tao object output stream
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 4. Ghi file
            for (Order o : this) {
                oos.writeObject(o);
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
                    Order ord = (Order) o;
                    this.addNew(ord);
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

    public void func05(Inputter ip, Scanner sc, Customers customers, SetMenus setmenus) {
        option = 0;
        do {
            Order order = ip.inputOrder(customers, setmenus, false, "");
            if (this.contains(order)) {
                System.out.println("Dupplicate data !");
            } else {
                this.addNew(order);
                order.display(customers, setmenus);
            }
            System.out.println("1. Continue your order");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your option: ");
            option = Integer.parseInt(sc.nextLine());
        } while (option != 2);
    }

    public void func06(Inputter ip, Scanner sc, Orders orders, Customers customers, SetMenus setmenus) {
        option = 0;
        do {
            System.out.print("Enter order ID: ");
            String orderID = sc.nextLine();
            Order o = this.searchById(orderID);
            if (o == null) {
                System.out.println("This Order does not exist.");
            } else {
                Order order = ip.inputOrder(customers, setmenus, true, o.getCustomerId());
                order.setOrderCode(orderID);
                this.update(order);
                System.out.println("Update successful.");
            }
            System.out.println("1. Continue update your order.");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your option: ");
            option = Integer.parseInt(sc.nextLine());
        } while (option != 2);
    }
}
