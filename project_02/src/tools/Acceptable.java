package tools;

public class Acceptable {

    public static String NATION_ID_VALID = "^\\d{12}$";
    public static String NAME_VALID = "^[A-Za-z][A-Za-z ]{1,24}$";
    public static String GENDER_VALID = "^(Male|Female)$";
    public static String PHONE_VALID = "^0[3|5|7|8|9]\\d{8}$";
    public static String ROOM_ID_VALID = "^[a-zA-Z][0-9]\\d{0,4}";
    public static String RENTAL_DAY_VALID = "^[1-9]\\d*$";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }

    public boolean isRoomIdValid(String roomID) {
        try {
            return roomID.matches("^R\\d{3}");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRateValid(String rate) {
        try {
            return Double.parseDouble(rate) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCapacityValid(String capacity) {
        try {
            return Integer.parseInt(capacity) > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
