package tools;

public class Acceptable {

    public static String POST_INT = "";
    public static String POST_DOUBLE = "";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
