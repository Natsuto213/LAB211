package models;

import business.Customers;
import business.SetMenus;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private String orderId;
    private String customerId;
    private String codeOfSetMenu;
    private int numOfTables;
    private Date eventDate;

    public Order() {
    }

    public Order(String orderId, String customerId, String codeOfSetMenu, int numOfTables, Date eventDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numOfTables = numOfTables;
        this.eventDate = eventDate;
    }

    public String getOrderCode() {
        return orderId;
    }

    public void setOrderCode(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMenuId() {
        return codeOfSetMenu;
    }

    public void setMenuId(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    public int getNumOfTables() {
        return numOfTables;
    }

    public void setNumOfTables(int numOfTables) {
        this.numOfTables = numOfTables;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerId=" + customerId + ", codeOfSetMenu=" + codeOfSetMenu + ", numOfTables=" + numOfTables + ", eventDate=" + eventDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        if (!Objects.equals(this.codeOfSetMenu, other.codeOfSetMenu)) {
            return false;
        }
        return Objects.equals(this.eventDate, other.eventDate);
    }

    public void display(Customers customers, SetMenus setMenus) {
        System.out.println("---------------------------------");
        System.out.println("Customer order information [Order id: " + this.getOrderCode() + "]");
        System.out.println("---------------------------------");

        Customer c = customers.searchById(this.getCustomerId());
        c.display();
        System.out.println("---------------------------------");

        SetMenu s = setMenus.searchById(this.getMenuId());
        s.display(this.eventDate, this.numOfTables);
        System.out.println("---------------------------------");

        System.out.format("%-16s: %,d Vnd\n", "Total cost", (int) s.getPrice() * this.getNumOfTables());
    }

    public void display02(Customers customers, SetMenus setMenus) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SetMenu s = setMenus.searchById(this.getMenuId());
        System.out.format("%-5s | %-10s | %-11s | %-8s | %,d | %-6s | %,d\n",
                this.getCustomerId(), sdf.format(this.getEventDate()), this.getCustomerId(), this.getMenuId(), (int) s.getPrice(), this.getNumOfTables(), (int) s.getPrice() * this.getNumOfTables());
    }

//    @Override
//    public int compareTo(Order that) {
//        return this.eventDate.compareTo(that.eventDate);
//    }
}
