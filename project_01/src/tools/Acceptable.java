package tools;

public class Acceptable {

    public static String CUST_ID_VALID = "^[CcGgKk]\\d{4}$";
    public static String CUST_NAME_VALID = "^.{2,25}$";
    public static String PHONE_VALID = "0\\d{9}";
    public static String EMAIL_VALID = "[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static String POST_INT = "^[1-9]\\d*$";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
