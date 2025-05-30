package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Order;
import models.SetMenu;

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
        this.remove(t);
        this.add(t);
    }

    @Override
    public Order searchById(String id) {
        for (Order o : this) {
            if (o.getOrderCode() == id) {
                return o;
            }
        }
        return null;

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

    @Override
    public void showAll() {
        SetMenus setmenus = null;
        showAll(this, setmenus);
    }

    public void showAll(ArrayList<Order> o, SetMenus setmenus) {
        System.out.println("----------------------------------------------------");
        System.out.format("%-5s | %-10s | %-11s | %-8s | %-9,d | %-6s | %-10s\n",
                "ID", "Event Date", "Customer ID", "Set Menu", "Price", "Tables", "Cost");
        System.out.println("----------------------------------------------------");
        for (Iterator<Order> it = this.iterator(); it.hasNext();) {
            Order ord = it.next();
            SetMenu s = setmenus.searchById(ord.getMenuId());
            System.out.format("%-5s | %-10s | %-11s | %-8s | %-9,d | %-6s | %-10,d\n",
                    ord.getCustomerId(), ord.getEventDate(), ord.getCustomerId(), ord.getMenuId(), (int) s.getPrice(), ord.getNumOfTables(), (int) ord.getNumOfTables() * s.getPrice());
        }
    }

}
