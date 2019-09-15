package cheema.hardeep.sahibdeep.studentify.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;

import static cheema.hardeep.sahibdeep.studentify.utils.DateUtils.getNextDayDate;

public class Studentiffffy {

    private static final String MON = "Mon";
    private static final String TUE = "Tue";
    private static final String WED = "Wed";
    private static final String THU = "Thu";
    private static final String FRI = "Fri";
    private static final String SAT = "Sat";
    private static final String AM = "AM";
    private static final String COLON = ":";
    private static final String SPACE = " ";

    public static void main(String[] args) {
        List<DayTime> dayTimeList = new ArrayList<>();
        dayTimeList.add(new DayTime("Mon", "2:45 PM", "3:45 PM"));
        dayTimeList.add(new DayTime("Thu", "5:15 PM", "6:17 PM"));
        dayTimeList.add(new DayTime("Fri", "8:00 AM", "9:00 AM"));


        for (DayTime dayTime : dayTimeList) {
            Calendar nextDayDate = getNextDayDate(dayTime);
            System.out.println(nextDayDate.getTime());
        }
    }
}
