package tools;

public class Acceptable {

    public static String ID_VALID = "^(BT|FS|BC|GM|TL)\\d{6}$";
    public static String NAME_VALID = "^.{5,30}$";
    public static String PHONE_VALID = "^0[3|5|7|8|9]\\d{8}$";
    public static String EMAIL_VALID = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static String PLATFORM_CODE_VALID = "^[A-Z]{2}\\d{2}$";
    public static String FOLLOWER_VALID = "^[1-9]\\d*$";
    public static String RATE_VALID = "^(20|25)$";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
