package tools;

public class Acceptable {

    public static String ROOMID_VALID = "^R\\d{3}";

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
    
    public boolean isCapacityValid(String capacity){
        try {
            return Integer.parseInt(capacity) > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
