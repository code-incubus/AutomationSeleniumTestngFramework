package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils extends LoggerUtils {

    public static void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            logger.warn("InterruptedException in Thread.sleep(). Message: " + e.getMessage());
        }
    }

    public static Date getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * Return date and time based on milliseconds.
     *
     * @param milliseconds
     * @return
     */
    public static Date getDateTime(long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return calendar.getTime();
    }

    /**
     * Return date in desired format.
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getFormattedDateTime(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * Return current date in desired format.
     *
     * @param pattern
     * @return
     */
    public static String getFormattedCurrentDateTime(String pattern) {
        Date date = getCurrentDateTime();
        return getFormattedDateTime(date, pattern);
    }

    /**
     * Return current date and time in desired format.
     * @return
     */
    public static String getDateTimeStamp() {
        return getFormattedCurrentDateTime("yyMMddHHmmss");
    }
}
