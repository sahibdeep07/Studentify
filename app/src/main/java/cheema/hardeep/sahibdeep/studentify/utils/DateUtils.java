package cheema.hardeep.sahibdeep.studentify.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;

public class DateUtils {

    private static final String TIME_FORMAT = "hh:mm a";
    private static final String DAY_FORMAT = "E";
    private static final String DATE_FORMAT = "MMM dd, YYYY";
    private static final String DATE_TIME_FORMAT = "MMM dd, YYYY hh:mm a";
    private static final String MON = "Mon";
    private static final String TUE = "Tue";
    private static final String WED = "Wed";
    private static final String THU = "Thu";
    private static final String FRI = "Fri";
    private static final String SAT = "Sat";
    private static final String AM = "AM";
    private static final String COLON = ":";
    private static final String SPACE = " ";

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

    public static String formatDisplayDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);
        return sdf.format(date.getTime());
    }

    public static String formatDisplayDay(Calendar day) {
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT, Locale.US);
        return sdf.format(day.getTime());
    }

    public static String formatDisplayDate(Calendar time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return sdf.format(time.getTime());
    }

    public static String formatDisplayDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return sdf.format(date.getTime());
    }

    public static Date convertStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Calendar getNextDayDate(DayTime dayTime) {
        Calendar result = Calendar.getInstance();
        while (result.get(Calendar.DAY_OF_WEEK) != getCalendarDayOfWeek(dayTime.getDay())) {
            result.add(Calendar.DATE, 1);
        }

        String[] startTimeToken = dayTime.getStartTime().split(SPACE);
        if (startTimeToken.length == 2) {
            String[] timeToken = startTimeToken[0].split(COLON);
            if (timeToken.length == 2) {
                result.set(Calendar.HOUR, Integer.parseInt(timeToken[0]));
                result.set(Calendar.MINUTE, Integer.parseInt(timeToken[1]));
            }
            result.set(Calendar.AM_PM, startTimeToken[1].equals(AM) ? Calendar.AM : Calendar.PM);
        }
        return result;
    }

    private static int getCalendarDayOfWeek(String day) {
        int result = Calendar.MONDAY;
        switch (day) {
            case MON:
                result = Calendar.MONDAY;
                break;
            case TUE:
                result = Calendar.TUESDAY;
                break;
            case WED:
                result = Calendar.WEDNESDAY;
                break;
            case THU:
                result = Calendar.THURSDAY;
                break;
            case FRI:
                result = Calendar.FRIDAY;
                break;
            case SAT:
                result = Calendar.SATURDAY;
                break;
        }
        return result;
    }
}
