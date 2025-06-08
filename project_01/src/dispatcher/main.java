package dispatcher;

import business.Customers;
import business.Orders;
import business.SetMenus;
import java.util.Scanner;
import models.Customer;
import tools.Inputter;

public class main {

    public static void main(String[] args) {
        Inputter ip = new Inputter();
        Scanner sc = new Scanner(System.in);

        Customers customers = new Customers("./src/data/customers.dat");
        SetMenus setmenus = new SetMenus("./src/data/FeastMenu.csv");
        Orders orders = new Orders("./src/data/feast_order_service.dat");

        int testCase;
        int option = 0;
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
                    customers.func01(ip, sc);
                    break;
                case 2:
                    customers.func02(ip, sc);
                    break;
                case 3:
                    customers.func03(sc);
                    break;
                case 4:
                    setmenus.func04();
                    break;
                case 5:
                    orders.func05(ip, sc, customers, setmenus);
                    break;
                case 6:
                    orders.func06(ip, sc, orders, customers, setmenus);
                    break;
                case 7:
                    customers.saveToFile();
                    orders.saveToFile();
                    System.out.println("----------------------");
                    System.out.println("Update succesfull!");
                    break;
                case 8:
                    int choose;
                    Customers customers_temp = new Customers("./src/data/customers.dat");
                    Orders orders_temp = new Orders("./src/data/feast_order_service.dat");
                    System.out.println("-----------------------");
                    System.out.println("1. Customer list");
                    System.out.println("2. Order list");
                    System.out.print("Enter your option: ");

                    choose = Integer.parseInt(sc.nextLine());

                    switch (choose) {
                        case 1:
                            if (!customers_temp.isEmpty()) {
                                customers_temp.showAll();
                                break;
                            } else {
                                System.out.println("Customer list is empty!");
                            }
                            break;
                        case 2:
                            if (!orders_temp.isEmpty()) {
                                orders_temp.showAll();
                                break;
                            } else {
                                System.out.println("Order list is empty!");
                            }
                        default:
                            System.out.println("No data in the system!");
                    }
                    break;
            }
        } while (testCase >= 1 && testCase <= 8);
    }

}
