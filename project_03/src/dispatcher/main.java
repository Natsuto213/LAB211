package dispatcher;

import business.KOLs;
import business.Platforms;
import java.util.Scanner;
import tools.Inputter;

public class main {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Inputter ip = new Inputter();
        
        Platforms platforms = new Platforms("./src/data/KOLList.csv");
        KOLs kols = new KOLs("./src/data/kol_registrations.dat");
        
        int testcase;
        do {
            System.out.println("1. New Registration\n"
                    + "2. Update Registration Information\n"
                    + "3. Display Registered List\n"
                    + "4. Delete Registration Information\n"
                    + "5. Search KOLs by Name\n"
                    + "6. Filter Data by Category\n"
                    + "7. Statistics of Registration Numbers by Platform\n"
                    + "8. Save Data to File\n"
                    + "9. Exit the Program");
            System.out.print("Enter your choose: ");
            testcase = Integer.parseInt(sc.nextLine());
            switch (testcase) {
                case 1:
                    kols.func01(ip, platforms);
                    break;
                case 3:
                    kols.func03();
                    break;
                default:
                    System.out.println("Exiting....");
                    break;
            }
        } while (testcase > 0 && testcase < 10);
    }
}
