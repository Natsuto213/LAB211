package dispatcher;

import business.Customers;
import business.Orders;
import business.SetMenus;
import java.util.HashSet;
import java.util.Scanner;
import models.Customer;
import models.Order;
import tools.Inputter;

public class main {

    public static void main(String[] args) {
        Inputter ip = new Inputter();
        Scanner sc = new Scanner(System.in);

        Customers customers = new Customers();
        SetMenus setmenus = new SetMenus();
        Orders orders = new Orders();

        int testCase = 10;
        do {
            System.out.println("----------MAIN MENU------------");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Seach for customer information by name");
            System.out.println("4. Display feast menu");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update order information");
            System.out.println("7. Save data to file");
            System.out.println("8. Display Customer or Order lists");
            System.out.println("Other-Quit.");
            System.out.print("Enter Test Case No. : ");
            testCase = Integer.parseInt(sc.nextLine());
            switch (testCase) {
                case 1:
                    int option = 0;
                    do {
                        customers.addNew(ip.inputCustomer(false));
                        System.out.println("1. Continue entering new customer.");
                        System.out.println("2. Return to main menu");
                        System.out.print("Enter your option: ");
                        option = Integer.parseInt(sc.nextLine());
                    } while (option != 2);
                    break;
                case 2:
                    option = 0;
                    do {
                        System.out.print("Input customer code: ");
                        String customerCode = sc.nextLine();
                        Customer c = customers.searchById(customerCode);
                        if (c == null) {
                            System.out.println("This customer does not exist.\n");
                        } else {
                            Customer customer = ip.inputCustomer(true);
                            customer.setCustomerId(customerCode);
                            customers.update(customer);
                        }
                        System.out.println("1. Continue updating new customer.");
                        System.out.println("2. Return to main menu");
                        System.out.print("Enter your option: ");
                        option = Integer.parseInt(sc.nextLine());
                    } while (option != 2);
                    break;
                case 3:
                    option = 0;
                    do {
                        System.out.print("Input customer name: ");
                        String name = sc.nextLine();
                        HashSet<Customer> cs = customers.filterByName(name);
                        if (cs.isEmpty()) {
                            System.out.println("No one matches the search critearia.");
                        } else {
                            customers.showAll(cs);
                        }

                        System.out.println("1. Continue search");
                        System.out.println("2. Return to main menu");
                        System.out.print("Enter your option: ");
                        option = Integer.parseInt(sc.nextLine());
                    } while (option != 2);
                    break;
                case 4: {
                    try {
                        setmenus.readFromFile();
                    } catch (Exception e) {
                    }
                    setmenus.showAll();
                    break;
                }
                case 5:
                    option = 0;
                    do {
                        Order order = ip.inputOrder(customers, setmenus, false);
                        if (orders.contains(order)) {
                            System.out.println("Dupplicate data !");
                        } else {
                            orders.addNew(order);
                            order.display(customers, setmenus);
                        }
                        System.out.println("1. Continue your order");
                        System.out.println("2. Return to main menu");
                        System.out.print("Enter your option: ");
                        option = Integer.parseInt(sc.nextLine());
                    } while (option != 2);

                    break;
                case 6:
                    System.out.print("Enter order ID: ");
                    String orderID = sc.nextLine();
                    Order o = orders.searchById(orderID);
                    if(o == null){
                        System.out.println("This Order does not exist.");
                    }else{
                        Order order = ip.inputOrder(customers, setmenus, true);
                    }
                    
                    break;
                case 7:
                    customers.saveToFile();
                    System.out.println("Customer data has been succesfully saved to 'customer.dat'");
                    orders.saveToFile();
                    System.out.println("Order data has been succesfully saved to 'feast_order_service.dat'");
                    break;

                case 8:
                    option = 0;
                    System.out.println("Display customer or order lists");
                    System.out.println("1. Customer list.");
                    System.out.println("2. Order list");
                    System.out.print("Enter your option: ");
                    option = Integer.parseInt(sc.nextLine());
                    switch (option) {

                        case 1:
                            customers.readFromFile();
                            customers.showAll();
                            break;
                        case 2:
                            orders.readFromFile();
                            orders.showAll();
                            break;
                        default:
                            System.out.println("Return to main menu...");
                            break;
                    }

                    break;
                default:
                    System.out.println("Exit...., bye bye");
                    break;
            }
        } while (testCase >= 1 && testCase <= 8);

    }

}
