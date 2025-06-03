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
        System.out.println("----------------------------------------------------");
        System.out.format("%-5s | %-10s | %-11s | %-8s | %-9,d | %-6s | %-10s\n",
                "ID", "Event Date", "Customer ID", "Set Menu", "Price", "Tables", "Cost");
        System.out.println("----------------------------------------------------");
        for (Order ord : o) {
            SetMenus setmenus = new SetMenus("./src/data/FeastMenu.csv");
            SetMenu s = setmenus.searchById(ord.getMenuId());
            System.out.format("%-5s | %-10s | %-11s | %-8s | %-9,d | %-6s | %-10,d\n",
                    ord.getCustomerId(), ord.getEventDate(), ord.getCustomerId(), ord.getMenuId(), (int) s.getPrice(), ord.getNumOfTables(), (int) ord.getNumOfTables() * s.getPrice());
        }
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
            for (Order o : this) {
                oos.writeObject(o);
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
                        Order order = (Order) o;
                        this.addNew(order);
                    }
                } catch (Exception e) {
                }
            }
        } catch (FileNotFoundException e1) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, e1);
        } catch (IOException e2) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, e2);
        } catch (Exception e3) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, e3);
        } finally {
            try {
                fis.close();
            } catch (Exception ex) {
            }
        }
    }
}
