package business;

import java.util.HashSet;
import models.Customer;

public class Customers extends HashSet<Customer> implements Workable<Customer, String> {

    private String pathFile;
    private boolean isSaved;

    public Customers() {
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
        System.out.format("%-6s | %-25s | %-11s | %-20s%n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("-------------------------------------------------------------");
        for (Customer c : h) {
            System.out.println(c);
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

}
