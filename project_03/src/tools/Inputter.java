package tools;

import business.Platforms;
import java.util.Scanner;
import models.KOL;

public class Inputter {

    Scanner sc;

    public Inputter() {
        sc = new Scanner(System.in);
    }

    public String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public String input(String msg, String errorMsg, String pattern) {
        String input = "";
        boolean more;
        do {
            input = getString(msg);
            more = !Acceptable.isValid(input, pattern);
            if (more) {
                System.out.println(errorMsg + " Please try again!");
            }
        } while (more);
        return input;
    }

    public KOL inputKOL(Platforms platforms) {

        // KOL ID
        String ID = "";
        String msg = "Input KOL ID: ";
        String errorMsg = "KOL ID invalid.";
        String pattern = Acceptable.ID_VALID;
        ID = input(msg, errorMsg, pattern);

        // Name
        String name = "";
        msg = "Input KOL name: ";
        errorMsg = "Name invalid.";
        pattern = Acceptable.NAME_VALID;
        name = input(msg, errorMsg, pattern);

        // Phone
        String phone = "";
        msg = "Input KOL phone: ";
        errorMsg = "Phone invalid.";
        pattern = Acceptable.PHONE_VALID;
        phone = input(msg, errorMsg, pattern);

        // Email
        String email = "";
        msg = "Input KOL email: ";
        errorMsg = "Email invalid.";
        pattern = Acceptable.EMAIL_VALID;
        email = input(msg, errorMsg, pattern);

        // Platform code
        String platformCode = "";
        do {
            msg = "Input platform code: ";
            errorMsg = "Platform code invalid.";
            pattern = Acceptable.PLATFORM_CODE_VALID;
            platformCode = input(msg, errorMsg, pattern);

            if (platformCode.isEmpty()) {
                System.out.println("Platform code cannot be empty. Please try again");
            }

            if (platforms.findByCode(platformCode) == null) {
                System.out.println("Platform code must from the KOL list.");
                platformCode = "";
            }

        } while (platformCode.isEmpty());

        // Follower count
        String followerStr = "";
        msg = "Input follower count: ";
        errorMsg = "follower count invalid.";
        pattern = Acceptable.FOLLOWER_VALID;
        followerStr = input(msg, errorMsg, pattern);
        int follower = Integer.parseInt(followerStr);

        // Rate
        int rate;
        if (follower > 1000000) {
            rate = 25;
        } else {
            rate = 20;
        }

        KOL kol = new KOL(ID, name, phone, email, platformCode, follower, rate);

        return kol;
    }

}
