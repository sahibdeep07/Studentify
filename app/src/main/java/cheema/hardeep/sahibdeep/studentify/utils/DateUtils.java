package cheema.hardeep.sahibdeep.studentify.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String TIME_FORMAT = "hh:mm";
    private static final String DATE_TIME_FORMAT = "MMM dd, YYYY hh:mm";

    public static String formatDisplayTime(Calendar time) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.US);
        return sdf.format(time.getTime());
    }

    public static String formatDisplayTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.US);
        return sdf.format(date.getTime());
    }

    public static String formatDisplayDateTime(Calendar time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);
        return sdf.format(time.getTime());
    }
}
