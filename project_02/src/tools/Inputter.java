package tools;

import java.util.Scanner;

public class Inputter {

    Scanner sc;

    public Inputter() {
        sc = new Scanner(System.in);
    }

    public String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
    
  

}
