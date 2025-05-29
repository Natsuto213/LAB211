package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;

public class Customers extends HashSet<Customer> implements Workable<Customer, String> {

    private String pathFile;
    private boolean isSaved;

    public Customers(String pathFile, boolean isSaved) {
        this.pathFile = pathFile;
        this.isSaved = isSaved;
    }

    public Customers() {
        super();
        this.pathFile = "./src/data/customers.dat";
        this.isSaved = true;
        readFromFile();

        // tao 1 vai customer mau
        this.add(new Customer("C0001", "Vo Anh Phat", "0901345599", "phatvo@gmail.com"));
        this.add(new Customer("C0002", "Nguyen Thi Mai", "0912345678", "mainguyen@gmail.com"));
        this.add(new Customer("g0003", "Tran Van Binh", "0987654321", "binhtv@yahoo.com"));
        this.add(new Customer("K0004", "Le Hoang Nam", "0909123456", "namle@outlook.com"));
        this.add(new Customer("c0005", "Pham Thi Lan", "0933445566", "lanpham@gmail.com"));
        this.add(new Customer("G0006", "Dang Minh Tri", "0977889900", "tridang@hotmail.com"));
        this.add(new Customer("k0007", "Hoang Bao Chau", "0966778899", "chauhb@gmail.com"));
        this.add(new Customer("C0008", "Vo Thanh Dat", "0922334455", "datvo@gmail.com"));
        this.add(new Customer("g0009", "Bui Kim Ngan", "0944221133", "nganbk@yahoo.com"));
        this.add(new Customer("K0010", "Nguyen Van Son", "0955667788", "sonnguyen@zmail.com"));

    }

    public boolean isDupplicate(Customer t) {
        return this.contains(t);
    }

    @Override
    public void addNew(Customer t) {
        if (!this.isDupplicate(t)) {
            this.add(t);
        } else {
            System.out.println("This customer already exist.");
        }
    }

    @Override
    public void update(Customer t) {
        this.remove(t);
        this.add(t);
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
        HashSet<Customer> result = new HashSet<>();
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
        FileOutputStream fos = null;
        try {
            // 1. File
            File f = new File(pathFile);
            // 2. FileOutputStream
            fos = new FileOutputStream(f);
            // 3. ObjectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 4. Lap de ghi du lieu
            for (Customer c : this) {
                oos.writeObject(c);
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
            System.out.println("The customer.dat is not exist!");
        } else {
            FileInputStream fis = null;
            try {
                // 2. FileInputStream
                fis = new FileInputStream(f);
                // 3. ObjectInputStream
                ObjectInputStream ois = new ObjectInputStream(fis);
                // 4. Lap de doc du lieu
                while (fis.available() > 0) {
                    Customer c = (Customer) ois.readObject();
                    this.add(c);
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
