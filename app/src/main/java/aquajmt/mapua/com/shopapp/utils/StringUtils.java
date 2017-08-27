package aquajmt.mapua.com.shopapp.utils;

/**
 * Created by Bryan on 7/23/2017.
 */

public class StringUtils {

    public static String getStringOfDaysAvailable(boolean openOnSundays, boolean openOnMondays,
                                                  boolean openOnTuesdays, boolean openOnWednesdays,
                                                  boolean openOnThursdays, boolean openOnFridays,
                                                  boolean openOnSaturdays) {
        return String.valueOf(openOnSundays ? '1' : '0') +
                    (openOnMondays ? '1' : '0') +
                    (openOnTuesdays ? '1' : '0') +
                    (openOnWednesdays ? '1' : '0') +
                    (openOnThursdays ? '1' : '0') +
                    (openOnFridays ? '1' : '0') +
                    (openOnSaturdays ? '1' : '0');
    }

    public static boolean[] getDaysAvailableFromString(String daysAvailableStr) {
        if (daysAvailableStr.length() != 7)
            throwInvalidDaysAvailableStr();

        boolean[] daysAvailable = new boolean[7];
        for (int i = 0; i < daysAvailableStr.toCharArray().length; i++) {
            char c = daysAvailableStr.charAt(i);
            if (c == '1') {
                daysAvailable[i] = true;
            } else if (c == '0') {
                daysAvailable[i] = false;
            } else {
                throwInvalidDaysAvailableStr();
            }
        }
        return daysAvailable;
    }

    private static void throwInvalidDaysAvailableStr() {
        throw new IllegalArgumentException("The argument for 'daysAvailableStr' " +
                "is not valid.");
    }
}
