package dispatcher;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase;

        do {
            System.out.println("1. Import Room Data from Text File"
                    + "2. Display Available Room List"
                    + "3. Enter Guest Stay Information"
                    + "4. Update Guest by National ID"
                    + "5. Search Guest by National ID"
                    + "6. Delete Guest Reservation Before Arrival"
                    + "7. List Vacant Rooms"
                    + "8. Monthly Revenue Report"
                    + "9. Revenue Report by Room Type"
                    + "10. Save Guest Information"
                    + "Others - Quit");
            System.out.print("Enter your option: ");
            testcase = Integer.parseInt(sc.nextLine());
            switch (testcase) {
                case 1:

                    break;
                default:
                    System.out.print("Exiit");
                    break;

            }
        } while (testcase > 0 && testcase < 11);
    }

}
