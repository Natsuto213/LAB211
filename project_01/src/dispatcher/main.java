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

        Customers customers = new Customers("./src/data/customers.dat");
        SetMenus setmenus = new SetMenus("./src/data/FeastMenu.csv");
        Orders orders = new Orders("./src/data/feast_order_service.dat");

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
                    option = 0;
                    do {
                        System.out.print("Enter order ID: ");
                        String orderID = sc.nextLine();
                        Order o = orders.searchById(orderID);
                        if (o == null) {
                            System.out.println("This Order does not exist.");
                        } else {
                            Order order = ip.inputOrder(customers, setmenus, true);
                            order.setOrderCode(orderID);
                            orders.update(order);
                            System.out.println("Update successful.");
                        }

                        System.out.println("1. Continue update your order.");
                        System.out.println("2. Return to main menu");
                        System.out.print("Enter your option: ");
                        option = Integer.parseInt(sc.nextLine());
                    } while (option != 2);

                    break;
                case 7:
                    customers.saveToFile();
                    orders.saveToFile();
                    System.out.println("The data is successfully saved");
                    break;
                case 8:
                    int choose = 0;
                    Customers customers_temp = new Customers("./src/data/customers.dat");
                    Orders orders_temp = new Orders("./src/data/feast_order_service.dat");
                    System.out.println("1. Customer list.");
                    System.out.println("2. Order list.");
                    System.out.print("Enter your option: ");
                    choose = Integer.parseInt(sc.nextLine());
                    switch (choose) {
                        case 1:
                            if (!customers_temp.isEmpty()) {
                                customers_temp.showAll();
                            }
                            break;
                        case 2:
                            if (!orders_temp.isEmpty()) {
                                orders_temp.showAll();
                                break;
                            }
                        default:
                            System.out.println("No data in the system!");
                            break;
                    }
                    break;
                default:
                    System.out.println("Exit");
                    break;

            }
        } while (testCase >= 1 && testCase <= 8);
    }
}
