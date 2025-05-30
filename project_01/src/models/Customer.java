package models;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Comparable<Customer> {
    
    private String customerId;
    private String name;
    private String phone;
    private String email;
    
    public Customer() {
    }
    
    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %-11s | %-20s%n", customerId, name, phone, email);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.customerId);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return Objects.equals(this.customerId, other.customerId);
    }
    
    public void display() {
        System.out.format("%-14s: %s\n", "Customer code", this.getCustomerId());
        System.out.format("%-14s: %s\n", "Customer name", this.getName());
        System.out.format("%-14s: %s\n", "Phone number", this.getPhone());
        System.out.format("%-14s: %s\n", "Email", this.getEmail());
        
    }
    
    @Override
    public int compareTo(Customer that) {
        return this.name.compareToIgnoreCase(that.name);
    }
}
