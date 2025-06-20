package dispatcher;

import business.Customers;
import business.Reports;
import business.Rooms;
import java.util.Scanner;
import tools.Inputter;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inputter ip = new Inputter();

        Rooms rooms = new Rooms("./src/data/Active_Room_List.txt");
        Customers customers = new Customers();
        Reports reports = new Reports();

        int testcase;
        do {
            System.out.println("1. Import Room Data from Text File\n"
                    + "2. Display Available Room List\n"
                    + "3. Enter Guest Stay Information\n"
                    + "4. Update Guest by National ID\n"
                    + "5. Search Guest by National ID\n"
                    + "6. Delete Guest Reservation Before Arrival\n"
                    + "7. List Vacant Rooms\n"
                    + "8. Monthly Revenue Report\n"
                    + "9. Revenue Report by Room Type\n"
                    + "10. Save Guest Information\n"
                    + "Others - Quit");
            System.out.print("Enter your option: ");
            testcase = Integer.parseInt(sc.nextLine());
            switch (testcase) {
                case 1:
                    rooms.func01();
                    break;
                case 2:
                    rooms.func02();
                    break;
                case 3:
                    customers.func03(ip, rooms);
                    break;
                case 4:
                    customers.func04(sc, ip, rooms);
                    break;
                case 5:
                    customers.func05(sc, rooms);
                    break;
                case 6:
                    customers.func06(sc, rooms);
                    break;
                case 7:
                    rooms.func07(customers);
                    break;
                case 8:
                    reports.func08(sc, customers, rooms);
                    break;
                default:
                    System.out.println("Exit... Bye bye");
                    break;

            }
        } while (testcase > 0 && testcase < 11);
    }

}
