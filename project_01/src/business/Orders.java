package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Order;

public class Orders extends ArrayList<Order> implements Workable<Order, String> {

    String pathFile;
    boolean isSaved;

    public Orders() {
        this.pathFile = "./src/data/feast_order_service.dat";
        this.isSaved = true;
    }

    public boolean isDupplicate(Order t) {
        return this.contains(t);
    }

    @Override
    public void addNew(Order t) {
        if (!this.isDupplicate(t)) {
            this.add(t);
        }
    }

    @Override
    public void update(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showAll() {
        System.out.println("----------------------------------------------------");
        System.out.format("%-5s | %-10s | %-11s | %-8s | %-9,d | %-6s | %-10s\n",
                "ID", "Event Date", "Customer ID", "Set Menu", "Price", "Tables", "Cost");
    }

    @Override
    public Order searchById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void saveToFile() {
        FileOutputStream fos = null;
        try {
            // 1. File
            File f = new File(pathFile);
            // 2. FileOutputStream
            fos = new FileOutputStream(f);
            // 3. ObjectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 4. Lap de ghi du lieu
            for (Order o : this) {
                oos.writeObject(o);
            }
            // 5. close
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void readFromFile() {

        // 1. File
        File f = new File(pathFile);
        if (!f.exists()) {
            System.out.println("The feast_order_service.dat is not exist!");
        } else {
            FileInputStream fis = null;
            try {
                // 2. FileInputStream
                fis = new FileInputStream(f);
                // 3. ObjectInputStream
                ObjectInputStream ois = new ObjectInputStream(fis);
                // 4. Lap de doc du lieu
                while (fis.available() > 0) {
                    Order o = (Order) ois.readObject();
                    this.add(o);
                }
                // 5. close
                ois.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
